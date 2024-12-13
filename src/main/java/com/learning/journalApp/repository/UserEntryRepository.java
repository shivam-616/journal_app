package com.learning.journalApp.repository;

import com.learning.journalApp.entites.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    User findByusername(String username);

    void deleteByUserName(String username);
}
