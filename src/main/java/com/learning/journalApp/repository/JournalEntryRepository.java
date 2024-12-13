package com.learning.journalApp.repository;

import com.learning.journalApp.entites.JournallEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournallEntry , ObjectId> {
}
