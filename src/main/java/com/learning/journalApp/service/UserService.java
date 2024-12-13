package com.learning.journalApp.service;

import com.learning.journalApp.entites.User;
import com.learning.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }


    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<User> findbyid(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public void deletebyid(ObjectId id) {
        userEntryRepository.deleteById(id);
    }
    public User findByusername(String username){
        return userEntryRepository.findByusername(username);
    }
}
