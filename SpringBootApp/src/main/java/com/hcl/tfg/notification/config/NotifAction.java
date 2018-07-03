package com.hcl.tfg.notification.config;

public class NotifAction {

    private String action;
    private String title;
    private String icon;

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

    @Override
    public String toString() {
        return "NotifAction [action=" + action + ", title=" + title + ", icon=" + icon + "]";
    }

}
