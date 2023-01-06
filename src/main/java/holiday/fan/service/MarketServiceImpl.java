package holiday.fan.service;

import holiday.fan.domain.dto.MarketDto;
import holiday.fan.domain.mdmarket.*;
import holiday.fan.domain.members.Member;
import holiday.fan.repository.*;
import holiday.fan.service.interfaces.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    @Override
    public String uploadSell(Long memberId, Market market) {
        Member findMember = marketRepository.findMemberById(memberId);
        market.sellerIsMe(findMember);
        marketRepository.save(market);
        return "세이브 완료";
    }
    @Override
    public String removeSellByMember(Long marketId, Long memberId) {
        /**
         * 마켓 찾음
         * 리무버 넣음
         */
        Market findMarket = marketRepository.findById(marketId);
        if(findMarket == null) {
            return "없는 게시글";
        }
        if(memberId != findMarket.getMember().getId()) {
            return "본인 게시글만 삭제 가능합니다";
        }

        MarketRemover remover = new MarketRemover(findMarket.getMember());
        marketRepository.delete(marketId, remover);
        return "삭제 완료";
    }

    @Override
    public Market read(Long marketId) {
        return marketRepository.findById(marketId);
    }

    @Override
    public Page<MarketDto> readAllList(Pageable pageable) {
        Page<Market> marketList = marketRepository.findAll(pageable);
        return marketDtoList(marketList);
    }

    @Override
    public Page<MarketDto> readList(Pageable pageable) {
        Page<Market> marketList = marketRepository.findAllForUser(pageable);
        return marketDtoList(marketList);
    }

    @Override
    public Page<MarketDto> readDeleteList(Pageable pageable) {
        Page<Market> deleteList = marketRepository.findAllDeleteList(pageable);
        return marketDtoList(deleteList);
    }

    @Override
    public Page<MarketDto> searchTitleContentPrice(MarketSearchCond marketSearchCond, Pageable pageable) {
        Page<Market> marketSearch = marketRepository.findAllSearch(
                marketSearchCond, pageable
        );
        return marketDtoList(marketSearch);
    }

    @Override
    public Page<MarketDto> searchTitleContent(MarketSearchCond marketSearchCond, Pageable pageable) {
        Page<Market> marketSearch = marketRepository.findAllSearch(
                marketSearchCond, pageable);
        return marketDtoList(marketSearch);
    }

    private Page<MarketDto> marketDtoList(Page<Market> marketList) {
        return marketList.map(m ->
                MarketDto.builder()
                        .marketId(m.getId())
                        .title(m.getTitle())
                        .content(m.getContent())
                        .status(m.getStatus())
                        .nickname(m.getMember().getNickname())
                        .name(m.getMd().getName())
                        .price(m.getMd().getPrice())
                        .quantity(m.getMd().getQuantity())
                        .build()
        );
    }



}
