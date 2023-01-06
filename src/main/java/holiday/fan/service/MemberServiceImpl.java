package holiday.fan.service;

import holiday.fan.domain.dto.*;
import holiday.fan.domain.mdmarket.Address;
import holiday.fan.domain.members.*;
import holiday.fan.repository.MemberRepository;
import holiday.fan.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    @Override
    public String join(MemberDto MemberDto) {

        Member findUserId = memberRepository.findByUserId(MemberDto.getUserId());
        Member findEmail = memberRepository.findByEmail(MemberDto.getEmail());
        if (findUserId != null) {
            return "중복 아이디";
        }
        if (findEmail != null) {
            return "중복 이메일";
        }

        Member member = new Member(MemberDto,
                new Address(MemberDto.getAddress(), MemberDto.getJibunAddress(), MemberDto.getDetail(), MemberDto.getZonecode()));

        memberRepository.save(member);
        return "가입 완료";
    }

    @Override
    public MemberDto readForUser(Long id) {
        Member findMember = memberRepository.findByIdMember(id);
        
        return new MemberDto(findMember.getUserId(), findMember.getName(),
                findMember.getNickname(), findMember.getEmail(),
                findMember.getAddress().getAddress(), findMember.getAddress().getJibunAddress(),
                findMember.getAddress().getDetail(), findMember.getAddress().getZonecode());
    }

    @Override
    public MemberDto readForAdmin(Long id) {
        Member member = memberRepository.findByIdMember(id);

        return new MemberDto(member.getId(), member.getUserId(), member.getName(),
                member.getNickname(), member.getEmail(),
                member.getAddress().getAddress(), member.getAddress().getJibunAddress(),
                member.getAddress().getDetail(), member.getAddress().getZonecode());
    }

    @Override
    public Member findDuplicationId(String id) {
        return memberRepository.findByUserId(id);
    }

    @Override
    public Member loginLog(LoginMemberDto loginMemberDto) {
        if(!loginCheckForMember(loginMemberDto, encoder)) {
            log.warn("로그인 실패");
        }
        Member loginMember = memberRepository.findByUserId(loginMemberDto.getUserId());
        memberRepository.saveMemberLog(loginMember);
        return loginMember;
    }

    @Override
    public void modify(Long id, ModifyMemberDto modifyMemberDto) {
        Member findMember = memberRepository.findByIdMember(id);
        memberRepository.update(id, nullCheckDto(modifyMemberDto, findMember));
    }

    @Override
    public void delete(Long id) {
        memberRepository.delete(id);
    }

    @Override
    public Member findPassword(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public void passwordUpdate(Long id, String password) {
        memberRepository.passwordUpdate(id, encoder.encode(password));
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public JoinAdminDto saveAdmin(JoinAdminDto JoinAdminDto) {
        Admin byAdminId = memberRepository.findByAdminForAdminId(JoinAdminDto.getAdminId());
        if (byAdminId != null) {
            return null;
        }
        memberRepository.saveAdmin(
                new Admin(JoinAdminDto.getAdminId(), JoinAdminDto.getPassword(), JoinAdminDto.getName(),
                        new Role(RoleName.ADMIN, Authority.SLAVE), encoder));
        return JoinAdminDto;
    }


    @Override
    public Page<MemberListDto> RemoveUserList(Pageable pageable) {
        return getMemberDtoList(memberRepository.findAllRemoveUsers(pageable));
    }

    @Override
    public Page<MemberListDto> userList(Pageable pageable) {
        return getMemberDtoList(memberRepository.findAllUsers(pageable));
    }

    @Override
    public JoinAdminDto loginAdmin(JoinAdminDto joinAdminDto) {
        Admin findAdmin = memberRepository.findByAdminForAdminId(joinAdminDto.getAdminId());
        if (findAdmin.getAdminId() == null) {
            return null;
        }
        boolean login = loginCheckForAdmin(joinAdminDto.getPassword(), findAdmin.getPassword(), encoder);
        if(login) {
            return new JoinAdminDto(findAdmin.getId());
        }


        return null;
    }

    @Override
    public JoinAdminDto adminInfo(Long id) {
        Admin findAdmin = memberRepository.findByIdAdmin(id);
        return new JoinAdminDto(findAdmin.getId(), findAdmin.getAdminId(), findAdmin.getName(),
                findAdmin.getRole().getRole(), findAdmin.getRole().getAuthority());
    }

    @Override
    public void adminInfoModify(Long adminId, JoinAdminDto joinAdminDto) {
        /**
         * 어드민 RoleName가 holiday여야지만 바꿀 수 있음
         */
        Admin admin = memberRepository.findByIdAdmin(adminId);
        if(!admin.getRole().getRole().equals(RoleName.ADMIN)) {
            //예외 발생
        }
        Admin modifyAdmin = memberRepository.findByAdminForAdminId(joinAdminDto.getAdminId());
        modifyAdmin.modifyInfoDto(joinAdminDtoNullCheck(joinAdminDto, modifyAdmin));
        memberRepository.updateAdmin(modifyAdmin);
    }


    private boolean loginCheckForMember(LoginMemberDto loginMemberDto, PasswordEncoder encoder) {
        try {
            Member findMember = findDuplicationId(loginMemberDto.getUserId());
            log.info("해당 아이디가 로그인 시도 = {}", findMember.getUserId());
            return encoder.matches(loginMemberDto.getPassword(), findMember.getPassword());
        } catch (NoSuchElementException e) {
            return false;
        }
        /**
         * 시큐리티에 로그인 권한 넘기기
         */
    }

    private boolean loginCheckForAdmin(String infoAdminPwd, String loginPwd, PasswordEncoder encoder) {
        try {
            return encoder.matches(infoAdminPwd, loginPwd);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private Member nullCheckDto(ModifyMemberDto modifyMemberDto, Member findMember) {
        if(modifyMemberDto.getName() == null) {
            modifyMemberDto.setName(findMember.getName());
        }
        if(modifyMemberDto.getPassword() == null) {
            modifyMemberDto.setPassword(findMember.getPassword());
        } else {
            //패스워드가 널이 아니면 비밀번호 가져와서 암호화
            modifyMemberDto.setPassword(encoder.encode(modifyMemberDto.getPassword()));
        }
        if(modifyMemberDto.getNickname() == null) {
            modifyMemberDto.setNickname(findMember.getNickname());
        }
        if(modifyMemberDto.getEmail() == null) {
            modifyMemberDto.setEmail(findMember.getEmail());
        }
        if(modifyMemberDto.getAddress() == null) {
            modifyMemberDto.setAddress(findMember.getAddress().getAddress());
        }
        if(modifyMemberDto.getJibunAddress() == null) {
            modifyMemberDto.setJibunAddress(findMember.getAddress().getJibunAddress());
        }
        if(modifyMemberDto.getDetail() == null) {
            modifyMemberDto.setDetail(findMember.getAddress().getDetail());
        }
        if(modifyMemberDto.getZonecode() == null) {
            modifyMemberDto.setZonecode(findMember.getAddress().getZonecode());
        }

        return findMember;
    }

    private JoinAdminDto joinAdminDtoNullCheck(JoinAdminDto JoinAdminDto, Admin modifyAdmin) {
        if (JoinAdminDto.getPassword() == null) {
            JoinAdminDto.setPassword(encoder.encode(modifyAdmin.getPassword()));
        }
        if (JoinAdminDto.getName() == null) {
            JoinAdminDto.setName(modifyAdmin.getName());
        }
        return JoinAdminDto;
    }


    private Page<MemberListDto> getMemberDtoList(Page<Member> memberList) {
        return memberList.map(
                m -> MemberListDto.builder()
                        .id(m.getId())
                        .userId(m.getUserId())
                        .name(m.getName())
                        .nickname(m.getNickname())
                        .email(m.getEmail())
                        .build()

        );
    }
}
