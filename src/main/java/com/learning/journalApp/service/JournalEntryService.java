package com.learning.journalApp.service;

import com.learning.journalApp.entites.JournallEntry;
import com.learning.journalApp.entites.User;
import com.learning.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournallEntry journallEntry, String username) {

        try {
            User user = userService.findByusername(username);
            journallEntry.setDate(LocalDateTime.now());
            JournallEntry saved = journalEntryRepository.save(journallEntry);
            user.getJournallEntryList().add(saved);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("error occuried");
        }
    }
    public void saveEntry(JournallEntry journallEntry) {

    journalEntryRepository.save(journallEntry);
    }

    public List<JournallEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournallEntry> findbyid(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deletebyid(ObjectId id, String username){
        User user = userService.findByusername(username);
        user.getJournallEntryList().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }
}

