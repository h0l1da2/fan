package holiday.fan.config;

import holiday.fan.service.EmailServiceImpl;
import holiday.fan.service.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
@RequiredArgsConstructor
public class WebConfig {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl(javaMailSender, springTemplateEngine);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> {
            p.setOneIndexedParameters(true);	// 1부터 시작
            p.setMaxPageSize(10);				// size=10
        };
    }
}
