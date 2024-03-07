package org.example.exception;

public class CustomException extends Exception {
    private final int errorCode;
    private final String message;

    public CustomException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

