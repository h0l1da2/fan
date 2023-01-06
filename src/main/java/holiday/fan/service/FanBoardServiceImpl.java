package holiday.fan.service;

import holiday.fan.domain.dto.FanBoardDto;
import holiday.fan.domain.fanletter.BoardRemover;
import holiday.fan.domain.fanletter.BoardSearchCond;
import holiday.fan.domain.fanletter.FanBoard;
import holiday.fan.domain.file.File;
import holiday.fan.domain.file.Type;
import holiday.fan.repository.FanBoardRepository;
import holiday.fan.service.interfaces.FanBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FanBoardServiceImpl implements FanBoardService {

    private final FanBoardRepository fanBoardRepository;

    @Override
    public FanBoard write(Long id, FanBoard fanBoard) {
        return fanBoardRepository.save(id, fanBoard);
    }

    @Override
    public FanBoard writeWithFile(Long id, FanBoard fanBoard) {
        return fanBoardRepository.saveWithFile(id, new File("asdf","asdf", Type.FILE,1234), fanBoard);
    }

    @Override
    public FanBoard read(Long boardId) {
        return fanBoardRepository.findById(boardId);
    }

    @Override
    public void modify(Long boardId, FanBoard fanBoard) {
        fanBoardRepository.update(boardId, fanBoard);
    }
    @Override
    public void modifyWithFile(Long boardId, FanBoard fanBoard) {
        fanBoardRepository.updateWithFile(boardId, fanBoard);
    }

    @Override
    public void delete(Long boardId, Long userId) {
        fanBoardRepository.delete(boardId, userId);
    }

    @Override
    public Page<FanBoardDto> boardList(Pageable pageable) {
        return fanBoardRepository.findAllForUser(pageable);
    }

    @Override
    public Page<FanBoardDto> boardSearchList(BoardSearchCond boardSearchCond, Pageable pageable) {
        log.info("서비스 ㄱㄱ");
        return fanBoardRepository.findAllSearch(boardSearchCond, pageable);
    }


}
