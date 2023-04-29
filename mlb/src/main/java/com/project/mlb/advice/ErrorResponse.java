package com.project.mlb.advice;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(final String message) {
        this.message = message;
    }

}
