package holiday.fan.service.interfaces;

import holiday.fan.domain.place.Place;
import holiday.fan.domain.place.PlaceRemover;

import java.util.List;

public interface PlaceService {
    /**
     * 쓰기, 읽기, 수정, 삭제
     */
    Place write(Place place);
    List<Place> read();
    void modify(Long placeId, Long adminId, Place place);
    void delete(Long id, PlaceRemover placeRemover);

}
