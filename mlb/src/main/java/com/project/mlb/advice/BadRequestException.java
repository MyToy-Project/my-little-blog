package com.project.mlb.advice;

public class BadRequestException extends BusinessException {

    public BadRequestException(final String message) {
        super(message);
    }

}
