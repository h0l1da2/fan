package holiday.fan.domain.mdmarket;

import com.querydsl.core.annotations.QueryProjection;
import holiday.fan.domain.dto.MarketDto;
import holiday.fan.domain.members.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class Market {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private MarketStatus status;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "md_id")
    private Md md;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_remover_id")
    private MarketRemover marketRemover;



    protected Market() {

    }

    @QueryProjection
    public Market(Long id, String title, String content, MarketStatus status, Md md, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.md = md;
        this.member = member;
    }

    public Market(MarketDto marketDto, Md md) {
        this.title = marketDto.getTitle();
        this.content = marketDto.getContent();
        this.status = marketDto.getStatus();
        this.md = md;
    }

    public void sellerIsMe(Member member) {
        this.member = member;
    }

    public void whoRemoved(MarketRemover marketRemover) {
        this.marketRemover = marketRemover;
    }
}
