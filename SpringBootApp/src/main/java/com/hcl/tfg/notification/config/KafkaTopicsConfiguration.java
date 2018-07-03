package com.hcl.tfg.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("kafka.topic")
public class KafkaTopicsConfiguration {

    private String notif;
    private String streamInput;
    private String streamStore;
    private String streamOutput;

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public String getStreamInput() {
        return streamInput;
    }

    public void setStreamInput(String streamInput) {
        this.streamInput = streamInput;
    }

    public String getStreamStore() {
        return streamStore;
    }

    public void setStreamStore(String streamStore) {
        this.streamStore = streamStore;
    }

    public String getStreamOutput() {
        return streamOutput;
    }

    public void setStreamOutput(String streamOutput) {
        this.streamOutput = streamOutput;
    }

    @Override
    public String toString() {
        return "KafkaTopicsConfiguration [notif=" + notif + ", streamInput=" + streamInput + ", streamStore="
                + streamStore + ", streamOutput=" + streamOutput + "]";
    }

}
