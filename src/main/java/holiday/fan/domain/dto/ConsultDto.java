package holiday.fan.domain.dto;

import holiday.fan.domain.consulting.ConsultStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class ConsultDto {

    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ConsultStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyDate;

    protected ConsultDto() {
    }

    public ConsultDto(ConsultStatus status, Date reservationDate) {
        this.status = status;
        this.reservationDate = reservationDate;
    }

    public ConsultDto(Long id, ConsultStatus status, Date reservationDate, Date modifyDate) {
        this.id = id;
        this.status = status;
        this.reservationDate = reservationDate;
        this.modifyDate = modifyDate;
    }
}
