package com.hcl.tfg.notification.model;

public class NotificationAction {

    private String action;
    private String title;
    private String icon;

    public NotificationAction() {
    }

    public NotificationAction(String action, String title, String icon) {
        this.action = action;
        this.title = title;
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
