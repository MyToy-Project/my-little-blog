package com.project.mlb.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mlb.member.domain.encryptor.Encryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @DisplayName("사용자의 이름은 인코딩 되어야 한다.")
    @Test
    void create_username_withEncoded() {
        String text = "테스트이름";
        Encryptor encryptor = new Encryptor();
        String expectedValue = encryptor.encrypt(text);

        Username username = Username.of(new Encryptor(), text);

        assertThat(username.getValue()).isEqualTo(expectedValue);
    }

}