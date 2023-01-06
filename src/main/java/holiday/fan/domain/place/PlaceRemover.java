package holiday.fan.domain.place;

import holiday.fan.domain.members.Admin;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class PlaceRemover {

    @Id @GeneratedValue
    private Long id;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime removeDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    protected PlaceRemover() {
    }

    public PlaceRemover(Admin admin) {
        this.admin = admin;
    }
}
