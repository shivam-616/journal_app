package com.learning.journalApp.service;

import com.learning.journalApp.entites.User;
import com.learning.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        User user = userEntryRepository.findByUsername(username);
        List<String> roles = user.getRoles() != null ? user.getRoles() : new ArrayList<>();
        // Log retrieved user details for debugging
        System.out.println("User found: " + user.getUsername() + ", Roles: " + user.getRoles());

        // Build UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Ensure this is hashed with BCryptPasswordEncoder
                .roles(user.getRoles().toArray(new String[0])) // Convert roles to String array
                .build();
    }
}

