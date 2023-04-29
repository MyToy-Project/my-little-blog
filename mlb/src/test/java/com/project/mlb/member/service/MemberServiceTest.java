package com.project.mlb.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.project.mlb.member.domain.encryptor.Encryptor;
import com.project.mlb.member.dto.SignUpRequest;
import com.project.mlb.member.exception.DuplicateLoginIdException;
import com.project.mlb.member.exception.DuplicateNicknameException;
import com.project.mlb.member.exception.InvalidPasswordConfirmationException;
import com.project.mlb.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Encryptor encryptor;

    SignUpRequest signUpRequest;

    @BeforeEach
    void setUp() {
        signUpRequest = SignUpRequest.builder()
                .loginId("test123")
                .username("이호석")
                .nickname("키다리")
                .password("!Abc123123")
                .passwordConfirmation("!Abc123123")
                .email("asdf@test.com")
                .phone("010-0000-0000")
                .build();
    }

    @DisplayName("회원가입 조건을 만족하면 회원가입을 성공한다.")
    @Test
    void signUp() {
        memberService.signUp(signUpRequest);

        assertThat(
                memberRepository.findByLoginIdValueAndPasswordValue("test123", encryptor.encrypt("!Abc123123"))).isPresent();
    }

    @DisplayName("회원가입시 비밀번호와 비밀번호 확인이 일치하지 않으면 회원가입을 실패한다.")
    @Test
    void signUp_exception_invalidConfirmationPassword() {
        SignUpRequest request = SignUpRequest.builder()
                .loginId("test123")
                .username("이호석")
                .nickname("키다리")
                .password("!Abc123123")
                .passwordConfirmation("!dddd123123")
                .email("asdf@test.com")
                .phone("010-0000-0000")
                .build();

        assertThatThrownBy(() -> memberService.signUp(request))
                .isInstanceOf(InvalidPasswordConfirmationException.class)
                .hasMessageContaining("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }

    @DisplayName("회원가입시 이미 존재하는 로그인 아이디를 입력하면 회원가입을 실패한다.")
    @Test
    void signUp_exception_duplicatedLoginId() {
        memberService.signUp(signUpRequest);
        SignUpRequest duplicatedLoginIdRequest = SignUpRequest.builder()
                .loginId("test123")
                .username("홍길동")
                .nickname("도적")
                .password("!Abc111222")
                .passwordConfirmation("!Abc111222")
                .build();

        assertThatThrownBy(() -> memberService.signUp(duplicatedLoginIdRequest))
                .isInstanceOf(DuplicateLoginIdException.class)
                .hasMessageContaining("이미 존재하는 아이디 입니다.");
    }

    @DisplayName("회원가입시 이미 존재하는 닉네임을 입력하면 회원가입을 실패한다.")
    @Test
    void signUp_exception_duplicatedNickname() {
        memberService.signUp(signUpRequest);
        SignUpRequest duplicatedNicknameRequest = SignUpRequest.builder()
                .loginId("asdfasdf123")
                .username("이호석")
                .nickname("키다리")
                .password("!Abc123123")
                .passwordConfirmation("!Abc123123")
                .build();

        assertThatThrownBy(() -> memberService.signUp(duplicatedNicknameRequest))
                .isInstanceOf(DuplicateNicknameException.class)
                .hasMessageContaining("이미 존재하는 닉네임 입니다.");
    }
}