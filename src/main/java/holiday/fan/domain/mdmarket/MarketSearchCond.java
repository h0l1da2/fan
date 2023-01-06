package holiday.fan.domain.mdmarket;

import lombok.Getter;

import java.util.Map;

@Getter
public class MarketSearchCond {

    private String title;
    private String titleContent;
    private Integer price;

    protected MarketSearchCond() {

    }

    public MarketSearchCond(String title, String titleContent, Integer price) {
        this.title = title;
        this.titleContent = titleContent;
        this.price = price;
    }
}
