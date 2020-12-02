package eu.itswc.persistance;

import eu.itswc.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface LocationRepository extends CrudRepository<Location, String> {

    @Query( value =
            "SELECT * " +
            "FROM LOCATION l " +
            "WHERE l.latitude = latitude AND l.longitude = longitude " +
            "LIMIT 1",
            nativeQuery = true)
    Location getLocationByCoordinates(double latitude, double longitude);
}
