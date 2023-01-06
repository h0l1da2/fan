package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.mdmarket.MarketStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketDto {

    private Long marketId;
    private String title;
    private String content;
    private MarketStatus status;
    private String nickname;
    private String name;
    private Integer price;
    private Integer quantity;

    protected MarketDto() {
    }

    public MarketDto(String title, String content, MarketStatus status, String nickname, String name, Integer price, Integer quantity) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.nickname = nickname;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @QueryProjection
    public MarketDto(Long marketId, String title, String content, MarketStatus status, String nickname, String name, Integer price, Integer quantity) {
        this.marketId = marketId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.nickname = nickname;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
