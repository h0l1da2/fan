package holiday.fan.service.interfaces;

import holiday.fan.domain.consulting.Consult;
import holiday.fan.domain.consulting.ConsultRemover;
import holiday.fan.domain.dto.ConsultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ConsultService {
    /**
     * 예약, 예약내역, 취소, 예약변경, 상담완료
     */
    String reservation(Consult consult, Long userId); //예약하기(현재 예약된 상담이 있으면 겹쳐서 안 됨)
    Page<ConsultDto> myConsults(Long id, Pageable pageable); //전체 예약 내역
    Page<ConsultDto> completeConsults(Long id, Pageable pageable); //상담 완료 내역
    Consult read(Long id); //상담 내역 보기
    Page<ConsultDto> searchConsults(Long id, Date date, Pageable pageable); //날짜별 검색
    void cancel(Long consultId, Long userId); //예약취소
    void modify(Long id, Date date); //예약변경
    void isComplete(Long id); //상담완료
}