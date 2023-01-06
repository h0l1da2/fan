package holiday.fan.web.controller;


import holiday.fan.domain.dto.FindMarketDto;
import holiday.fan.domain.dto.MdOrderDto;
import holiday.fan.domain.members.MemberOrAdmin;
import holiday.fan.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<MdOrderDto> order(HttpSession session, Pageable pageable) {
        Long memberId = getMemberId(session);
        return orderService.orderList(memberId, pageable);
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long mdOrderId, HttpSession session) {
        Long id = getMemberId(session);
        MdOrderDto result = orderService.read(mdOrderId, id);
        if (result == null) {
            return "다른 거예요";
        }
        return result.getName();
    }

    @GetMapping("/buy")
    public String buy() {
        return "구매페이지";
    }

    @PostMapping("/buy")
    public String buy(@RequestBody FindMarketDto findmarketDto, HttpSession session) {
        Long memberId = getMemberId(session);
        orderService.buy(memberId, findmarketDto);
        return "구매";
    }

    @PostMapping("/cancel")
    public String cancel(Long orderId, HttpSession session) {
        Long memberId = getMemberId(session);
        return orderService.cancel(orderId, memberId, MemberOrAdmin.MEMBER);
    }

    @GetMapping("/progress")
    public Page<MdOrderDto> progressList(Pageable pageable, HttpSession session) {
        Long memberId = getMemberId(session);
        return orderService.progressList(memberId, pageable);
    }

    @GetMapping("/old")
    public Page<MdOrderDto> oldList(Pageable pageable, HttpSession session) {
        Long id = getMemberId(session);
        return orderService.oldList(id, pageable);
    }

    @GetMapping("/complete")
    public Page<MdOrderDto> completeList(Pageable pageable, HttpSession session) {
        Long id = getMemberId(session);
        return orderService.completeList(id, pageable);
    }
    @GetMapping("/cancel")
    public Page<MdOrderDto> cancelList(Pageable pageable, HttpSession session) {
        Long id = getMemberId(session);
        return orderService.cancelList(id, pageable);
    }

    @GetMapping("/list")
    public Page<MdOrderDto> list(Pageable pageable, HttpSession session) {
        //세션 널이면 리다이렉트
        if (getMemberId(session) == null) {
            return null;
        }
        return orderService.orderList(getMemberId(session), pageable);
    }

    private static Long getMemberId(HttpSession session) {
        if(session == null) {
            return null;
        }
        Long id = (Long) session.getAttribute("id");
        return id;
    }
}
