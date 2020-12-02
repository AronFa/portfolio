package eu.itswc.services;

import eu.itswc.model.Location;
import eu.itswc.persistance.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepo;

    public String getLocationIdByCoordinates(double latitude, double longitude) {
        return locationRepo.getLocationByCoordinates(latitude, longitude).getId();
    }

    public String getLocationIdByInstance(Location loc) {
        return getLocationIdByCoordinates(loc.getLatitude(), loc.getLongitude());
    }

    public void addLocation(Location l){
        locationRepo.save(l);
    }

    public Optional<Location> getLocationById(String id){
        return locationRepo.findById(id);
    }

    public void updateLocation(Location l){
        locationRepo.save(l);
    }

    public void deleteLocationById(String id){
        locationRepo.deleteById(id);
    }

}
