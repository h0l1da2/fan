package holiday.fan.domain.fanletter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardSearchCond {

    private String titleOrContent;
    private String nickName;

    private BoardSearchType boardSearchType;

}
