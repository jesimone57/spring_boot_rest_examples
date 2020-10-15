package com.jsimone.exception;

import com.jsimone.error.ErrorResponse;

public class ErrorResponseException extends RuntimeException {
    private ErrorResponse errorResponse = null;

    public ErrorResponseException() {
        super();
    }

    public ErrorResponseException(ErrorResponse errorResponse) {
        super();
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
