package holiday.fan.domain.members;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class MailHandler {
    private JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper helper;

    public MailHandler(JavaMailSender sender) throws MessagingException {
        this.sender = sender;
        message = sender.createMimeMessage();
        helper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void fromWho(String fromAddress) throws MessagingException {
        helper.setFrom(fromAddress);
    }

    public void toWho(String toAddress) throws MessagingException {
        helper.setTo(toAddress);
    }

    public void titleEmail(String title) throws MessagingException {
        helper.setSubject(title);
    }

    public void contentHow(String content, boolean useHtml) throws MessagingException {
        helper.setText(content, useHtml);
    }

    public void fileWhat(String fileName, String path) throws IOException, MessagingException {
        File file = new ClassPathResource(path).getFile();
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        helper.addAttachment(fileName, fileSystemResource);
    }

    public void imageWhat(String imageName, String path) throws IOException, MessagingException {
        File file = new ClassPathResource(path).getFile();
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        helper.addAttachment(imageName, fileSystemResource);
    }

    public void send() {
        try {
            sender.send(message);
        } catch (MailException e) {
            log.warn("메일 전송 에러 = {}", e);
        }
    }
}
