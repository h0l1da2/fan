package holiday.fan.service;

import holiday.fan.domain.dto.FindMarketDto;
import holiday.fan.domain.dto.MdOrderDto;
import holiday.fan.domain.mdmarket.*;
import holiday.fan.domain.members.Member;
import holiday.fan.domain.members.MemberOrAdmin;
import holiday.fan.repository.OrderRepository;
import holiday.fan.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public String buy(Long memberId, FindMarketDto findMarketDto) {
        if(memberId == null) {
            return "로그인 하세요";
        }
        Member member = orderRepository.findMemberById(memberId);
        Market market = orderRepository.findByMarketById(findMarketDto.getMarketId());

        if(market.getMd().getQuantity() < findMarketDto.getQuantity()) {
            return "최대 수량을 넘었어요";
        }

        return orderRepository.insert(makeMdOrder(findMarketDto, member, market));
    }

    @Override
    public String cancel(Long mdOrdersId, Long memberId, MemberOrAdmin memberOrAdmin) {
        return orderRepository.deleteOrder(mdOrdersId, memberId, memberOrAdmin);
    }

    @Override
    public void deliveryUpdate(Long mdOrdersId, DeliveryStatus status) {
        orderRepository.deliveryUpdate(mdOrdersId, status);
    }

    @Override
    public void orderUpdate(Long orderId, OrderStatus status) {
        orderRepository.orderUpdate(orderId, status);
    }

    @Override
    public MdOrderDto read(Long mdOrderId, Long memberId) {
        if(memberId == null) {
            return null;
        }
        MdOrder mdOrder = orderRepository.selectOne(mdOrderId, memberId);

        return new MdOrderDto(
                mdOrder.getId(), mdOrder.getQuantity(), mdOrder.getPrice(), mdOrder.getMarket().getMd().getName(),
                mdOrder.getDelivery().getAddress(), mdOrder.getDelivery().getStatus(), mdOrder.getOrder().getOrderDate()
        );
    }

    /**
     *  조건마다 조건에 맞는 리스트를 불러오는 것보다
     *  리스트를 전부 불러온 후에 걸러서 가져오는 것이 성능이 좋다
     */

    @Override
    public Page<MdOrderDto> orderList(Long memberId, Pageable pageable) {
        //멤버id가 널일 경우 널반환?
        return mdOrderAllList(orderRepository.findAllOrders(memberId, pageable));
    }

    @Override
    public Page<MdOrderDto> oldList(Long memberId, Pageable pageable) {
        return mdOrderAllList(
                orderRepository.findAllOldOrders(memberId, pageable));
    }

    @Override
    public Page<MdOrderDto> completeList(Long memberId, Pageable pageable) {
        return mdOrderAllList(
                orderRepository.findAllCompleteOrders(memberId, pageable));
    }

    @Override
    public Page<MdOrderDto> cancelList(Long memberId, Pageable pageable) {
        return mdOrderAllList(
                orderRepository.findAllCancelOrders(memberId, pageable));
    }

    @Override
    public Page<MdOrderDto> progressList(Long memberId, Pageable pageable) {
        return mdOrderAllList(
                orderRepository.findAllProgressOrders(memberId, pageable));
    }

    public Page<MdOrderDto> mdOrderAllList(Page<MdOrder> mdOrderList) {

        return mdOrderList.map(
                m -> MdOrderDto.builder()
                        .id(m.getId())
                        .quantity(m.getQuantity())
                        .price(m.getPrice())
                        .name(m.getMarket().getMd().getName())
                        .deliveryStatus(m.getDelivery().getStatus())
                        .orderDate(m.getOrder().getOrderDate())
                        .build()
        );

    }

    private MdOrder makeMdOrder(FindMarketDto findMarketDto, Member member, Market market) {
        Delivery delivery = new Delivery(DeliveryStatus.READY, member.getAddress());
        Order order = new Order(OrderStatus.READY, member);
        MdOrder mdOrder = new MdOrder(findMarketDto.getQuantity(),
                findMarketDto.getQuantity()* market.getMd().getPrice(),
                delivery, order, market);
        return mdOrder;
    }


}
