package holiday.fan.web.controller;

import holiday.fan.domain.dto.FanBoardDto;
import holiday.fan.domain.fanletter.BoardSearchCond;
import holiday.fan.domain.fanletter.BoardSearchType;
import holiday.fan.domain.fanletter.FanBoard;
import holiday.fan.domain.file.File;
import holiday.fan.domain.file.FileInfo;
import holiday.fan.service.interfaces.FanBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
//@Controller
@RequestMapping("/fanboard")
public class FanBoardController {

    private final FanBoardService fanBoardService;

    @GetMapping
    public Page<FanBoardDto> list(Pageable pageable) {
        return fanBoardService.boardList(pageable);
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long boardId, Model model) {
        FanBoard fanBoard = fanBoardService.read(boardId);
        model.addAttribute("fanBoard", fanBoard);
        return "읽기~"+fanBoard.getTitle();
    }

    @GetMapping("/write")
    public String write() {
        return "글쓰기 페이지";
    }

    @PostMapping("/write")
    public String write(@RequestBody FanBoardDto fanBoardDto, HttpSession session) {
        Long id = getMemberId(session);

        if(fanBoardDto.getType()==null) {
            fanBoardService.write(id, addFanBoard(fanBoardDto));
        } else {
            fanBoardService.writeWithFile(id, addBoardWithFile(fanBoardDto));
        }
        return "업로드 완료 ";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long boardId) {
        return "글쓰기 수정창";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long boardId, @RequestBody FanBoardDto fanBoardDto) {
        if(fanBoardDto.getType()==null) {
            fanBoardService.modify(boardId, addFanBoard(fanBoardDto));
        } else {
            fanBoardService.modifyWithFile(boardId, addBoardWithFile(fanBoardDto));
        }

        return "ㅋㅋ";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long boardId) {
        return "삭제창";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long boardId, HttpSession session) {
        Long userId = getMemberId(session);
        fanBoardService.delete(boardId, userId);

        return "삭제";
    }

    @GetMapping("/search")
    public Page<FanBoardDto> searchList(@RequestParam(required = false)String word,
                                        @RequestParam(required = false)String nickname,
                                        @RequestParam BoardSearchType boardSearchType,
                                        Pageable pageable) {
        BoardSearchCond boardSearchCond = new BoardSearchCond(word, nickname, boardSearchType);
        return fanBoardService.boardSearchList(boardSearchCond, pageable);
    }

    private static Long getMemberId(HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        return id;
    }

    private FanBoard addBoardWithFile(FanBoardDto fanBoardDto) {
        return new FanBoard(fanBoardDto,
                new FileInfo(fanBoardDto.getIsWhere(),
                        new File(fanBoardDto.getName(), fanBoardDto.getPath(), fanBoardDto.getType(), fanBoardDto.getSize())
                        ));
    }

    private FanBoard addFanBoard(FanBoardDto fanBoardDto) {
        return new FanBoard(fanBoardDto);
    }
}
