package holiday.fan.domain.mdmarket;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.members.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_remover_id")
    private OrderRemover orderRemover;

    protected Order() {

    }

    @QueryProjection
    public Order(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Order(OrderStatus status, Member member) {
        this.status = status;
        this.member = member;
    }

    public void whoOrder(Member member) {
        this.member = member;
    }

    public void orderCancel(OrderStatus status, OrderRemover orderRemover) {
        this.status = status;
        this.orderRemover = orderRemover;
    }

    public void orderStatusChange(OrderStatus status) {
        this.status = status;
    }

}
