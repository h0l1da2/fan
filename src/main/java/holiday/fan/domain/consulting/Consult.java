package holiday.fan.domain.consulting;

import holiday.fan.domain.members.Member;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity @Getter
public class Consult {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ConsultStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consult_remover_id")
    private ConsultRemover remover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Consult() {

    }

    public Consult(ConsultStatus status, Date reservationDate) {
        this.status = status;
        this.reservationDate = reservationDate;
        this.modifyDate = reservationDate;
    }

    public void consultMember(Member member) {
        this.member = member;
    }
    public void changeTime(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void wantCancel(ConsultStatus status, ConsultRemover remover) {
        this.status = status;
        this.remover = remover;
    }

    public void completeConsult(ConsultStatus status) {
        this.status = status;
    }
}
