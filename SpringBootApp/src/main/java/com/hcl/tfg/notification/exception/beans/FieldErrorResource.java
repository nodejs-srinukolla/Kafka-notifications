package com.hcl.tfg.notification.exception.beans;

public class FieldErrorResource {

    private String resourceName;

    private String field;

    private String code;

    private String message;

    public FieldErrorResource() {
        // Default Constructor
    }

    public FieldErrorResource(String resourceName, String field, String code, String message) {
        this.resourceName = resourceName;
        this.field = field;
        this.code = code;
        this.message = message;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FieldErrorResource [resourceName=" + resourceName + ", field=" + field + ", code=" + code + ", message="
                + message + "]";
    }

}
