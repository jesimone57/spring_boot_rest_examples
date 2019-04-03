package com.jsimone.exception;

public class RangeValidationException extends RuntimeException {

    public RangeValidationException() {
        super();
    }

    public RangeValidationException(String message) {
        super(message);
    }
}
