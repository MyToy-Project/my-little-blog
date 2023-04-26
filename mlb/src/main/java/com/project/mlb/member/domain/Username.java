package com.project.mlb.member.domain;

import com.project.mlb.member.domain.encryptor.Encryptor;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Username {

    @Column(name = "username", length = 100)
    private String value;

    protected Username() {
    }

    private Username(final String encryptedValue) {
        this.value = encryptedValue;
    }

    public static Username of(final Encryptor encryptor, final String name) {
        return new Username(encryptor.encrypt(name));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Username username = (Username) o;
        return Objects.equals(value, username.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
