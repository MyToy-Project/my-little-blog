package com.project.mlb.member.domain;

import com.project.mlb.member.domain.encryptor.Encryptor;
import com.project.mlb.member.exception.InvalidPasswordFormatException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Password {

    private static final Pattern PATTERN = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$");

    @Column(name = "password", length = 100, nullable = false)
    private String value;

    protected Password() {
    }

    private Password(final String encryptedValue) {
        this.value = encryptedValue;
    }

    public static Password of(final Encryptor encryptor, final String password) {
        validate(password);
        return new Password(encryptor.encrypt(password));
    }

    private static void validate(final String password) {
        if (!PATTERN.matcher(password).matches()) {
            throw new InvalidPasswordFormatException();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
