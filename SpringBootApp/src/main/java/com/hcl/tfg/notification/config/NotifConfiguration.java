package com.hcl.tfg.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notification")
public class NotifConfiguration {

    private NotifServer server;
    private NotifData data;
    private NotifAction action;

    public NotifServer getServer() {
        return server;
    }

    public void setServer(NotifServer server) {
        this.server = server;
    }

    public NotifData getData() {
        return data;
    }

    public void setData(NotifData data) {
        this.data = data;
    }

    public NotifAction getAction() {
        return action;
    }

    public void setAction(NotifAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "NotifConfiguration [server=" + server + ", data=" + data + ", action=" + action + "]";
    }

}
