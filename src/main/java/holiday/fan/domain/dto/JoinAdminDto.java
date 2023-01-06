package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.members.Authority;
import holiday.fan.domain.members.RoleName;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class JoinAdminDto {

    private Long id;
    private String adminId;
    private String password;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private RoleName RoleName;
    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    protected JoinAdminDto() {
    }

    public JoinAdminDto(Long id) {
        this.id = id;
    }

    public JoinAdminDto(String adminId, String password, String name, RoleName roleName, Authority authority) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.RoleName = roleName;
        this.authority = authority;
    }

    public JoinAdminDto(Long id, String adminId, String name, RoleName roleName, Authority authority) {
        this.id = id;
        this.adminId = adminId;
        this.name = name;
        this.RoleName = roleName;
        this.authority = authority;
    }

    @QueryProjection
    public JoinAdminDto(Long id, String adminId, String password, String name, RoleName roleName, Authority authority) {
        this.id = id;
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.RoleName = roleName;
        this.authority = authority;
    }
}
