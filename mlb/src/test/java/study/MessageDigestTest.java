package study;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 회원가입시 회원의 실명과 비밀번호를 암호화 하기 위한 MessageDisgest 학습 테스트
 */
class MessageDigestTest {


    @DisplayName("학습 테스트: MessageDigest 테스트")
    @Test
    void encryptTest() {
        Charset charset = Charset.defaultCharset();
        System.out.println("charset = " + charset);
        try {
            // SHA-256 해쉬 알고리즘을 이용한 messageDigest 인스턴스 생성
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 비밀번호를 인코딩
            messageDigest.update("sd".getBytes());
            // 256비트이므로 32길이의 byte 배열이 반환됨
            byte[] digest = messageDigest.digest();

            // 비밀번호에 대한 일관적인 크기를 얻기 위해 16진수로 컨버팅
            StringBuilder hexBuilder = new StringBuilder();
            StringBuilder byteBuilder = new StringBuilder();
            for (byte b : digest) {
                hexBuilder.append(String.format("%02x", b));
                byteBuilder.append(b);
            }

            System.out.println(hexBuilder.length());
            System.out.println(byteBuilder.length());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
