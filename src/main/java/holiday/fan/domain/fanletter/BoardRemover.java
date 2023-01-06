package holiday.fan.domain.fanletter;

import holiday.fan.domain.members.Admin;
import holiday.fan.domain.members.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class BoardRemover {

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

    protected BoardRemover() {
    }

    public BoardRemover(Member member) {
        this.member = member;
    }

    public BoardRemover(Admin admin) {
        this.admin = admin;
    }
}
