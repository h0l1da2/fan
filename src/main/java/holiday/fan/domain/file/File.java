package holiday.fan.domain.file;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity @Getter
public class File {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String uuid;
    private String path;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer size;

    protected File() {

    }

    public File(String name, String path, Type type, Integer size) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        uuid = UUID.randomUUID().toString();
    }
}
