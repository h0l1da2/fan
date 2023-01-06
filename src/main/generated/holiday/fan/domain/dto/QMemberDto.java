package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QMemberDto is a Querydsl Projection type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDto extends ConstructorExpression<MemberDto> {

    private static final long serialVersionUID = 608872605L;

    public QMemberDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> jibunAddress, com.querydsl.core.types.Expression<String> detail, com.querydsl.core.types.Expression<String> zonecode) {
        super(MemberDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, userId, name, nickname, email, address, jibunAddress, detail, zonecode);
    }

    public QMemberDto(com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> jibunAddress, com.querydsl.core.types.Expression<String> detail, com.querydsl.core.types.Expression<String> zonecode) {
        super(MemberDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, userId, name, nickname, email, address, jibunAddress, detail, zonecode);
    }

}

