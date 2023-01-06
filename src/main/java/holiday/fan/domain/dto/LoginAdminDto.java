package holiday.fan.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginAdminDto {

    private String adminId;
    private String password;
}
