package com.project.mlb.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.project.mlb.member.domain.encryptor.Encryptor;
import com.project.mlb.member.exception.InvalidPasswordFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {

    @DisplayName("비밀번호는 최소 8자이상 ~ 20자이하 까지 이며 하나 이상의 대문자, 소문자, 숫자, 특수 문자(@$!%*#?&)가 아니라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"123", " ", "", "asdfㅋ", "adsfD123!ㄷㄱ", "!Aasdf123123124123123123"})
    void create_exception_withInvalidFormat(String invalidPassword) {
        assertThatThrownBy(() -> Password.of(new Encryptor(), invalidPassword))
                .isInstanceOf(InvalidPasswordFormatException.class)
                .hasMessageContaining("비밀번호는 최소 8자이상 ~ 20자이하 까지 이며 하나 이상의 대문자, 소문자, 숫자, 특수 문자(@$!%*#?&)를 가져야 합니다.");
    }

    @DisplayName("비밀번호는 최소 8자이상 ~ 20자이하 까지 이며 하나 이상의 대문자, 소문자, 숫자, 특수 문자(@$!%*#?&)가 맞다면 통과합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"!Asdf123", "@Asd1123412345234123"})
    void create_success_withValidFormat(String validPassword) {
        assertThatNoException()
                .isThrownBy(() -> Password.of(new Encryptor(), validPassword));
    }

    @DisplayName("동일한 패스워드를 가지고 있다면 동등성이 보장된다.")
    @Test
    void equal_success_samePasswordValue() {
        Password password1 = Password.of(new Encryptor(), "!Test1234");
        Password password2 = Password.of(new Encryptor(), "!Test1234");

        assertThat(password1).isEqualTo(password2);
    }

}