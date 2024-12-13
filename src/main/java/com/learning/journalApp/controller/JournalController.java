package com.learning.journalApp.controller;

import com.learning.journalApp.entites.JournallEntry;
import com.learning.journalApp.entites.User;
import com.learning.journalApp.service.JournalEntryService;
import com.learning.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllEntries(@PathVariable String username) {
        User user = userService.findByusername(username);
        List<JournallEntry> all = user.getJournallEntryList();
        if (all != null && !all.isEmpty()) return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("id/{id}")
    public ResponseEntity<JournallEntry> getEntryId(@PathVariable ObjectId id) throws NoSuchFieldException {
        Optional<JournallEntry> journallEntry = journalEntryService.findbyid(id);
        if (journallEntry.isPresent()) {
            return new ResponseEntity<>(journallEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournallEntry> createEntry(@RequestBody JournallEntry myEntry, @PathVariable String username) {
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("id/{username}/{myid}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myid, @PathVariable String username) {
        journalEntryService.deletebyid(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}/{id}")
    public ResponseEntity<?> update(
            @PathVariable ObjectId id,
            @PathVariable String username,
            @RequestBody JournallEntry newentry
    ) {
        JournallEntry old = journalEntryService.findbyid(id).orElse(null);
        if (old != null) {
            old.setContent(newentry.getContent() != null && !newentry.equals("") ? newentry.getContent() : old.getContent());
            old.setName(newentry.getName() != null && !newentry.equals("") ? newentry.getName() : old.getName());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
