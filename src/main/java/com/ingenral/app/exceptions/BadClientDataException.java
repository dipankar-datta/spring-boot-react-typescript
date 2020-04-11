package com.ingenral.app.exceptions;

public class BadClientDataException extends RuntimeException{

    public BadClientDataException(String message) {
        super(message);
    }
}
