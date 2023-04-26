package com.project.mlb.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank
    private String loginId;

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    private String email;

    private String phone;

    private SignUpRequest() {
    }

    @Builder
    private SignUpRequest(final String username, final String nickname, final String loginId, final String password,
                         final String passwordConfirmation,
                         final String email, final String phone) {
        this.username = username;
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.email = email;
        this.phone = phone;
    }

}
