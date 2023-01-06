package holiday.fan.domain.fanletter;

import holiday.fan.domain.dto.FanBoardDto;
import holiday.fan.domain.file.FileInfo;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class FanBoard {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_member_id")
    private BoardMember boardMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_remover_id")
    private BoardRemover boardRemover;

    protected FanBoard() {

    }

    public FanBoard(String title, String content, BoardMember boardMember) {
        this.title = title;
        this.content = content;
        this.boardMember = boardMember;
    }

    public FanBoard(FanBoardDto fanBoardDto, FileInfo fileInfo) {
        this.title = fanBoardDto.getTitle();
        this.content = fanBoardDto.getContent();
        this.fileInfo = fileInfo;
    }

    public FanBoard(FanBoardDto fanBoardDto) {
        this.title = fanBoardDto.getTitle();
        this.content = fanBoardDto.getContent();
    }

    public void modifyMyBoard(FanBoard fanBoard) {
        this.title = fanBoard.getTitle();
        this.content = fanBoard.getContent();
        this.fileInfo = fanBoard.getFileInfo();
    }

    public void whoRemoved(BoardRemover boardRemover) {
        this.boardRemover = boardRemover;
    }

    public void uploadFile(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void addBoardMember(BoardMember boardMember) {
        this.boardMember = boardMember;
    }
}
