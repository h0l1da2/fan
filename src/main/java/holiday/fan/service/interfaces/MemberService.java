package holiday.fan.service.interfaces;

import holiday.fan.domain.dto.*;
import holiday.fan.domain.members.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    /**
     * 가입, 로그인, 정보수정, 탈퇴
     */

//    Admin addAdmin(Admin admin); //어드민 추가
    String join(MemberDto MemberDto); //가입
    MemberDto readForUser(Long id); //유저용 정보수정
    MemberDto readForAdmin(Long id); //어드민용 정보보기
    Member findDuplicationId(String id); //아이디 중복 확인
    Member loginLog(LoginMemberDto loginMemberDto); //로그인 로그 남기기
    void modify(Long id, ModifyMemberDto modifyMemberDto); //정보 수정
    void delete(Long id); //탈퇴
    Member findPassword(String userId); //아이디로 찾기(예:비밀번호)

    void passwordUpdate(Long id, String password);

    /**
     * email이 일치하면(존재하면)
     * 그 email로 인증번호를 보내서
     * 맞을 경우, id를 알려준다
     */
    Member findByEmail(String email); //email로 아이디 찾기

    JoinAdminDto saveAdmin(JoinAdminDto JoinAdminDto);
    Page<MemberListDto> RemoveUserList(Pageable pageable); //모든 회원 불러오기

    Page<MemberListDto> userList(Pageable pageable);
    JoinAdminDto loginAdmin(JoinAdminDto JoinAdminDto);
    JoinAdminDto adminInfo(Long id);
    void adminInfoModify(Long adminId, JoinAdminDto JoinAdminDto);
}
