package holiday.fan.domain.mdmarket;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    protected Delivery() {

    }

    public Delivery(DeliveryStatus status, Address address) {
        this.status = status;
        this.address = address;
    }

    @QueryProjection
    public Delivery(DeliveryStatus status) {
        this.status = status;
    }

    public void changeStatus(DeliveryStatus status) {
        this.status = status;
    }
}
