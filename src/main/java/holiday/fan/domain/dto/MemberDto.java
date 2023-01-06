package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
public class MemberDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String mailKey;
    private String address; //경기도 부천시
    private String jibunAddress; //~~로 111
    private String detail; //00동 100호
    private String zonecode; //우편번호

    protected MemberDto() {
    }

    @QueryProjection
    public MemberDto(Long id, String userId, String name, String nickname, String email, String address, String jibunAddress, String detail, String zonecode) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }

    @QueryProjection
    public MemberDto(String userId, String name, String nickname, String email, String address, String jibunAddress, String detail, String zonecode) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }

    public MemberDto(Long id, String userId, String password, String name, String nickname, String email, String mailKey, String address, String jibunAddress, String detail, String zonecode) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.mailKey = mailKey;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }
}
