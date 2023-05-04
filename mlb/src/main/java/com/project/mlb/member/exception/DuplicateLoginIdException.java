package com.project.mlb.member.exception;

import com.project.mlb.advice.BadRequestException;

public class DuplicateLoginIdException extends BadRequestException {

    private static final String MESSAGE = "이미 존재하는 아이디 입니다.";

    public DuplicateLoginIdException() {
        super(MESSAGE, MemberErrorCode.DUPLICATE_LOGIN_ID.value());
    }

}
