package com.learning.journalApp.entites;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@Document(collection = "journal")
@Data
@NoArgsConstructor
public class JournallEntry {


    @Id
    private ObjectId id;
    private String name;
    @NonNull
    private String content;
    private LocalDateTime date;


}
