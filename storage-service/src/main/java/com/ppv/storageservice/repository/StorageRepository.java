package com.ppv.storageservice.repository;

import com.ppv.storageservice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StorageRepository extends MongoRepository<Note, String> {
}
