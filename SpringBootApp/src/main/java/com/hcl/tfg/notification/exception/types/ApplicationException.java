package com.hcl.tfg.notification.exception.types;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -1606764628090262816L;
    
    private String errCode;
    private Object[] args;

    public ApplicationException() {
        super();
    }

    public ApplicationException(final String errCode) {
        this.errCode = errCode;
    }

    public ApplicationException(final String errCode, Object... args) {
        this.args = args;
        this.errCode = errCode;
    }

    public ApplicationException(final Throwable throwable) {
        super(throwable);
    }

    public ApplicationException(final Throwable throwable, final String errCode) {
        super(throwable);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
