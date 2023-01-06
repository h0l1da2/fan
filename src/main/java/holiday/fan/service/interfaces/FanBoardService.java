package holiday.fan.service.interfaces;

import holiday.fan.domain.dto.FanBoardDto;
import holiday.fan.domain.fanletter.BoardRemover;
import holiday.fan.domain.fanletter.BoardSearchCond;
import holiday.fan.domain.fanletter.FanBoard;
import holiday.fan.domain.file.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FanBoardService {
    /**
     * 글쓰기, 읽기, 수정, 삭제, 검색, 목록
     */
    FanBoard write(Long id, FanBoard fanBoard); //쓰기
    FanBoard writeWithFile(Long id, FanBoard fanBoard); //이미지 추가
    FanBoard read(Long boardId); //읽기
    void modify(Long boardId, FanBoard fanBoard); //수정
    void modifyWithFile(Long boardId, FanBoard fanBoard); //수정
    void delete(Long boardId, Long userId); //삭제
    Page<FanBoardDto> boardList(Pageable pageable); //목록

    Page<FanBoardDto> boardSearchList(BoardSearchCond boardSearchCond, Pageable pageable); //검색
}
