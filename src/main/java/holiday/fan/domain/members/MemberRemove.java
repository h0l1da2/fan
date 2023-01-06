package holiday.fan.domain.members;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class MemberRemove {

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

    protected MemberRemove() {

    }

    public MemberRemove(Admin admin) {
        this.admin = admin;
    }

    public MemberRemove(Member member) {
        this.member = member;
    }
}
