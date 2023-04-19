package com.project.mlb.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String loginId;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String phone;

}
