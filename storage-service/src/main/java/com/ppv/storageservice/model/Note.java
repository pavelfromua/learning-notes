package com.ppv.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "t_notes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Note {
    @Id
    private String id;
    private String question;
    private String answer;
}
