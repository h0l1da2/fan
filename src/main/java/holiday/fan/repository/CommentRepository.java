package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.fanletter.*;
import holiday.fan.domain.members.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static holiday.fan.domain.fanletter.QBoardComment.*;
import static holiday.fan.domain.fanletter.QComment.*;

@Slf4j
@Repository
@Transactional
public class CommentRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public CommentRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    /**
     * CommentMember
     * Comment
     * BoardComment
     */
    public Comment save(Long memberId, Long boardId, String content) {
        Member member = em.find(Member.class, memberId);
        CommentMember commentMember = new CommentMember(member);
        em.persist(commentMember);

        Comment comment = new Comment(content, commentMember);
        comment.whoIsWriter(commentMember);
        em.persist(comment);

        FanBoard fanBoard = em.find(FanBoard.class, boardId);
        BoardComment boardComment = new BoardComment(fanBoard, comment);
        em.persist(boardComment);

        em.close();

        return comment;
    }

    /**
     * board id로 id가 같은 bc 다 데려옴
     * 데려온 bc들의 commentId 데려옴
     * commentId들 다 데려옴 List
     */

    public List<Comment> findAllById(Long boardId) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(boardComment.fanBoard.id.like(String.valueOf(boardId)));

        List<BoardComment> boardComments =
                query.select(boardComment)
                .from(boardComment)
                .where(builder)
                        .fetch();

        List<Long> commentIds = boardComments.stream()
                .map(b -> b.getComment().getId())
                .collect(Collectors.toList());

        return query.select(comment)
                .from(comment)
                .where(comment.id.in(commentIds))
                .fetchAll().fetch();
    }

    public void update(Long id, String content) {
        Comment findComment = em.find(Comment.class, id);
        findComment.updateComment(content);
        em.close();
    }

    public void delete(Long id, CommentRemover remover) {
        Comment comment = em.find(Comment.class, id);
        comment.whoRemoved(remover);
    }


}
