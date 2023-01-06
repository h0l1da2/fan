package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.mdmarket.*;
import holiday.fan.domain.members.Member;
import holiday.fan.repository.jpa.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static holiday.fan.domain.mdmarket.QMarket.*;

@Slf4j
@Repository
@Transactional
public class MarketRepository {

    private final JPAQueryFactory query;
    private final EntityManager em;
    private final MarketJpaRepository marketJpaRepository;

    public MarketRepository(EntityManager em, MarketJpaRepository marketJpaRepository) {
        query = new JPAQueryFactory(em);
        this.em = em;
        this.marketJpaRepository = marketJpaRepository;
    }

    public Market save(Market market) {
        em.persist(market);
        em.close();
        return market;
    }

    public void delete(Long marketId, MarketRemover remover) {
        Market market = em.find(Market.class, marketId);
        em.persist(remover);
        market.whoRemoved(remover);
        em.close();
    }

    public Page<Market> findAll(Pageable pageable) { //쿼리dsl
        return noWhereList(pageable);
    }

    public Page<Market> findAllDeleteList(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(market.marketRemover.isNotNull());

        return getOrderList(pageable, builder);
    }

    public Page<Market> findAllForUser(Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(market.marketRemover.isNull());

        return getOrderList(pageable, builder);
    }

    public Market findById(Long marketId) {
        return marketJpaRepository.findById(marketId).orElse(null);
    }

    public Page<Market> findAllSearch(MarketSearchCond search, Pageable pageable) {
        String title = search.getTitle();
        String titleContent = search.getTitleContent();
        Integer price = search.getPrice();

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.hasText(title)) {
            builder.and(market.title.like("%"+title+"%"));
        }
        if(StringUtils.hasText(titleContent)) {
            builder.or(market.title.like("%"+titleContent+"%"));
            builder.or(market.content.like("%"+titleContent+"%"));
        }
        if(price != null) {
            builder.and(market.md.price.loe(price));
        }

        return getOrderList(pageable, builder);
    }
    public Member findMemberById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        em.close();

        return member;
    }

    private PageImpl<Market> noWhereList(Pageable pageable) {
        List<Market> resultList = query.select(Projections.constructor(Market.class,
                        market.id, market.title, market.content, market.status,
                        market.member.nickname, market.md.name, market.md.price, market.md.quantity))
                .from(market)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(market.count())
                .from(market)
                .fetchOne();

        return new PageImpl<>(resultList, pageable, total);
    }
//    }

    private Page<Market> getOrderList(Pageable pageable, BooleanBuilder builder) {

        Long total = query.select(market.count())
                .from(market)
                .fetchOne();

        List<Market> resultList = query.select(Projections.constructor(Market.class,
                        market.id, market.title, market.content, market.status,
                        market.member.nickname, market.md.name, market.md.price, market.md.quantity))
                .from(market)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(resultList, pageable, total);
    }
}
