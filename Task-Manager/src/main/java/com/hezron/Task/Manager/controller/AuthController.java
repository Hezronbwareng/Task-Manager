package com.hezron.Task.Manager.controller;

import com.hezron.Task.Manager.model.User;
import com.hezron.Task.Manager.service.CustomUserDetailsService;
import com.hezron.Task.Manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Endpoint for login to generate JWT token
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        // On successful authentication, retrieve UserDetails and generate JWT token
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }

        return "Invalid username or password";
    }
}
