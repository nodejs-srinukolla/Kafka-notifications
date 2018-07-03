package com.hcl.tfg.notification.exception.beans;

import java.util.List;

public class ErrorResource {

    private String code;
    private String errCode;
    private String message;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResource() {
        // Default Constructor
    }

    public ErrorResource(String code, String message) {
        this(code, null, message);
    }

    public ErrorResource(String code, String errCode, String message) {
        this.code = code;
        this.errCode = errCode;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorResource> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return "ErrorResource [code=" + code + ", errCode=" + errCode + ", message=" + message + ", fieldErrors="
                + fieldErrors + "]";
    }

}
