package com.icarusrises.teststuff.exceptions;


public class UserInterruptException extends Exception {

    public UserInterruptException(String message) {
        super(message);
    }

    public UserInterruptException(String message, Throwable cause) {
        super(message, cause);
    }
}
