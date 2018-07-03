package com.hcl.tfg.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "notifications")
public class Notification {

    public static final String COLLECTION_NAME = "notifications";

    @Id
    @Field("id")
    private String id;

    private String title;
    private String body;
    private String icon;
    private NotificationData data;
    private List<NotificationAction> actions;

    public Notification() {
    }

    public Notification(String title, String body, String icon, NotificationData data,
            List<NotificationAction> actions) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.data = data;
        this.actions = actions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }

    public List<NotificationAction> getActions() {
        return actions;
    }

    public void setActions(List<NotificationAction> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Notification [id=" + id + ", title=" + title + ", body=" + body + ", icon=" + icon + ", data=" + data
                + ", actions=" + actions + "]";
    }

}
