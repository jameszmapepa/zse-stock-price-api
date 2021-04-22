package com.jameszmapepa.zsestockpriceapi.exception;


public class EcoCashException extends RuntimeException {

    public EcoCashException() {
    }

    public EcoCashException(String s) {
        super(s);
    }

    public EcoCashException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EcoCashException(Throwable throwable) {
        super(throwable);
    }

    public EcoCashException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
