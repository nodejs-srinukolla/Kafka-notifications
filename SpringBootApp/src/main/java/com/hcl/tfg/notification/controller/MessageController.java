package com.hcl.tfg.notification.controller;

import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.service.MessageService;
import com.hcl.tfg.notification.utils.ValidatorUtil;
import com.hcl.tfg.notification.validators.MessageValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private MessageValidator messageValidator;
    
    @Autowired
    private ValidatorUtil validatorUtil;

    @PostMapping
    public boolean sendNotification(@RequestBody Message message) {
        validatorUtil.validate(messageValidator, message, "message");
        return messageService.processMessage(message);
    }
}
