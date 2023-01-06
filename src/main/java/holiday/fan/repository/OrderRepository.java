package holiday.fan.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.mdmarket.*;
import holiday.fan.domain.members.Admin;
import holiday.fan.domain.members.Member;
import holiday.fan.domain.members.MemberOrAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import java.util.List;

import static holiday.fan.domain.mdmarket.QMdOrder.*;

@Slf4j
@Repository
@Transactional
public class OrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public OrderRepository(EntityManager em) {
        this.em = em;
        query = new JPAQueryFactory(em);
    }

    public MdOrder selectOne(Long mdOrderId, Long memberId) {
        MdOrder mdOrder = em.find(MdOrder.class, mdOrderId);
        if(mdOrder.getOrder().getMember().getId().equals(memberId)) {
            return null;
        }
        em.close();

        return mdOrder;
    }

    public String insert(MdOrder mdOrder) {
        Market market = em.find(Market.class, mdOrder.getMarket().getId());
        market.getMd().mdQuantityChange( -mdOrder.getQuantity() );
        em.persist(mdOrder);
        em.close();

        return "결제 완료";
    }



    public String deleteOrder(Long orderId, Long id, MemberOrAdmin memberOrAdmin) {
        // 주문 정보 찾기
        Order order = em.find(Order.class, orderId);
        order.orderStatusChange(OrderStatus.CANCEL);

        MdOrder mdOrder = query.select(QMdOrder.mdOrder)
                .from(QMdOrder.mdOrder)
                .where(QMdOrder.mdOrder.order.id.eq(orderId))
                .fetchOne();

        // delivery status -> cancel
        mdOrder.getDelivery()
                .changeStatus(DeliveryStatus.CANCEL);

        // 삭제자 찾음
        OrderRemover orderRemover = searchCanceler(id, memberOrAdmin);
        removeOrder(mdOrder, orderRemover);

        // md quantity 복구
        mdOrder.getMarket()
                .getMd()
                .mdQuantityChange(mdOrder.getQuantity());
        em.close();

        return "삭제 완료";
    }

    public void deliveryUpdate(Long mdOrderId, DeliveryStatus status) {
        em.find(MdOrder.class, mdOrderId)
                .getDelivery()
                .changeStatus(status);
        em.close();
    }

    public void orderUpdate(Long orderId, OrderStatus status) {
        em.find(Order.class, orderId)
                .orderStatusChange(status);
        em.close();
    }

    /**
     * 전체 주문 목록
     */
    public Page<MdOrder> findAllOrders(Long memberId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mdOrder.order.member.id.eq(memberId));

        return getOrderList(pageable, builder);
    }

    /**
     * 지난 주문 모두 보기
     */
    public Page<MdOrder> findAllOldOrders(Long memberId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(mdOrder.order.member.id.like(String.valueOf(memberId)))
                .and(mdOrder.order.status.eq(OrderStatus.COMPLETE))
                .or(mdOrder.order.status.eq(OrderStatus.CANCEL))
                .or(mdOrder.order.status.eq(OrderStatus.CANCEL_ADMIN));

        return getOrderList(pageable, builder);
    }

    /**
     * 완료된 주문
     */

    public Page<MdOrder> findAllCompleteOrders(Long memberId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(mdOrder.order.member.id.like(String.valueOf(memberId)))
                .and(mdOrder.order.status.eq(OrderStatus.COMPLETE));

        return getOrderList(pageable, builder);
    }

    /**
     * 취소한(된) 주문
     */
    public Page<MdOrder> findAllCancelOrders(Long memberId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(mdOrder.order.member.id.like(String.valueOf(memberId)))
                .and(mdOrder.order.status.eq(OrderStatus.CANCEL))
                .or(mdOrder.order.status.eq(OrderStatus.CANCEL_ADMIN));

        return getOrderList(pageable, builder);
    }

    public Member findMemberById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        em.close();

        return member;
    }

    public Market findByMarketById(Long marketId) {
        Market market = em.find(Market.class, marketId);
        em.close();

        return market;
    }

    /**
     * 진행 중인 주문
     */
    public Page<MdOrder> findAllProgressOrders(Long memberId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mdOrder.order.member.id.like(String.valueOf(memberId)))
                .and(mdOrder.order.status.eq(OrderStatus.READY));

        return getOrderList(pageable, builder);
    }

    private OrderRemover searchCanceler(Long id, MemberOrAdmin memberOrAdmin) {
        log.info("주문 취소자 찾기");
        // 주문 취소자를 찾기
        OrderRemover orderRemover = new OrderRemover();
        if(memberOrAdmin.equals(MemberOrAdmin.MEMBER)) {
            orderRemover.removerIsMember(
                    em.find(Member.class, id)
            );
        }

        if(memberOrAdmin.equals(MemberOrAdmin.ADMIN)) {
            em.find(Admin.class, id);
            orderRemover.removerIsAdmin(
                    em.find(Admin.class, id)
            );
        }

        em.persist(orderRemover);
        em.close();

        return orderRemover;
    }

    private void removeOrder(MdOrder mdOrder, OrderRemover orderRemover) {
        if(orderRemover.getMember() != null) {
            mdOrder.getOrder()
                    .orderCancel(OrderStatus.CANCEL, orderRemover);
        }
        if (orderRemover.getAdmin() != null) {
            mdOrder.getOrder()
                    .orderCancel(OrderStatus.CANCEL_ADMIN, orderRemover);
        }
    }

    private Page<MdOrder> getOrderList(Pageable pageable, BooleanBuilder builder) {
        List<MdOrder> resultList = query.select(Projections.constructor(MdOrder.class,
                        mdOrder.id, mdOrder.price, mdOrder.quantity, mdOrder.market,
                        mdOrder.delivery, mdOrder.order))
                .from(mdOrder)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(mdOrder.count())
                .from(mdOrder)
                .fetchOne();

        return new PageImpl<>(resultList, pageable, total);
    }
}
