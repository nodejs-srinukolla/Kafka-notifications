package com.hcl.tfg.notification.utils;

import com.hcl.tfg.notification.exception.types.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

@Component
public class ValidatorUtil {

    @Autowired
    @Qualifier("ErrorMessageSource")
    private MessageSource messageSource;

    public void validate(Validator validator, Object bean, String objectName) {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(getPropertyBean(bean), objectName);
        validator.validate(bean, result);

        if (result.hasErrors() || result.hasFieldErrors()) {
            throw new ValidationException("CLIENT ERROR", result);
        }
    }

    private Object getPropertyBean(Object bean) {
        Object propertyBean = bean;
        if (bean instanceof Object[] && ((Object[]) bean).length > 0) {
            propertyBean = ((Object[]) bean)[0];
        }
        return propertyBean;
    }

}
