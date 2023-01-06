package holiday.fan.domain.members;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class MemberStop {

    @Id @GeneratedValue
    private Long id;

    private String cause;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public MemberStop() {
    }

    public MemberStop(String cause, Admin admin) {
        this.cause = cause;
        this.admin = admin;
    }
}
