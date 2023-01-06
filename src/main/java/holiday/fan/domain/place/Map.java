package holiday.fan.domain.place;

import holiday.fan.domain.mdmarket.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Map {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "x_axis")
    private Integer x;

    @Column(name = "y_axis")
    private Integer y;

    protected Map() {

    }

    public Map(String name, Address address, Integer x, Integer y) {
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
    }
}
