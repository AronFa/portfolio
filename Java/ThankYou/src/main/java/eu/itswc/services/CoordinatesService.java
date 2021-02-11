package eu.itswc.services;

import eu.itswc.exception.CoordinatesNotFoundException;
import eu.itswc.exception.SuperfluousIDException;
import eu.itswc.model.Coordinates;
import eu.itswc.persistance.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesService {

    private CoordinatesRepository coordinatesRepository;

    CoordinatesService(@Autowired CoordinatesRepository cr){
        coordinatesRepository = cr;
    }

    public Coordinates getCoordinatesById(Long id) {
        makeSureCoordinateIsPresentWithId(id);
        return coordinatesRepository.findById(id).get();
    }

    public Long saveCoordinates(Coordinates c){
        makeSureNoIdIsIncluded(c);
        Coordinates coords = new Coordinates(c.getLatitude(), c.getLongitude());
        coordinatesRepository.save(coords);
        return coords.getId();
    }

    private boolean coordinateIsPresentWithId(Long id){
        return coordinatesRepository.findById(id).isPresent();
    }

    private void makeSureCoordinateIsPresentWithId(Long id){
        if(! coordinateIsPresentWithId(id)){
            throw new CoordinatesNotFoundException(id);
        }
    }

    private void makeSureNoIdIsIncluded(Coordinates c){
        if (c.getId() != null){
            throw new SuperfluousIDException(c);
        }
    }
}
