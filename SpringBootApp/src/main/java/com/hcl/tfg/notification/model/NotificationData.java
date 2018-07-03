package com.hcl.tfg.notification.model;

import java.util.Date;

public class NotificationData {

    private Long dateOfArrival;
    private String primaryKey;
    private String url;

    public NotificationData() {
    }

    public NotificationData(String primaryKey, String url) {
        this.dateOfArrival = new Date().getTime();
        this.primaryKey = primaryKey;
        this.url = url;
    }

    public Long getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Long dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
