package com.hcl.tfg.notification.kafka;

import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.service.NotificationService;
import com.hcl.tfg.notification.utils.JsonUtil;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "${kafka.topic.notif}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        LOGGER.debug("received payload='{}'", consumerRecord.toString());
        Message msg = JsonUtil.INSTANCE.getObject(consumerRecord.value().toString(), Message.class);

        notificationService.saveNotification(msg);
    }
}
