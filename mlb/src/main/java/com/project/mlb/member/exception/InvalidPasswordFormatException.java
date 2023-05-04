package com.project.mlb.member.exception;

import com.project.mlb.advice.BadRequestException;

public class InvalidPasswordFormatException extends BadRequestException {

    private static final String MESSAGE = "비밀번호는 최소 8자이상 ~ 20자이하 까지 이며 하나 이상의 대문자, 소문자, 숫자, 특수 문자(@$!%*#?&)를 가져야 합니다.";

    public InvalidPasswordFormatException() {
        super(MESSAGE, MemberErrorCode.INVALID_PASSWORD_FORMAT.value());
    }

}
