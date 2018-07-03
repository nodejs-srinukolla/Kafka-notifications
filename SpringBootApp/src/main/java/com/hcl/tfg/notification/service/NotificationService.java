package com.hcl.tfg.notification.service;

import com.hcl.tfg.notification.config.NotifConfiguration;
import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.model.Notification;
import com.hcl.tfg.notification.repository.NotificationRepository;
import com.hcl.tfg.notification.transformer.MessageTransformer;
import com.hcl.tfg.notification.utils.HttpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MessageTransformer messageTransformer;

    @Autowired
    private HttpService httpService;

    @Autowired
    private NotifConfiguration notifConfiguration;

    /**
     * Call when MongoDB is not started in Replication mode.
     * @param message
     * @return
     */
    @Async
    public String triggerNotification(Message message) {
        Notification notification = saveNotification(message);
        return pingNotificationServer(notification.getId());
    }

    public Notification saveNotification(Message message) {
        Notification notification = messageTransformer.transform(message);
        LOGGER.debug("notification = " + notification);
        notificationRepository.save(notification);
        LOGGER.debug("Saved notification = " + notification);
        return notification;
    }

    private String pingNotificationServer(String id) {
        return httpService.getApiResponse(null, httpService.getHttpHeaders(),
                notifConfiguration.getServer().getBaseUrl() + notifConfiguration.getServer().getTriggerApi() + id,
                HttpMethod.GET, String.class);
    }
}
