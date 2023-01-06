package holiday.fan.domain.fanletter;

import holiday.fan.domain.members.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class CommentMember {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected CommentMember() {

    }

    public CommentMember(Member member) {
        this.member = member;
    }
}
