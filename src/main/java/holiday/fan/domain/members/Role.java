package holiday.fan.domain.members;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Role {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private RoleName role;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    protected Role() {
    }

    public Role(RoleName role, Authority authority) {
        this.role = role;
        this.authority = authority;
    }
}
