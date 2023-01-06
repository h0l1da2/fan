package holiday.fan.domain.mdmarket;

import holiday.fan.domain.members.Admin;
import holiday.fan.domain.members.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class MarketRemover {

    @Id @GeneratedValue
    private Long id;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime removeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    protected MarketRemover() {
    }

    public MarketRemover(Admin admin) {
        this.admin = admin;
    }

    public MarketRemover(Member member) {
        this.member = member;
    }

    public void adminRemoved(Admin admin) {
        this.admin = admin;
    }

    public void memberRemoved(Member member) {
        this.member = member;
    }

}
