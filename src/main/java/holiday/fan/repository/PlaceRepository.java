package holiday.fan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import holiday.fan.domain.place.Place;
import holiday.fan.domain.place.PlaceRemover;
import holiday.fan.domain.place.QPlace;
import holiday.fan.exception.YouNotWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static holiday.fan.domain.place.QPlace.*;

@Slf4j
@Repository
@Transactional
public class PlaceRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public PlaceRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public Place save(Place place) {
        em.persist(place.getMap().getAddress());
        em.persist(place.getMap());
        em.persist(place.getFileInfo().getFile());
        em.persist(place.getFileInfo());
        em.persist(place);
        em.close();

        return place;
    }

    public List<Place> findAll() {
        return query.select(place)
                .from(place)
                .fetch();
    }

    public void update(Long placeId, Long adminId, Place place) {
        Place findPlace = em.find(Place.class, placeId);

        //작성한 어드민이랑 수정하려는 어드민이 불일치하면 예외 발생
        if(findPlace.getAdmin().getId() != adminId) {
            throw new YouNotWriter("본인만 수정 가능합니다");
        }
        findPlace.modifyPlace(place);
        em.close();
    }

    public void delete(Long id, PlaceRemover remover) {
        Place place = em.find(Place.class, id);
        place.whoRemoved(remover);
        em.close();
    }

}
