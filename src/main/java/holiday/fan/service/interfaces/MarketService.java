package holiday.fan.service.interfaces;

import holiday.fan.domain.dto.MarketDto;
import holiday.fan.domain.mdmarket.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MarketService {
    /**
     * 판매글 등록, 판매글 삭제, 읽기, 목록, 검색
     * 구매, 구매취소, 배송상태 변경, 주문상태 변경
     */
    String uploadSell(Long memberId, Market market); //판매글 등록 save
    String removeSellByMember(Long marketId, Long memberId); //판매글 삭제 delete
    Market read(Long marketId); //판매글 읽기 findById
    Page<MarketDto> readAllList(Pageable pageable); // 전체 리스트 보기(삭제글 포함)
    Page<MarketDto> readList(Pageable pageable); // 유저용 판매글 리스트
    Page<MarketDto> readDeleteList(Pageable pageable); // 어드민용 삭제된 게시글 리스트
    Page<MarketDto> searchTitleContentPrice(MarketSearchCond marketSearchCond, Pageable pageable); //판매글 검색 findByMdName 페이징
    Page<MarketDto> searchTitleContent(MarketSearchCond marketSearchCond, Pageable pageable); //판매글 검색 findByMdName 페이징

}
