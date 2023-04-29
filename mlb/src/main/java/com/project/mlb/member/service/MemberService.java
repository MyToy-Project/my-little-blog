package com.project.mlb.member.service;

import com.project.mlb.member.domain.LoginId;
import com.project.mlb.member.domain.Member;
import com.project.mlb.member.domain.Password;
import com.project.mlb.member.domain.Username;
import com.project.mlb.member.domain.encryptor.Encryptor;
import com.project.mlb.member.dto.SignUpRequest;
import com.project.mlb.member.exception.DuplicateLoginIdException;
import com.project.mlb.member.exception.DuplicateNicknameException;
import com.project.mlb.member.exception.InvalidPasswordConfirmationException;
import com.project.mlb.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encryptor encryptor;

    public MemberService(final MemberRepository memberRepository, final Encryptor encryptor) {
        this.memberRepository = memberRepository;
        this.encryptor = encryptor;
    }

    @Transactional
    public void signUp(final SignUpRequest signUpRequest) {
        validate(signUpRequest);

        Member member = Member.builder()
                .loginId(LoginId.from(signUpRequest.getLoginId()))
                .username(Username.of(encryptor, signUpRequest.getUsername()))
                .nickname(signUpRequest.getNickname())
                .password(Password.of(encryptor, signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .phone(signUpRequest.getPhone())
                .build();
        memberRepository.save(member);
    }

    private void validate(final SignUpRequest signUpRequest) {
        confirmPassword(signUpRequest.getPassword(), signUpRequest.getPasswordConfirmation());
        validateLoginId(signUpRequest.getLoginId());
        validateNickname(signUpRequest.getNickname());
    }

    private void validateNickname(final String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private void validateLoginId(final String loginId) {
        if (memberRepository.existsByLoginIdValue(loginId)) {
            throw new DuplicateLoginIdException();
        }
    }

    private void confirmPassword(final String password, final String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new InvalidPasswordConfirmationException();
        }
    }

}
