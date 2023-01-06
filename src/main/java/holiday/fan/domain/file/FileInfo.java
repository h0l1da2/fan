package holiday.fan.domain.file;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class FileInfo {

    @Id @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private IsWhere isWhere;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    protected FileInfo() {

    }

    public FileInfo(IsWhere isWhere, File file) {
        this.isWhere = isWhere;
        this.file = file;
    }

    public void myFile(File file) {
        this.file = file;
    }
}
