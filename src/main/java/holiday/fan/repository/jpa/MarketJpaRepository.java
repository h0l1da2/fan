package holiday.fan.repository.jpa;

import holiday.fan.domain.mdmarket.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketJpaRepository extends JpaRepository<Market, Long> {

//    List<Market> findAllById(); //판매글 목록
//    List<Market> findAllByTitle(); //제목으로 찾기
//    List<Market> findAllByTitleAndContent(); //제목+내용
//    List<Market> findAllByTitleAndMdPrice(); //제목+가격대
//    List<Market> findAllByTitleAndContentAndMdPrice(); //제목+가격대


}
