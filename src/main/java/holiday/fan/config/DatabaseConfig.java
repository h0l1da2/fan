package holiday.fan.config;

import holiday.fan.repository.*;
import holiday.fan.repository.jpa.*;
import holiday.fan.service.*;
import holiday.fan.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final EntityManager em;
    private final MarketJpaRepository marketJpaRepository;
    private final FanBoardJpaRepository fanBoardJpaRepository;
    private final ConsultJpaRepository consultJpaRepository;



    @Bean
    public MarketService marketService() {
        return new MarketServiceImpl(marketRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(orderRepository());
    }

    @Bean
    public FanBoardService fanBoardService() {
        return new FanBoardServiceImpl(fanBoardRepository());
    }

    @Bean
    public ConsultService consultService() {
        return new ConsultServiceImpl(consultRepository());
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentRepository());
    }

    @Bean
    public PlaceService placeService() {
        return new PlaceServiceImpl(placeRepository());
    }


    @Bean
    public MarketRepository marketRepository() {
        return new MarketRepository(em, marketJpaRepository);
    }
    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository(em);
    }
    @Bean
    public FanBoardRepository fanBoardRepository() {
        return new FanBoardRepository(em, fanBoardJpaRepository);
    }
    @Bean
    public ConsultRepository consultRepository() {
        return new ConsultRepository(em, consultJpaRepository);
    }
    @Bean
    public CommentRepository commentRepository() {
        return new CommentRepository(em);
    }
    @Bean
    public PlaceRepository placeRepository() {
        return new PlaceRepository(em);
    }
}

