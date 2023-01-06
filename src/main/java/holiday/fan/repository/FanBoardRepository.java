package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.dto.FanBoardDto;
import holiday.fan.domain.fanletter.*;
import holiday.fan.domain.file.File;
import holiday.fan.domain.file.FileInfo;
import holiday.fan.domain.file.IsWhere;
import holiday.fan.domain.members.Member;
import holiday.fan.repository.jpa.FanBoardJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static holiday.fan.domain.fanletter.QFanBoard.*;

@Slf4j
@Repository
@Transactional
public class FanBoardRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final FanBoardJpaRepository fanBoardJpaRepository;

    public FanBoardRepository(EntityManager em, FanBoardJpaRepository fanBoardJpaRepository) {
        this.em = em;
        query = new JPAQueryFactory(em);
        this.fanBoardJpaRepository = fanBoardJpaRepository;
    }

    public FanBoard saveWithFile(Long id, File file, FanBoard fanBoard) {
        addWriter(id, fanBoard);
        em.persist(file);

        FileInfo fileInfo = new FileInfo(IsWhere.FAN_BOARD, file);
        em.persist(fileInfo);

        fanBoard.uploadFile(fileInfo);
        em.persist(fanBoard);

        em.close();
        return fanBoard;
    }

    public FanBoard save(Long id, FanBoard fanBoard) {
        addWriter(id, fanBoard);
        em.persist(fanBoard);
        em.close();
        return fanBoard;
    }

    public FanBoard findById(Long id) {
        return fanBoardJpaRepository.findById(id).orElseThrow();
    }

    public void update(Long boardId, FanBoard fanBoard) {
        FanBoard updateBoard = fanBoardJpaRepository.findById(boardId).orElseThrow();
        updateBoard.modifyMyBoard(fanBoard);
    }

    public void updateWithFile(Long boardId, FanBoard fanBoard) {
        FanBoard updateBoard = fanBoardJpaRepository.findById(boardId).orElseThrow();
        em.persist(fanBoard.getFileInfo());
        updateBoard.modifyMyBoard(fanBoard);
    }

    public void delete(Long boardId, Long userId) {
        FanBoard fanboard = fanBoardJpaRepository.findById(boardId).orElseThrow();
        Member member = em.find(Member.class, userId);
        BoardRemover boardRemover = new BoardRemover(member);
        em.persist(boardRemover);
        fanboard.whoRemoved(boardRemover);
    }

    public Page<FanBoardDto> findAllSearch(BoardSearchCond cond, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        log.info("리포지토리 ㄱㄱ");

        builder.and(fanBoard.boardRemover.isNull());

        if(cond.getBoardSearchType().equals(BoardSearchType.TITLE)) {
            builder.and(fanBoard.title.like("%"+cond.getTitleOrContent()+"%"));
        }

        if(cond.getBoardSearchType().equals(BoardSearchType.TITLE_CONTENT)) {
            builder.and(fanBoard.title.like("%"+cond.getTitleOrContent()+"%"));
            builder.or(fanBoard.content.like("%"+cond.getTitleOrContent()+"%"));
        }

        if(cond.getBoardSearchType().equals(BoardSearchType.NICKNAME)) {
            builder.and(fanBoard.boardMember.member.nickname.like("%" + cond.getNickName() + "%"));
        }
        log.info("getNickName ㄱㄱ = {}", cond.getNickName());


        log.info("리스트만들기 ㄱㄱ");
        List<FanBoardDto> resultList = query.select(Projections.constructor(FanBoardDto.class,
                        fanBoard.id, fanBoard.title,
                        fanBoard.boardMember.member.nickname, fanBoard.uploadDate))
                .from(fanBoard)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        log.info("리스트만듬 ㄱㄱ");

        Long total = query.select(fanBoard.count())
                .from(fanBoard)
                .fetchFirst();
        log.info("토탈 ㄱㄱ");

        return new PageImpl<>(resultList, pageable, total);
    }

    /**
     * 클라이언트에게 보여줄 목록
     * 어드민이 보는 전체 목록
     * 삭제된 게시글만 보는 목록
     * 검색은? 삭제된 게시글은 검색에서 제외
     */

    public Page<FanBoardDto> findAllForUser(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fanBoard.boardRemover.isNull());

        return getFanBoards(pageable);
    }

    public Page<FanBoardDto> findAllDelete(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fanBoard.boardRemover.isNotNull());

        return getFanBoards(pageable);
    }

    private Page<FanBoardDto> getFanBoards(Pageable pageable) {
        List<FanBoardDto> resultList =
                query.select(Projections.constructor(FanBoardDto.class,
                        fanBoard.id, fanBoard.title, fanBoard.boardMember.member.nickname, fanBoard.uploadDate))
                .from(fanBoard)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(fanBoard.count())
                .from(fanBoard)
                .fetchFirst();

        return new PageImpl<>(resultList, pageable, total);
    }

    private void addWriter(Long id, FanBoard fanBoard) {
        Member member = em.find(Member.class, id);
        BoardMember boardMember = new BoardMember(member);
        fanBoard.addBoardMember(boardMember);
    }
}
