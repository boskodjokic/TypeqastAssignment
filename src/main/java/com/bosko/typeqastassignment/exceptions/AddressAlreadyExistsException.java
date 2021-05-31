package com.bosko.typeqastassignment.exceptions;

public class AddressAlreadyExistsException extends RuntimeException{

    public AddressAlreadyExistsException() {
        super();
    }

    public AddressAlreadyExistsException(String message) {
        super(message);
    }

    public AddressAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected AddressAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
