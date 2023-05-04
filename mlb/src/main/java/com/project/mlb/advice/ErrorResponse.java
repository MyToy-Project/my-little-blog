package com.project.mlb.advice;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String message;
    private final int errorCode;

    public ErrorResponse(final String message, final int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
