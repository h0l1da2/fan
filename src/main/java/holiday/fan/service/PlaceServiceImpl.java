package holiday.fan.service;

import holiday.fan.domain.place.Place;
import holiday.fan.domain.place.PlaceRemover;
import holiday.fan.repository.PlaceRepository;
import holiday.fan.service.interfaces.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public Place write(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> read() {
        return placeRepository.findAll();
    }

    @Override
    public void modify(Long placeId, Long adminId, Place place) {
        placeRepository.update(placeId, adminId, place);
    }

    @Override
    public void delete(Long id, PlaceRemover placeRemover) {
        placeRepository.delete(id, placeRemover);
    }
}

