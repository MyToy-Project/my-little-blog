package com.project.mlb.member.exception;

import com.project.mlb.advice.BadRequestException;

public class InvalidLoginIdFormatException extends BadRequestException {

    private static final String MESSAGE = "로그인 아이디는 영문과 숫자로만(7 ~ 20자) 이루어져 있어야 하며, 반드시 영문으로 시작해야 합니다.";

    public InvalidLoginIdFormatException() {
        super(MESSAGE);
    }

}
