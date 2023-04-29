package com.project.mlb.member.domain;

import com.project.mlb.member.exception.InvalidLoginIdFormatException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class LoginId {

    private static final Pattern PATTERN = Pattern.compile("^[a-z]{1}[a-z0-9]{6,19}$");

    @Column(name = "loginId", length = 100, nullable = false, unique = true)
    private String value;

    protected LoginId() {
    }

    private LoginId(final String value) {
        this.value = value;
    }

    public static LoginId from(final String loginId) {
        validate(loginId);
        return new LoginId(loginId);
    }

    private static void validate(final String loginId) {
        if (!PATTERN.matcher(loginId).matches()) {
            throw new InvalidLoginIdFormatException();
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
        LoginId loginId = (LoginId) o;
        return Objects.equals(value, loginId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
