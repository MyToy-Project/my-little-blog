package com.project.mlb.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.project.mlb.member.dto.SignUpRequest;
import com.project.mlb.member.exception.DuplicateLoginIdException;
import com.project.mlb.member.exception.DuplicateNicknameException;
import com.project.mlb.member.exception.InvalidPasswordConfirmationException;
import com.project.mlb.member.exception.InvalidPasswordFormatException;
import com.project.mlb.member.service.MemberService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest {

    @MockBean
    MemberService memberService;

    MockMvcRequestSpecification restDocs;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        restDocs = RestAssuredMockMvc.given()
                .mockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .apply(documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint()))
                        .build())
                .log().all();
    }

    @DisplayName("회원가입을 하면 201 반환")
    @Test
    void signUp() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test1234")
                .username("이호석")
                .nickname("키다리")
                .password("!Asdf1234")
                .passwordConfirmation("!Asdf1234")
                .email("test1234@test.com")
                .phone("010-0000-0000")
                .build();

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpRequest)
                .when().post("/api/v1/members")
                .then().log().all()
                .assertThat()
                .apply(document("member/signup/success"))
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("비밀번호 확인이 다르다면 400 Bad Request 반환")
    @Test
    void signUp_exception_invalidConfirmationPassword() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test1234")
                .username("이호석")
                .nickname("키다리")
                .password("!Asdf1234")
                .passwordConfirmation("123")
                .email("test1234@test.com")
                .phone("010-0000-0000")
                .build();

        doThrow(new InvalidPasswordConfirmationException())
                .when(memberService)
                .signUp(any());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpRequest)
                .when().post("/api/v1/members")
                .then().log().all()
                .assertThat()
                .apply(document("member/signup/fail/invalidConfirmationPassword"))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("비밀번호 형식이 다르다면 400 Bad Reqeust 반환")
    @Test
    void signUp_exception_invalidPasswordFormat() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test1234")
                .username("이호석")
                .nickname("키다리")
                .password("invalidPassword")
                .passwordConfirmation("invalidPassword")
                .email("test1234@test.com")
                .phone("010-0000-0000")
                .build();

        doThrow(new InvalidPasswordFormatException())
                .when(memberService)
                .signUp(any());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpRequest)
                .when().post("/api/v1/members")
                .then().log().all()
                .assertThat()
                .apply(document("member/signup/fail/invalidPasswordFormat"))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이미 존재하는 아이디가 있다면 400 Bad Request 반환")
    @Test
    void signUp_exception_duplicatedLoginId() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("duplicateloginid123")
                .username("이호석")
                .nickname("키다리")
                .password("!Asdf1234")
                .passwordConfirmation("!Asdf1234")
                .email("test1234@test.com")
                .phone("010-0000-0000")
                .build();

        doThrow(new DuplicateLoginIdException())
                .when(memberService)
                .signUp(any());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpRequest)
                .when().post("/api/v1/members")
                .then().log().all()
                .assertThat()
                .apply(document("member/signup/fail/duplicatedLoginId"))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이미 존재하는 닉네임이 있다면 400 Bad Request 반환")
    @Test
    void signUp_exception_duplicatedNickname() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test123")
                .username("이호석")
                .nickname("중복닉네임")
                .password("!Asdf1234")
                .passwordConfirmation("!Asdf1234")
                .email("test1234@test.com")
                .phone("010-0000-0000")
                .build();

        doThrow(new DuplicateNicknameException())
                .when(memberService)
                .signUp(any());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpRequest)
                .when().post("/api/v1/members")
                .then().log().all()
                .assertThat()
                .apply(document("member/signup/fail/duplicatedNickname"))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}