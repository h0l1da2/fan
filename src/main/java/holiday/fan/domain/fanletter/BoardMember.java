package holiday.fan.domain.fanletter;

import holiday.fan.domain.members.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class BoardMember {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    protected BoardMember() {

    }

    public BoardMember(Member member) {
        this.member = member;
    }
}
