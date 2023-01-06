package holiday.fan.service;

import holiday.fan.service.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import  org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine; //타임리프를 사용하기 위한 객체
    private final String fromEmail ="bengi0@naver.com";
    private MimeMessage message;
    private String title;
    private String randomCode;
    private String randomPwd;


    @Override
    public MimeMessage joinMailForm(String toEmail) {
        createCode();
        title = "HOLIDAY FAN PAGE 가입 인증";

        try {
            message = mailSender.createMimeMessage();
            message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
            message.setSubject(title);
            message.setFrom(fromEmail);
            message.setText(contextJoin(randomCode), "UTF-8", "html");
        } catch (MessagingException e) {
            log.error("가입 이메일 전송 오류 = {}", e);
        }

        return message;
    }

    @Override
    public MimeMessage passwordMailForm(String toEmail) {
        createPassword();
        title = "HOLIDAY FAN PAGE 임시 비밀번호";
        try {
            message = mailSender.createMimeMessage();
            message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
            message.setSubject(title);
            message.setFrom(fromEmail);
            message.setText(contextPwd(randomPwd), "UTF-8", "html");
        } catch (MessagingException e) {
            log.error("패스워드 이메일 전송 오류 = {}", e);
        }
        return message;
    }

    @Override
    public MimeMessage idMailForm(String toEmail, String id) {
        title = "HOLIDAY FAN PAGE 아이디";
        try {
            message = mailSender.createMimeMessage();
            message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
            message.setSubject(title);
            message.setFrom(fromEmail);
            message.setText(contextId(id), "UTF-8", "html");
        } catch (MessagingException e) {
            log.error("아이디 이메일 전송 오류 = {}", e);
        }
        return message;
    }

    @Override
    public String joinMailSend(String toEmail) {
        MimeMessage message = joinMailForm(toEmail);
        mailSender.send(message);
        return randomCode;
    }

    @Override
    public String idMailSend(String toEmail,String id) {
        MimeMessage message = idMailForm(toEmail, id);
        mailSender.send(message);
        return id;
    }

    @Override
    public String passwordMailSend(String toEmail) {
        message = passwordMailForm(toEmail);
        mailSender.send(message);
        return randomPwd;
    }

    //타임 리프 context설정

    @Override
    public String contextJoin(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("joinmail", context); // mail.html을 보낸다
    }
    @Override
    public String contextPwd(String password) {
        Context context = new Context();
        context.setVariable("password", password);
        return templateEngine.process("pwdmail", context); // mail.html을 보낸다
    }

    @Override
    public String contextId(String id) {
        Context context = new Context();
        context.setVariable("id", id);
        return templateEngine.process("idmail", context); // mail.html을 보낸다
    }

    @Override
    public void createCode() {
        int min = 1000;
        int max = 9999;
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        int code = random.nextInt(max-min)+min;

        randomCode = buffer.append(code).toString();
    }

    @Override
    public void createPassword() {
        int offset = 48; // 0
        int limit = 122; // z
        int pwdLength = 10;
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();
        randomPwd =
                random.ints(offset, limit+1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // 0 ~9 || A ~ z
                .limit(pwdLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) //StringBuilder 객체 생성
                .toString();
    }

}
