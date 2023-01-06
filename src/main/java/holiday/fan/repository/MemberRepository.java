package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.members.*;
import holiday.fan.repository.jpa.MemberJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static holiday.fan.domain.members.QMember.*;

@Slf4j
@Repository
@Transactional
public class MemberRepository {

    private EntityManager em;
    private final JPAQueryFactory query;
    private final MemberJpaRepository memberJpaRepository;

    public MemberRepository(EntityManager em, MemberJpaRepository memberJpaRepository) {
        this.query = new JPAQueryFactory(em);
        this.em = em;
        this.memberJpaRepository = memberJpaRepository;
    }

    public Admin saveAdmin(Admin admin) {
        em.persist(admin);
        em.close();
        return admin;
    }

    public void update(Long id, Member member) {
        Member findMember = findByIdMember(id);
        findMember.changeMemberInfo(member);
    }

    public void passwordUpdate(Long id, String password) {
        Member findMember = findByIdMember(id);
        findMember.passwordUpdate(password);
    }

    public Member save(Member member) {
        em.persist(member);

        em.persist(new MemberLog(MailConfirm.OK, member));
        em.close();
        return member;
    }

    public Member findByIdMember(Long id) {
        return memberJpaRepository.findById(id).orElse(null);
    }

    public Member findByUserId(String userId) {
        return memberJpaRepository.findByUserId(userId).orElse(null);
    }

    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email).orElse(null);
    }

    public Long count() {
        return memberJpaRepository.count();
    }

    public Page<Member> findAllRemoveUsers(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.memberRemove.isNotNull());

        return getMemberList(pageable, builder);
    }

    public Page<Member> findAllUsers(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.memberRemove.isNull());

        return getMemberList(pageable, builder);
    }

    private Page<Member> getMemberList(Pageable pageable, BooleanBuilder builder) {
        List<Member> resultList = query.select(Projections.constructor(Member.class,
                        member.id, member.userId, member.name, member.nickname, member.email))
                .from(member)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(member.count())
                .from(member)
                .fetchFirst();

        return new PageImpl<>(resultList, pageable, total);
    }

    public void saveMemberLog(Member member) {

        query.select(QMemberLog.memberLog)
                .from(QMemberLog.memberLog)
                .where(QMemberLog.memberLog.member.eq(member))
                .fetchFirst()
                .loginLog();

        log.info("마지막 로그인 = {}", LocalDateTime.now());
    }

    /**
     * 탈퇴 과정
     * Long id 받아서 member 조회
     * member의 String userId = removed_'id' 변경
     * member_remove 추가
     * if(member_remove != null) 회원 삭제 상태
     */
    public void delete(Long memberId) {
        Member member = memberJpaRepository.findById(memberId).orElseThrow();
        MemberRemove memberRemove = new MemberRemove(member);
        em.persist(memberRemove);
        member.wantDelete(memberRemove);
        em.close();
    }

    public Admin findByIdAdmin(Long id) {
        Admin admin = em.find(Admin.class, id);
        em.close();
        return admin;
    }

    public Admin findByAdminForAdminId(String adminId) {
        Admin admin = em.find(Admin.class, adminId);
        em.close();
        return admin;
    }

    public void updateAdmin(Admin admin) {
        Admin findAdmin = em.find(Admin.class, admin.getId());
        findAdmin.modifyInfoAdmin(admin);
        em.close();
    }
}
