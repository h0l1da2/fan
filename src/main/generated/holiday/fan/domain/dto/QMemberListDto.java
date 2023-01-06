package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QMemberListDto is a Querydsl Projection type for MemberListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberListDto extends ConstructorExpression<MemberListDto> {

    private static final long serialVersionUID = -868891617L;

    public QMemberListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> email) {
        super(MemberListDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class}, id, userId, name, nickname, email);
    }

}

