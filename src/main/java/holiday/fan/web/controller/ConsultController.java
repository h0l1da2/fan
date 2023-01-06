package holiday.fan.web.controller;

import holiday.fan.domain.consulting.Consult;
import holiday.fan.domain.dto.ConsultDto;
import holiday.fan.service.interfaces.ConsultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
//@Controller
@RestController
@RequestMapping("/consult")
@RequiredArgsConstructor
public class ConsultController {

    private final ConsultService consultService;

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long consultId) {
        Consult read = consultService.read(consultId);
        return read.getStatus().toString();
    }

    @GetMapping("/write")
    public String write() {
        return "상담쓰기창";
    }

    @PostMapping("/write")
    public String write(@RequestBody ConsultDto consultDto, HttpSession session) {
        Long userId = getMemberId(session);
        return consultService.reservation(addConsult(consultDto), userId);
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") Long consultId, HttpSession session) {
        Long userId = getMemberId(session);
        consultService.cancel(consultId, userId);
        return "삭제 완료!";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long consultId) {
        return "수정 ㄱㄱ?";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long consultId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        consultService.modify(consultId, date);
        return "수정 완료";
    }

    /**
     * list 에서도 시간 지나면 컴플리트인거 바뀌어야함 (isComplete)
     * 서비스에서 바꾸는 게 맞는 거 같음
     */

    @GetMapping
    public Page<ConsultDto> list(Pageable pageable, HttpSession session) {
        //페이지에선 modify 데이트를 보여줘야함
        Long id = getMemberId(session);
        return consultService.myConsults(id, pageable);
    }

    @GetMapping("/complete")
    public Page<ConsultDto> completeList(Pageable pageable, HttpSession session) {
        Long id = getMemberId(session);
        return consultService.completeConsults(id, pageable);
    }

    private static Long getMemberId(HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        return id;
    }

    private Consult addConsult(ConsultDto consultDto) {
        return new Consult(consultDto.getStatus(), consultDto.getReservationDate());
    }

}
