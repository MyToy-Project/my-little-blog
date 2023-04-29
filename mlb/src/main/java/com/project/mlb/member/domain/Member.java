package com.project.mlb.member.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private LoginId loginId;

    @Embedded
    private Username username;

    @Column(length = 150, unique = true)
    private String nickname;

    @Embedded
    private Password password;

    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String phone;

    protected Member() {
    }

    @Builder
    public Member(final LoginId loginId, final Username username, final String nickname, final Password password,
                  final String email, final String phone) {
        this.loginId = loginId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public LoginId getLoginId() {
        return loginId;
    }

    public Username getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Password getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
