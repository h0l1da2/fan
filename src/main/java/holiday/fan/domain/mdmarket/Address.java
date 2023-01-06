package holiday.fan.domain.mdmarket;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter //값 타입은 변경 불가
public class Address {

    @Id @GeneratedValue
    private Long id;
    private String address; //경기도 부천시
    private String jibunAddress; //~~로 111
    private String detail; //00동 100호
    private String zonecode; //우편번호

    protected Address() {
    }

    public Address(String address, String jibunAddress, String detail, String zonecode) {
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }

    public void wantAddressChange(String address, String jibunAddress, String detail, String zonecode) {
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.detail = detail;
        this.zonecode = zonecode;
    }
}
