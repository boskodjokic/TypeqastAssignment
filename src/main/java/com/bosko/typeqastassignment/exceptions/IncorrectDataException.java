package com.bosko.typeqastassignment.exceptions;

public class IncorrectDataException extends RuntimeException{

    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }

    protected IncorrectDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
