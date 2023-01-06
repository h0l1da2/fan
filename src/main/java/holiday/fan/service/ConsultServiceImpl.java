package holiday.fan.service;

import holiday.fan.domain.consulting.Consult;
import holiday.fan.domain.consulting.ConsultRemover;
import holiday.fan.domain.consulting.ConsultStatus;
import holiday.fan.domain.dto.ConsultDto;
import holiday.fan.repository.ConsultRepository;
import holiday.fan.service.interfaces.ConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public String reservation(Consult consult, Long userId) {
        return consultRepository.save(consult, userId);
    }

    @Override
    public Page<ConsultDto> myConsults(Long id, Pageable pageable) {
        if (id == null) {
            return null;
        }
        return consultRepository.findAll(id, pageable);
    }

    @Override
    public Page<ConsultDto> completeConsults(Long id, Pageable pageable) {
        return consultRepository.findAllComplete(id, pageable);
    }

    @Override
    public Consult read(Long id) {
        isComplete(id);
        return consultRepository.findById(id);
    }

    @Override
    public Page<ConsultDto> searchConsults(Long id, Date date, Pageable pageable) {
        return consultRepository.findAllByDate(id, date, pageable);
    }

    @Override
    public void cancel(Long consultId, Long userId) {
        consultRepository.delete(consultId, userId);
    }

    @Override
    public void modify(Long id, Date date) {
        consultRepository.updateDate(id, date);
    }

    @Override
    public void isComplete(Long id) {
        Consult read = consultRepository.findById(id);
        Date today = new Date();
            if(read.getReservationDate() != read.getModifyDate()) {
                if(today.after(read.getModifyDate())) {
                    if(read.getStatus() != ConsultStatus.CANCEL) {
                        consultRepository.updateStatus(id, ConsultStatus.COMPLETE);
                    }
                }
            }
    }


}
