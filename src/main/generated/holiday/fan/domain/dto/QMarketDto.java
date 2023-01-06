package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QMarketDto is a Querydsl Projection type for MarketDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMarketDto extends ConstructorExpression<MarketDto> {

    private static final long serialVersionUID = -1667128389L;

    public QMarketDto(com.querydsl.core.types.Expression<Long> marketId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<holiday.fan.domain.mdmarket.MarketStatus> status, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity) {
        super(MarketDto.class, new Class<?>[]{long.class, String.class, String.class, holiday.fan.domain.mdmarket.MarketStatus.class, String.class, String.class, int.class, int.class}, marketId, title, content, status, nickname, name, price, quantity);
    }

}

