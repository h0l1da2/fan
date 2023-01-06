package holiday.fan.domain.place;

import holiday.fan.domain.file.FileInfo;
import holiday.fan.domain.members.Admin;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class Place {

    @Id @GeneratedValue
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private Star star;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadDate;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY) //place가 1, map이 n
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_remover_id")
    private PlaceRemover placeRemover;

    protected Place() {
    }

    public Place(String content, Star star, Admin admin, Map map, FileInfo fileInfo) {
        this.content = content;
        this.star = star;
        this.admin = admin;
        this.map = map;
        this.fileInfo = fileInfo;
    }

    public void modifyPlace(Place place) {
        this.content = place.getContent();
        this.star = place.getStar();
        this.map = place.getMap();
        this.fileInfo = place.getFileInfo();
    }

    public void whoRemoved(PlaceRemover placeRemover) {
        this.placeRemover = placeRemover;
    }
}
