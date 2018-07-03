package com.hcl.tfg.notification.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotifExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorProcessor errorProcessor;

    /**
     * This will intercept any {@link Throwable} thrown and convert that to json response for UI.
     * 
     * @param ex the instance of {@link Throwable}
     * @param request the spring wrapper over web request.
     * @return the json error response.
     */
    @ExceptionHandler({ Throwable.class })
    public final ResponseEntity<Object> handleException(final Throwable ex, final WebRequest request) {

        return handleExceptionInternal((Exception) ex, errorProcessor.getError(ex), getHeaderWithContentTypeJson(),
                HttpStatus.OK, request);
    }

    private HttpHeaders getHeaderWithContentTypeJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
