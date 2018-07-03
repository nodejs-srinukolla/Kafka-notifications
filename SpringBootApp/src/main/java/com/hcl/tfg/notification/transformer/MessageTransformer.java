package com.hcl.tfg.notification.transformer;

import com.hcl.tfg.notification.config.NotifConfiguration;
import com.hcl.tfg.notification.model.Message;
import com.hcl.tfg.notification.model.Notification;
import com.hcl.tfg.notification.model.NotificationAction;
import com.hcl.tfg.notification.model.NotificationData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageTransformer implements Transformer<Message, Notification> {

    @Autowired
    private NotifConfiguration notifConfiguration;

    @Override
    public Notification transform(Message msg) {

        String icon = notifConfiguration.getData().getIcon();
        String messageUrl = notifConfiguration.getData().getMessageUrl() + msg.getId();
        NotificationData data = new NotificationData(msg.getId(), messageUrl);

        List<NotificationAction> actions = new ArrayList<>();
        actions.add(new NotificationAction(notifConfiguration.getAction().getAction(),
                notifConfiguration.getAction().getTitle(), notifConfiguration.getAction().getIcon()));
        return new Notification(msg.getTitle(), msg.getText(), icon, data, actions);

    }

}
