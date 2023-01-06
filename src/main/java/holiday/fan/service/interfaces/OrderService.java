package holiday.fan.service.interfaces;

import holiday.fan.domain.dto.FindMarketDto;
import holiday.fan.domain.dto.MdOrderDto;
import holiday.fan.domain.mdmarket.*;
import holiday.fan.domain.members.MemberOrAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    String buy(Long memberId, FindMarketDto findmarketDto); //구매 findById로 수정
    String cancel(Long mdOrdersId, Long memberId, MemberOrAdmin memberOrAdmin); //구매 취소 findById로 수정
    void deliveryUpdate(Long mdOrdersId, DeliveryStatus status); //배송상태 수정 findById로 수정
    void orderUpdate(Long orderId, OrderStatus status); //주문상태 수정 findById로 수정
    MdOrderDto read(Long mdOrderId, Long memberId);
    Page<MdOrderDto> orderList(Long memberId, Pageable pageable); //주문 전체 보기
    Page<MdOrderDto> progressList(Long memberId, Pageable pageable); //진행 주문 전체
    Page<MdOrderDto> oldList(Long memberId, Pageable pageable); //지난 주문 보기
    Page<MdOrderDto> completeList(Long memberId, Pageable pageable); //완료 주문 전체
    Page<MdOrderDto> cancelList(Long memberId, Pageable pageable); //취소 주문 전체


}
