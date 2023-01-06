package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberListDto {

    private Long id;
    private String userId;
    private String name;
    private String nickname;
    private String email;

    protected MemberListDto() {

    }

    @QueryProjection
    public MemberListDto(Long id, String userId, String name, String nickname, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}
