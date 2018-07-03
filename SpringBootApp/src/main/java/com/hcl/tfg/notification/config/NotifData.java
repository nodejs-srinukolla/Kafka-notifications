package com.hcl.tfg.notification.config;

public class NotifData {

    private String messageUrl;
    private String icon;

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "NotifData [messageUrl=" + messageUrl + ", icon=" + icon + "]";
    }

}
