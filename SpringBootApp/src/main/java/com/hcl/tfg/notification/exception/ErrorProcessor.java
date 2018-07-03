package com.hcl.tfg.notification.exception;

import com.hcl.tfg.notification.exception.beans.ErrorResource;
import com.hcl.tfg.notification.exception.beans.FieldErrorResource;
import com.hcl.tfg.notification.exception.types.ApplicationException;
import com.hcl.tfg.notification.exception.types.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ErrorProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorProcessor.class);
    private static final String APPLICATION_EXCEPTION = "ApplicationException";
    private static final String VALIDATION_EXCEPTION = "ValidationException";
    private static final String SYSTEM_EXCEPTION = "SystemException";

    @Autowired
    @Qualifier("ErrorMessageSource")
    private MessageSource messageSource;

    private Locale locale = Locale.ENGLISH;

    public ErrorResource getError(final Throwable ex) {
        ErrorResource error = null;
        if (ex instanceof ValidationException) {
            error = getValidationError(ex);

        } else if (ex instanceof ApplicationException) {
            error = getApplicationError(ex);
        } else {
            error = getSystemError(ex);
        }
        return error;
    }

    private ErrorResource getSystemError(Throwable ex) {
        ErrorResource error = new ErrorResource(SYSTEM_EXCEPTION,
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        LOGGER.error(SYSTEM_EXCEPTION, ex);
        return error;
    }

    private ErrorResource getApplicationError(Throwable ex) {
        ApplicationException applicationException = (ApplicationException) ex;
        String errMessage = applicationException.getLocalizedMessage();

        if (!StringUtils.isEmpty(applicationException.getErrCode())) {
            errMessage = messageSource.getMessage(applicationException.getErrCode(), applicationException.getArgs(),
                    locale);
        }

        ErrorResource error = new ErrorResource(APPLICATION_EXCEPTION, applicationException.getErrCode(), errMessage);
        LOGGER.error("[" + applicationException.getErrCode() + "] " + errMessage, applicationException);
        return error;
    }

    private ErrorResource getValidationError(Throwable ex) {

        ValidationException validationException = (ValidationException) ex;

        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();

        setGlobalErrors(validationException, fieldErrorResources, locale);

        for (FieldError fieldErr : validationException.getErrors().getFieldErrors()) {
            String errMessage = messageSource.getMessage(fieldErr.getCode(), fieldErr.getArguments(), locale);
            FieldErrorResource fieldErrorResource = new FieldErrorResource(fieldErr.getObjectName(),
                    fieldErr.getField(), fieldErr.getCode(), errMessage);
            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource(VALIDATION_EXCEPTION, validationException.getMessage());
        error.setFieldErrors(fieldErrorResources);
        LOGGER.error(VALIDATION_EXCEPTION + " '{}'", error.toString(), validationException);
        return error;
    }

    private void setGlobalErrors(ValidationException bex, List<FieldErrorResource> fieldErrorResources, Locale locale) {
        if (bex.getErrors().getGlobalErrors() != null) {
            for (ObjectError error : bex.getErrors().getGlobalErrors()) {
                String errMessage = messageSource.getMessage(error.getCode(), error.getArguments(), locale);
                FieldErrorResource fieldErrorResource = new FieldErrorResource(error.getObjectName(), null,
                        error.getCode(), errMessage);
                fieldErrorResources.add(fieldErrorResource);
            }
        }
    }
}
