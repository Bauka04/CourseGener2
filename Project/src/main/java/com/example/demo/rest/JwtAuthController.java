package com.example.demo.rest;

import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;
import com.example.demo.jwt.JWTTokenGenerator;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class JwtAuthController {

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{

        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails =
                userService.loadUserByUsername(request.getEmail());

        final String token = jwtTokenGenerator.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws Exception{
        String email = request.getEmail();
        if (userService.getUserByEmail(email)==null){
            String password = request.getPassword();
            String fullName = request.getFullName();
            List<Roles> roles = new ArrayList<>();
            Roles role = userService.getRole("ROLE_USER");
            roles.add(role);
            userService.addUser(new Users(null, email, encoder.encode(password), fullName, roles));
            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails =
                    userService.loadUserByUsername(request.getEmail());

            final String token = jwtTokenGenerator.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        else{
            return ResponseEntity.status(409).body("Email exists");
        }
    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}