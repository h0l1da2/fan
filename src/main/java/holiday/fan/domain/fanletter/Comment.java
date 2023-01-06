package holiday.fan.domain.fanletter;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String content;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_member_id")
    private CommentMember commentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_remover_id")
    private CommentRemover commentRemover;

    protected Comment() {

    }

    public Comment(String content, CommentMember commentMember) {
        this.content = content;
        this.commentMember = commentMember;
    }

    public void updateComment(String content) {
        this.content = content;
    }

    public void whoRemoved(CommentRemover commentRemover) {
        this.commentRemover = commentRemover;
    }

    public void whoIsWriter(CommentMember commentMember) {
        this.commentMember = commentMember;
    }
}
