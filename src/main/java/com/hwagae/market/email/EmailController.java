package com.hwagae.market.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody String user_email) {
        // user_email 값에서 'user_email='을 삭제하고, '%40'을 '@'로 변경하여 올바른 이메일 주소로 변환
        String userEmail = user_email.replace("user_email=", "").replace("%40", "@");

        // userEmail에는 사용자가 입력한 올바른 이메일 주소가 전달됨
        System.out.println("111111111111111111111111111 " + userEmail);

        String randomString = generateRandomString(8); // 8자리의 랜덤 문자열 생성
        System.out.println("222222222222222222222222222 " + userEmail);

        String subject = "[화개장터] 인증확인 메일입니다.";
        System.out.println("333333333333333333333333333 " + userEmail);

        String text = "인증번호" + randomString;
        System.out.println("4444444444444444444444444444 " + userEmail);

        emailService.sendEmail(userEmail, subject, text);
        System.out.println("5555555555555555555555555555 " + userEmail);
    }

    // 랜덤 문자열 생성 메서드는 EmailService 클래스에 구현하거나 별도의 Util 클래스에 구현할 수 있음
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }
}