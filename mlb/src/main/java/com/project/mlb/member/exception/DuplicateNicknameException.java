package com.project.mlb.member.exception;

import com.project.mlb.advice.BadRequestException;

public class DuplicateNicknameException extends BadRequestException {

    private static final String MESSAGE = "이미 존재하는 닉네임 입니다.";

    public DuplicateNicknameException() {
        super(MESSAGE);
    }

}
