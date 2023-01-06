package holiday.fan;

import holiday.fan.domain.members.Member;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class dasdasdTest {

    @Test
    void lot() {

        List<Member> list = new ArrayList<>();
        list.add(null);

        System.out.println(list.get(0));
        Member member = Optional.ofNullable(list.get(0)).orElse(null);
        System.out.println(member==null);

        System.out.println("list.get(0) = " + list.get(0));
        assertThat(Optional.ofNullable(list.get(0)).orElse(null)).isNull();
        assertThatThrownBy(() -> list.get(0).getId()).isInstanceOf(NullPointerException.class);
    }

    @Test
    void lolo() {
        List<Member> list = new ArrayList<>();
        list.add(null);

        assertThat(Optional.ofNullable(list.get(0)).orElse(null)).isNull();

    }
}
