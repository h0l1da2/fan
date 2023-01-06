package holiday.fan.domain.mdmarket;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
public class Md {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer quantity;

    protected Md() {

    }

    @QueryProjection
    public Md(String name) {
        this.name = name;
    }

    public Md(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void mdQuantityChange(Integer quantity) {
        this.quantity += quantity;
    }

}
