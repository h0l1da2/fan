package holiday.fan.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmailDto {

    @Email
    @NotEmpty(message = "이메일을 입력하세요")
    public String email;
}
