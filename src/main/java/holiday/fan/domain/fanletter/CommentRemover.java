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
public class CommentRemover {

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


    protected CommentRemover() {

    }

    public CommentRemover(Member member, Admin admin) {
        this.member = member;
    }

    public CommentRemover(Admin admin) {
        this.admin = admin;
    }

    public CommentRemover(Member member) {
        this.member = member;
    }
}
