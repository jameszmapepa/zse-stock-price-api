package com.jameszmapepa.zsestockpriceapi.exception;

public class EcocashConnectionException extends RuntimeException {
    public EcocashConnectionException(final String message) {
        super(message);
    }

    public EcocashConnectionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Ecocash Service not Available";
    }
}
