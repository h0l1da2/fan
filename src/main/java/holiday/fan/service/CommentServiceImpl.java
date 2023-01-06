package holiday.fan.service;

import holiday.fan.domain.fanletter.Comment;
import holiday.fan.domain.fanletter.CommentRemover;
import holiday.fan.repository.CommentRepository;
import holiday.fan.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment write(Long memberId, Long boardId, String content) {
        return commentRepository.save(memberId, boardId, content);
    }

    @Override
    public List<Comment> read(Long id) {
        return commentRepository.findAllById(id);
    }

    @Override
    public void modify(Long id, String content) {
        commentRepository.update(id, content);
    }

    @Override
    public void delete(Long id, CommentRemover commentRemover) {
        commentRepository.delete(id, commentRemover);
    }
}
