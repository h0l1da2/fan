package holiday.fan.web.controller;

import holiday.fan.domain.dto.JoinAdminDto;
import holiday.fan.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminInfoController {

    private final MemberService memberService;

    @GetMapping
    public String main() {
        return "메인 페이지";
    }

    @GetMapping("/join")
    public String join() {
        return "회원가입 페이지";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinAdminDto JoinAdminDto) {
        JoinAdminDto joinAdmin = memberService.saveAdmin(JoinAdminDto);
        if (joinAdmin == null) {
            return "아이디 중복";
        }
        log.info("어드민 가입 완료 = {}", JoinAdminDto.getAdminId());
        return "가입 완료";
    }

    @GetMapping("/login")
    public String login() {
        return "로그인 페이지";
    }

    @PostMapping("/login")
    public String login(JoinAdminDto JoinAdminDto, HttpServletRequest request) {
        JoinAdminDto loginAdmin = memberService.loginAdmin(JoinAdminDto);
        if (loginAdmin.getId() != null) {
            getSession(request, loginAdmin);
            return "로그인 완료";
        }
        return "로그인 문제 있음";
    }

    @GetMapping("/info")
    public JoinAdminDto info(HttpSession session) {
        Long id = getAdminId(session);
        if (id == null) {
            return null; // 예외 발생시켜야할듯
        }
        return memberService.adminInfo(id);
    }

    @PostMapping("/modify")
    public String modifyInfo(@RequestBody JoinAdminDto JoinAdminDto, HttpSession session) {

        Long adminId = getAdminId(session);
        memberService.adminInfoModify(adminId, JoinAdminDto);
        return "수정 성공~";
    }


    private void getSession(HttpServletRequest request, JoinAdminDto loginAdmin) {
        HttpSession session = request.getSession();
        session.setAttribute("adminId", loginAdmin.getId());
    }

    private static Long getAdminId(HttpSession session) {
        Long id = (Long) session.getAttribute("adminId");
        return id;
    }
}
