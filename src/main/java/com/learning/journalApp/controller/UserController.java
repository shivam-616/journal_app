package com.learning.journalApp.controller;

import com.learning.journalApp.entites.User;
import com.learning.journalApp.repository.UserEntryRepository;
import com.learning.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepository userEntryRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userndb = userService.findByusername(username);

        userndb.setUsername(user.getUsername());
        userndb.setPassword(user.getPassword());
        userService.saveEntry(userndb);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserBYId()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        userEntryRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

