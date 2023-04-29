package com.project.mlb.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.project.mlb.member.exception.InvalidLoginIdFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LoginIdTest {

    @DisplayName("로그인 아이디는 영문과 숫자로만(7 ~ 20자) 이루어져 있어야 하며, 반드시 영문으로 시작하지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1asadf123", "asf_123", "as1223", "1234"})
    void create_exception_invalidLoginIdFormat(String invalidLoginId) {
        assertThatThrownBy(() -> LoginId.from(invalidLoginId))
                .isInstanceOf(InvalidLoginIdFormatException.class)
                .hasMessageContaining("로그인 아이디는 영문과 숫자로만(7 ~ 20자) 이루어져 있어야 하며, 반드시 영문으로 시작해야 합니다.");
    }

    @DisplayName("동일한 아이디라면 동등하다")
    @Test
    void same_success_equalLoginId() {
        LoginId loginId1 = LoginId.from("test1234");
        LoginId loginId2 = LoginId.from("test1234");

        assertThat(loginId1).isEqualTo(loginId2);
    }

}