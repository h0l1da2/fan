package holiday.fan.domain.members;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

@Entity @Getter
public class MemberLog {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private MailConfirm mailConfirm;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime removeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_stop_id")
    private MemberStop memberStop;

    public MemberLog() {

    }

    public MemberLog(MailConfirm mailConfirm, Member member) {
        this.mailConfirm = mailConfirm;
        this.member = member;
    }

    public void loginLog() {
        this.lastLogin = now();
    }
}
