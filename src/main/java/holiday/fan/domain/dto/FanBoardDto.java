package holiday.fan.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.file.IsWhere;
import holiday.fan.domain.file.Type;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class FanBoardDto {

    private Long id;
    private String title;
    private String content;

    @Enumerated(value = EnumType.STRING)
    private IsWhere isWhere;

    private String nickname;
    private String name;
    private String path;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private Integer size;
    private LocalDateTime uploadDate;

    protected FanBoardDto() {
    }

    public FanBoardDto(String title, String content, IsWhere isWhere, String name, String path, Type type, Integer size) {
        this.title = title;
        this.content = content;
        this.isWhere = isWhere;
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
    }

    @QueryProjection
    public FanBoardDto(Long id, String title, String nickname, LocalDateTime uploadDate) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.uploadDate = uploadDate;
    }
}
