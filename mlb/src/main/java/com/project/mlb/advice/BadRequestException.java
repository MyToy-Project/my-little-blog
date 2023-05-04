package com.project.mlb.advice;

public class BadRequestException extends BusinessException {

    private final int errorCode;

    public BadRequestException(final String message, final int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
