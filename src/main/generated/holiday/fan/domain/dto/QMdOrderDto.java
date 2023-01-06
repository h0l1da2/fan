package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QMdOrderDto is a Querydsl Projection type for MdOrderDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMdOrderDto extends ConstructorExpression<MdOrderDto> {

    private static final long serialVersionUID = -261187152L;

    public QMdOrderDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<holiday.fan.domain.mdmarket.DeliveryStatus> deliveryStatus, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate) {
        super(MdOrderDto.class, new Class<?>[]{long.class, int.class, int.class, String.class, holiday.fan.domain.mdmarket.DeliveryStatus.class, java.time.LocalDateTime.class}, id, quantity, price, name, deliveryStatus, orderDate);
    }

}

