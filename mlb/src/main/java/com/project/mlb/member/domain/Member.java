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

    @Column(length = 100, nullable = false, unique = true)
    private String loginId;

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
    public Member(final String loginId, final Username username, final String nickname, final Password password,
                  final String email, final String phone) {
        this.loginId = loginId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

}
