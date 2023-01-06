package holiday.fan;

import holiday.fan.config.DatabaseConfig;
import holiday.fan.config.WebConfig;
import holiday.fan.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({DatabaseConfig.class, SecurityConfig.class, WebConfig.class})
@SpringBootApplication(scanBasePackages = "holiday.fan.web")
public class FanApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanApplication.class, args);
	}

}
