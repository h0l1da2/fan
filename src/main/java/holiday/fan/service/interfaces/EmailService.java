package holiday.fan.service.interfaces;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    MimeMessage joinMailForm(String toEmail);
    MimeMessage passwordMailForm(String toEmail);
    MimeMessage idMailForm(String toEmail, String id);
    String passwordMailSend(String toEmail);
    String joinMailSend(String toEmail);
    String idMailSend(String toEmail, String id);
    String contextJoin(String code);
    String contextPwd(String code);
    String contextId(String id);
    void createCode();
    void createPassword();
}
