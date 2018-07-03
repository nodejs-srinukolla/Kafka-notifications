package com.hcl.tfg.notification.config;

public class NotifServer {

    private String baseUrl;

    private String triggerApi;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getTriggerApi() {
        return triggerApi;
    }

    public void setTriggerApi(String triggerApi) {
        this.triggerApi = triggerApi;
    }

    @Override
    public String toString() {
        return "NotifServer [baseUrl=" + baseUrl + ", triggerApi=" + triggerApi + "]";
    }

}
