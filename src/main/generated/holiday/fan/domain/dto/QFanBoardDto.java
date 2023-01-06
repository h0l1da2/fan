package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QFanBoardDto is a Querydsl Projection type for FanBoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QFanBoardDto extends ConstructorExpression<FanBoardDto> {

    private static final long serialVersionUID = -411603196L;

    public QFanBoardDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<java.time.LocalDateTime> uploadDate) {
        super(FanBoardDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class}, id, title, nickname, uploadDate);
    }

}

