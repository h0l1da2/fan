package holiday.fan.repository.jpa;

import holiday.fan.domain.fanletter.FanBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FanBoardJpaRepository extends JpaRepository<FanBoard, Long> {

    List<FanBoard> findAllByTitle(String title, Pageable pageable);
    List<FanBoard> findAllByTitleAndContent(String title, String content, Pageable pageable);
}
