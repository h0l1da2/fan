package holiday.fan.domain.mdmarket;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class MdOrder {

    @Id @GeneratedValue
    private Long id;
    private Integer quantity;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    protected MdOrder() {

    }

    @QueryProjection
    public MdOrder(Long id, Integer quantity, Integer price, Market market, Delivery delivery, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.delivery = delivery;
        this.order = order;
        this.market = market;
    }

    public MdOrder(Integer quantity, Integer price, Delivery delivery, Order order, Market market) {
        this.quantity = quantity;
        this.price = price;
        this.delivery = delivery;
        this.order = order;
        this.market = market;
    }

    public void mdOrderHaveTo(Delivery delivery, Order order, Market market) {
        this.delivery = delivery;
        this.order = order;
        this.market = market;

    }
}
