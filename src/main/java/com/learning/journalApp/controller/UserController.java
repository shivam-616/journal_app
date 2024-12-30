package com.learning.journalApp.controller;

import com.learning.journalApp.api_response.weather_response;
import com.learning.journalApp.entites.User;
import com.learning.journalApp.repository.UserEntryRepository;
import com.learning.journalApp.service.UserService;
import com.learning.journalApp.service.WeatherServices;
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

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        System.out.println(userName);
        User userInDb = userService.findByusername(userName);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Check if user exists
        User user = userService.findByusername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Delete user
        userService.deleteByUsername(username);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @Autowired
    private WeatherServices weather;


    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        weather_response w =   weather.getWeather("Mumbai");
        String greeting  =" ";
        if(w!=null){
            greeting = ",  Weather feels like " + w.getCurrent().getFeelsLikeC();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
}

