package holiday.fan.domain.fanletter;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class BoardComment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fan_board_id")
    private FanBoard fanBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    protected BoardComment() {
    }

    public BoardComment(FanBoard fanBoard, Comment comment) {
        this.fanBoard = fanBoard;
        this.comment = comment;
    }
}
