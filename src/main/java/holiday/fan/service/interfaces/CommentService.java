package holiday.fan.service.interfaces;

import holiday.fan.domain.fanletter.BoardComment;
import holiday.fan.domain.fanletter.Comment;
import holiday.fan.domain.fanletter.CommentRemover;

import java.util.List;

public interface CommentService {
    /**
     * 댓글쓰기, 읽기, 수정, 삭제
     */
    Comment write(Long memberId, Long boardId, String content); //쓰기
    List<Comment> read(Long id); //읽기
    void modify(Long id, String content); //수정
    void delete(Long id, CommentRemover commentRemover); //삭제
}
