package holiday.fan.domain.mdmarket;

import holiday.fan.domain.members.Admin;
import holiday.fan.domain.members.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class OrderRemover {

    @Id @GeneratedValue
    private Long id;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime removeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public OrderRemover() {
    }

    public void removerIsMember(Member member) {
        this.member = member;
    }

    public void removerIsAdmin(Admin admin) {
        this.admin = admin;
    }
}
