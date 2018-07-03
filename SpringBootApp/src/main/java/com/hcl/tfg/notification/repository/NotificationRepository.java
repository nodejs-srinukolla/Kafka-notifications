package com.hcl.tfg.notification.repository;

import com.hcl.tfg.notification.model.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

}
