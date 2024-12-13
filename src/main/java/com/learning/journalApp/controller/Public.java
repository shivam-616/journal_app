package com.learning.journalApp.controller;

import com.learning.journalApp.entites.User;
import com.learning.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Public {

    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }
}
