package com.hcl.tfg.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableAsync
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
    
    @Bean(name = "ErrorMessageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasename("classpath:messages/err_message");
        return messageSource;
    }

}
