package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.mdmarket.Address;
import holiday.fan.domain.mdmarket.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MdOrderDto {

    private Long id;
    private Integer quantity;
    private Integer price;
    private String name;

    private Address address;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime orderDate;


    @QueryProjection
    public MdOrderDto(Long id, Integer quantity, Integer price, String name, DeliveryStatus deliveryStatus, LocalDateTime orderDate) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
    }

    public MdOrderDto(Long id, Integer quantity, Integer price, String name, Address address, DeliveryStatus deliveryStatus, LocalDateTime orderDate) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
    }


}