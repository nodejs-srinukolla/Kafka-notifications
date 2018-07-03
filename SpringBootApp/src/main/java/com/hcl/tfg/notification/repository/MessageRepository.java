package com.hcl.tfg.notification.repository;

import com.hcl.tfg.notification.model.Message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String>{

    
}
