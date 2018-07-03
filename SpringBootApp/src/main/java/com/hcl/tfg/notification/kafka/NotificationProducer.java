package com.hcl.tfg.notification.kafka;

import com.hcl.tfg.notification.config.KafkaTopicsConfiguration;
import com.hcl.tfg.notification.exception.types.ApplicationException;
import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.utils.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class NotificationProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTopicsConfiguration kafkaTopicsConfiguration;

    public boolean send(final Message message) {
        LOGGER.debug("sending data='{}'", message);

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopicsConfiguration.getNotif(),
                JsonUtil.INSTANCE.getJsonString(message));

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.debug("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("unable to send message='{}'", message, ex);
            }
        });
        SendResult<String, String> result = null;
        try {
            result = future.get(60, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new ApplicationException("message.sent.error", e);
        }
        LOGGER.debug("result='{}'", result);
        return null != result;
    }

}
