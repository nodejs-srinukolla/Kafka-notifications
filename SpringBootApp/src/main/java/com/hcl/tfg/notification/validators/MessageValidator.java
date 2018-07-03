package com.hcl.tfg.notification.validators;

import com.hcl.tfg.notification.model.Message;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MessageValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Message.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object msg, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "url.required");

    }

}
