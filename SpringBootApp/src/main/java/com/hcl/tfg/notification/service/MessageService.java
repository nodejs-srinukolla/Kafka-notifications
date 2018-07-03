package com.hcl.tfg.notification.service;

import com.hcl.tfg.notification.kafka.NotificationProducer;
import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.repository.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NotificationProducer notificationProducer;

    public boolean processMessage(Message message) {
        saveMessage(message);
        LOGGER.debug("Saved Message = " + message);
        return notificationProducer.send(message);

    }

    private Message saveMessage(Message message) {
        LOGGER.debug("Message = " + message);
        message.setTime(new Date());
        message.setRead(false);
        return messageRepository.save(message);
    }
}
