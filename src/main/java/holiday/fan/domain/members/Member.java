package holiday.fan.domain.members;

import holiday.fan.domain.dto.MemberDto;
import holiday.fan.domain.mdmarket.Address;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Random;

@Entity @Getter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String nickname;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Email
    private String email;
    private String mailKey;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_remove_id")
    private MemberRemove memberRemove;

    protected Member() {}

    public Member(MemberDto MemberDto, Address address) {
        this.userId = MemberDto.getUserId();
        this.password = MemberDto.getPassword();
        this.name = MemberDto.getName();
        this.nickname = MemberDto.getNickname();
        this.address = address;
        this.email = MemberDto.getEmail();
        this.mailKey = MemberDto.getMailKey();
    }

    public void changeMemberInfo(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.mailKey = member.getMailKey();
    }

    public void wantDelete(MemberRemove memberRemove) {
        Random random = new Random();
        int code = random.nextInt(899999)+100000;
        this.memberRemove = memberRemove;
        userId = "removed_"+userId+code;
    }
    public void myAddressIs(Address address) {
        this.address = address;
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }
}
