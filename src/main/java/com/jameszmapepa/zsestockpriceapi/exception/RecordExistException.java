package com.jameszmapepa.zsestockpriceapi.exception;

public class RecordExistException extends RuntimeException {

    public RecordExistException() {
    }

    public RecordExistException(String message) {
        super(message);
    }

    public RecordExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordExistException(Throwable cause) {
        super(cause);
    }

    public RecordExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
