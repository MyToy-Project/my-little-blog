package com.project.mlb.member.exception;

public enum MemberErrorCode {

    // 회원가입 관련
    DUPLICATE_LOGIN_ID(2001),
    DUPLICATE_NICKNAME(2002),
    INVALID_LOGIN_ID_FORMAT(2003),
    INVALID_PASSWORD_CONFIRMATION(2004),
    INVALID_PASSWORD_FORMAT(2005);

    private int errorCode;

    MemberErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    public int value() {
        return errorCode;
    }

}
