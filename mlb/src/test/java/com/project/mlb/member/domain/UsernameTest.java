package com.project.mlb.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mlb.member.domain.encryptor.Encryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsernameTest {

    @DisplayName("사용자의 이름은 인코딩 되어야 한다.")
    @Test
    void create_username_withEncoded() {
        String text = "테스트이름";
        Encryptor encryptor = new Encryptor();
        String expectedValue = encryptor.encrypt(text);

        Username username = Username.of(new Encryptor(), text);

        assertThat(username.getValue()).isEqualTo(expectedValue);
    }

    @DisplayName("사용자의 이름이 동일하다면 동등한 객체다")
    @Test
    void same_username_equal() {
        Username username1 = Username.of(new Encryptor(), "사용자");
        Username username2 = Username.of(new Encryptor(), "사용자");

        assertThat(username1).isEqualTo(username2);
    }

}