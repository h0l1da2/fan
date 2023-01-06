package holiday.fan.web.controller;

import holiday.fan.domain.dto.MemberDto;
import holiday.fan.domain.dto.EmailDto;
import holiday.fan.domain.dto.ModifyMemberDto;
import holiday.fan.domain.members.Member;
import holiday.fan.domain.dto.LoginMemberDto;
import holiday.fan.service.interfaces.EmailService;
import holiday.fan.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    /**
     * 회원가입, 로그인
     */

    private final MemberService memberService;
    private final EmailService emailService;
    private final PasswordEncoder encoder;


    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@RequestBody MemberDto MemberDto) {
        String join = memberService.join(MemberDto);
        /**
         * 일단 멤버 저장 후, 서비스에서 메일 키 생성하고 등록
         * 조인(DB저장) 완료되면 아이디 메일 키로 저장해서 보냄
         * 메일 키가 있어야 인증 완료
         */

        if(join.equals("가입 완료")) {
            log.info("새로운 유저가 가입했습니다 id = {}", MemberDto.getUserId());
        }
        return join;
    }

    @PostMapping("/join/mailConfirm")
    public String emailSend(@RequestBody EmailDto emailDto) {
        return emailService.joinMailSend(emailDto.getEmail());
    }

    @GetMapping("/login")
    public String login() {
        log.info("로그인 GET");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginMemberDto loginMemberDto,
                        HttpServletResponse response, HttpServletRequest request) {
        /**
         * 시큐리티에서 로그인 확인
         * 메일 키 있어야 로그인 성공 가능
         * 성공했다면 로그인 로그 등록
         */
        Member member = memberService.loginLog(loginMemberDto);

        //쿠키 만들기, 세션에 id 정보 집어 넣기
        createCookie(loginMemberDto, response);
        addSession(member, request);

        log.info("해당 아이디 로그인 완료 = {}", loginMemberDto.getUserId());
        return "로그인 완료";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        removeCookie(response);
        session.invalidate();
        return "로그아웃 완료";
    }

    @GetMapping("/info")
    public MemberDto info(HttpSession session) {
        Long id = getMemberId(session);
        return memberService.readForUser(id);
    }

    @GetMapping("/info/update")
    public String updateMember() {
        return "수정화면";
    }

    @PostMapping("/info/update")
    public String updateMember(@RequestBody ModifyMemberDto modifyMemberDto, HttpSession session) {
        Long id = getMemberId(session);
        memberService.modify(id, modifyMemberDto);
        log.info("{} 유저 정보 수정", id);
        return modifyMemberDto.getNickname();
    }

    @GetMapping("/info/remove")
    public String removeMember() {
        return "삭제 화면";
    }

    @PostMapping("/info/remove")
    public String removeMember(HttpSession session) {
        Long id = getMemberId(session);
        memberService.delete(id);
        return "삭제 화면";
    }

    @GetMapping("/findId")
    public String findId() {
        return "아이디 찾기 화면";
    }

    // 이메일 보내고 인증번호 ㄱㄱ 하고 아이디 보내기

    @PostMapping("/findId")
    public String findId(@RequestBody EmailDto emailDto) { //메일은 @가 들어가서 Dto로 전달해줘야하는듯

        Member findMember = memberService.findByEmail(emailDto.getEmail());
        if(findMember == null) {
            return "해당 이메일 정보는 없음";
        }
        emailService.idMailSend(emailDto.getEmail(), findMember.getUserId());
        return "해당 이메일로 찾은 아이디는 ? : "+findMember.getUserId();
    }
    @GetMapping("/findPwd")
    public String findPassword() {
        return "비밀번호 찾기 화면";
    }

    @PostMapping("/findPwd")
    public String findPassword(@RequestParam String userId) {
        Member findMember = memberService.findPassword(userId);
        if(findMember == null) {
            return "해당 아이디 없음";
        }
        /**
         * 비밀번호 랜덤 생성
         * 생성 비번 멤버에 업데이트
         * 생성 비번 메일에 보냄
         */
        String randomPwd = emailService.passwordMailSend(findMember.getEmail());
        memberService.passwordUpdate(findMember.getId(), randomPwd);
        return "해당 아이디 비밀번호는?";
    }

    private void addSession(Member member, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id", member.getId());
    }

    private void createCookie(LoginMemberDto loginMemberDto, HttpServletResponse response) {
        Cookie cookie = new Cookie("id", loginMemberDto.getUserId());
        response.addCookie(cookie);
    }

    private Long getMemberId(HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        return id;
    }

    private void removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
