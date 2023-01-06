package holiday.fan.domain.members;

import holiday.fan.domain.dto.JoinAdminDto;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity @Getter
public class Admin {

    @Id @GeneratedValue
    private Long id;

    private String adminId;
    private String password;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    protected Admin() {
    }

    public Admin(String adminId, String password, String name, Role role, PasswordEncoder passwordEncoder) {
        this.adminId = adminId;
        this.password = passwordEncoder.encode(password);
        this.name = name;
        this.role = role;
    }

    public void modifyInfoDto(JoinAdminDto JoinAdminDto) {
        this.password = JoinAdminDto.getPassword();
        this.name = JoinAdminDto.getName();
    }

    public void modifyInfoAdmin(Admin admin) {
        this.password = admin.getPassword();
        this.name = admin.getName();
    }


}
