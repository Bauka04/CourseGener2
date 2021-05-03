package com.example.demo.filters;

import io.jsonwebtoken.ExpiredJwtException;
import com.example.demo.jwt.JWTTokenGenerator;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Auth: "+ request.getHeader("Authorization"));
        final String requestTokenHeader = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlcmxhbkBtYWlsLnJ1IiwiZXhwIjoxNjE5MDI1MTUyLCJpYXQiOjE2MTkwMDcxNTJ9.9DL6h5aoOQJwRcdcAVEcXs3j-tt0E8-R8othbC39Hpuw8QroaQbLMtCfVtk3IPILSlNfueIDvlbpZ4VB-7VQXA";
        String email = null;
        String jwtToken = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try{

                email = jwtTokenGenerator.getEmailFromToken(jwtToken);

            }catch (IllegalArgumentException e){
                System.out.println("Unable get JWT Token");
            }catch (ExpiredJwtException e){
                System.out.println("JWT was expired");
            }

        }else{

            System.out.println("Token doesn't starts with Bearer");

        }

        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = userService.loadUserByUsername(email);

            if(jwtTokenGenerator.validateToken(jwtToken, userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }

        filterChain.doFilter(request, response);

    }
}