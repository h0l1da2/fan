package holiday.fan.web.controller;
import holiday.fan.domain.dto.MemberDto;
import holiday.fan.domain.dto.MemberListDto;
import holiday.fan.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/members")
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public Page<MemberListDto> memberList(Pageable pageable) {
        return memberService.userList(pageable);
    }

    @GetMapping("/{id}")
    public MemberDto member(@PathVariable("id")Long id) {
        return memberService.readForAdmin(id);
    }

}
