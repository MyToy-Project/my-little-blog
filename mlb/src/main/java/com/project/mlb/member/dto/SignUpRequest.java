package com.project.mlb.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
    private String loginId;

    @NotBlank(message = "이름은 필수로 입력해야 합니다.")
    private String username;

    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수로 입력해야 합니다.")
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
