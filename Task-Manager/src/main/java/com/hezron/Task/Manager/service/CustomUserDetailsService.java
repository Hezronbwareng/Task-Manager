package com.hezron.Task.Manager.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace this with your user retrieval logic
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password("{bcrypt}$2a$10$Wz4R1G/m12nGzreWlWQ4xOynScAAMr9J2xDeNwoDAGa7Hph8EhmP.")
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
