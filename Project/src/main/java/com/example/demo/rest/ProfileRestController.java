package com.example.demo.rest;


import com.example.demo.dto.*;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage(){
        Users user = getUser();
        return new ResponseEntity<>(new UserDTO(user.getId(), user.getEmail(), user.getRoles(), user.getFull_name()), HttpStatus.OK);
    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }
    @PostMapping(value = "/delete_acc")
    public ResponseEntity<?> deleteAccount(HttpServletRequest request, HttpServletResponse response){
        Users user = getUser();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        userService.deleteUser(user);
        return ResponseEntity.ok().body("Deleted");
    }

    @PostMapping(value = "/update_acc")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request){
        System.out.println(request.getEmail());
        System.out.println(request.getFullName());
        Users user = getUser();
        if(userService.getUserByEmail(request.getEmail())==null || user.getEmail().equals(request.getEmail())) {
            user.setEmail(request.getEmail());
            user.setFull_name(request.getFullName());
            userService.saveUser(user);
            return new ResponseEntity<>(new UserDTO(user.getId(), user.getEmail(), user.getRoles(), user.getFull_name()), HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(409).body("Email exists");
        }
    }

    @PostMapping(value = "/update_pass")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request){
        Users user = getUser();
        if(encoder.matches(request.getPassword(),user.getPassword())){
            user.setPassword(encoder.encode(request.getNewPassword()));
            userService.saveUser(user);
            return ResponseEntity.ok().body("Updated");
        }
        else {
            return ResponseEntity.status(409).body("Password is incorrect");
        }
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        System.out.println("Log out");
        return ResponseEntity.ok().body("Logged Out");
    }



}
