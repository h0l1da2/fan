package holiday.fan.domain.dto;

import lombok.Data;


@Data
public class FindMarketDto {
    private Long marketId;
    private Integer quantity;


    protected FindMarketDto() {
    }


}
