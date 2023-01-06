package holiday.fan.domain.dto;

import lombok.Data;

@Data
public class ModifyMemberDto {

    private String password;
    private String name;
    private String nickname;
    private String email;
    private String address; //경기도 부천시
    private String jibunAddress; //~~로 111
    private String detail; //00동 100호
    private String zonecode; //우편번호

    protected ModifyMemberDto() {

    }

    public ModifyMemberDto(String password, String name, String nickname, String email, String address, String jibunAddress, String detail, String zonecode) {
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }
}
