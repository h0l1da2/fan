package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.consulting.Consult;
import holiday.fan.domain.consulting.ConsultRemover;
import holiday.fan.domain.consulting.ConsultStatus;
import holiday.fan.domain.consulting.QConsult;
import holiday.fan.domain.dto.ConsultDto;
import holiday.fan.domain.members.Member;
import holiday.fan.repository.jpa.ConsultJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;

import static holiday.fan.domain.consulting.QConsult.*;

@Slf4j
@Repository
@Transactional
public class ConsultRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final ConsultJpaRepository consultJpaRepository;

    public ConsultRepository(EntityManager em, ConsultJpaRepository consultJpaRepository) {
        this.em = em;
        query = new JPAQueryFactory(em);
        this.consultJpaRepository = consultJpaRepository;
    }

    public String save(Consult consult, Long userId) {

        Member member = em.find(Member.class, userId);
        consult.consultMember(member);

        BooleanBuilder builder = new BooleanBuilder();
        // 해당 멤버의 상담 내역을 가지고 오는데,
        // 현재 status가 waiting 인 것이 있으면 예외

        builder.and(QConsult.consult.member.eq(consult.getMember()))
               .and(QConsult.consult.status.loe(ConsultStatus.WAITING));

        List<Consult> getList =
                query.select(QConsult.consult)
                .from(QConsult.consult)
                .where(builder)
                .fetch();

        if (getList.size() != 0) {
            return "중복 예약은 어렵습니다";
        }
        consultJpaRepository.save(consult);
        return "예약 완료";
    }

    public Page<ConsultDto> findAll(Long id, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(consult.member.id.eq(id));

        return getConsultList(pageable, builder);
    }

    public Page<ConsultDto> findAllComplete(Long id, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(consult.member.id.eq(id))
                .and(consult.status.eq(ConsultStatus.COMPLETE));

        return getConsultList(pageable, builder);
    }

    public Consult findById(Long id) {
        return consultJpaRepository.findById(id).orElseThrow();
    }

    public Page<ConsultDto> findAllByDate(Long id, Date date, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(consult.member.id.like(String.valueOf(id)))
                .and(consult.reservationDate.after(date));

        return getConsultList(pageable, builder);
    }

    public void delete(Long consultId, Long userId) {
        Consult consult = consultJpaRepository.findById(consultId).orElseThrow();
        ConsultRemover consultRemover = new ConsultRemover(em.find(Member.class, userId));
        em.persist(consultRemover);
        consult.wantCancel(ConsultStatus.CANCEL, consultRemover);
    }

    public void updateDate(Long id, Date date) {
        Consult consult = consultJpaRepository.findById(id).orElseThrow();
        consult.changeTime(date);
    }

    public void updateStatus(Long id, ConsultStatus status) {
        Consult consult = consultJpaRepository.findById(id).orElseThrow();
        consult.completeConsult(status);
    }

    private Page<ConsultDto> getConsultList(Pageable pageable, BooleanBuilder builder) {
        List<ConsultDto> resultList = query.select(Projections.constructor(ConsultDto.class,
                        consult.id, consult.status, consult.reservationDate, consult.modifyDate))
                .from(consult)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(consult.count())
                .from(consult)
                .fetchOne();
        return new PageImpl<>(resultList, pageable, total);
    }
}
