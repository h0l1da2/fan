package holiday.fan.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * holiday.fan.domain.dto.QJoinAdminDto is a Querydsl Projection type for JoinAdminDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QJoinAdminDto extends ConstructorExpression<JoinAdminDto> {

    private static final long serialVersionUID = -316674910L;

    public QJoinAdminDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> adminId, com.querydsl.core.types.Expression<String> password, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<holiday.fan.domain.members.RoleName> roleName, com.querydsl.core.types.Expression<holiday.fan.domain.members.Authority> authority) {
        super(JoinAdminDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, holiday.fan.domain.members.RoleName.class, holiday.fan.domain.members.Authority.class}, id, adminId, password, name, roleName, authority);
    }

}

