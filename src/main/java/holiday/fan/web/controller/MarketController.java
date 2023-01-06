package holiday.fan.web.controller;

import holiday.fan.domain.dto.MarketDto;
import holiday.fan.domain.mdmarket.Market;
import holiday.fan.domain.mdmarket.MarketSearchCond;
import holiday.fan.domain.mdmarket.Md;
import holiday.fan.service.interfaces.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @GetMapping
    public Page<MarketDto> list(Pageable pageable) {
        return marketService.readList(pageable);
    }

    @GetMapping("/write")
    public String sell() {
        return "세이브 첫페이지";
    }

    @PostMapping("/write")
    public String sell(@RequestBody MarketDto marketDto, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        marketService.uploadSell(id, addMarket(marketDto));

        return "작성완료";
    }

    @GetMapping("/remove")
    public String deleteSell() {
        return "삭제 페이지";
    }

    @PostMapping("/remove")
    public String deleteSell(Long marketId, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        return marketService.removeSellByMember(marketId, id);
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long marketId, Model model) {
        Market market = marketService.read(marketId);
        model.addAttribute("market", market);
        return "뷰 보여주기"+market.getTitle();
    }

    @GetMapping("/search")
    public Page<MarketDto> search(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String titleContent,
                                  @RequestParam(required = false) Integer price, Pageable pageable) {
        return marketService.searchTitleContent(new MarketSearchCond(title, titleContent, price), pageable);
    }

    private Market addMarket(MarketDto marketDto) {
        return new Market(marketDto, new Md(marketDto.getName(), marketDto.getPrice(), marketDto.getQuantity()));
    }

}
