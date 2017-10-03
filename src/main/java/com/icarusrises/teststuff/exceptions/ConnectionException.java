package com.icarusrises.teststuff.exceptions;

public class ConnectionException extends InternalFailureException {


    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
