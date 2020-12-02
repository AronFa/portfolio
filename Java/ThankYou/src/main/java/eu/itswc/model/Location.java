package eu.itswc.model;

import javax.persistence.*;

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Column(name = "LAT")
    private double latitude;

    @Column(name = "LONG")
    private double longitude;

    public Location(){};

    // TODO how to handle illegal args given by the user?
    public Location(double latitude, double longitude) {
        validateLocation(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static boolean locationIsValid(double latitude, double longitude){
        return latitudeIsValid(latitude) && longitudeIsValid(longitude);
    }

    private static boolean latitudeIsValid(double latitude){
        return (-90 <= latitude && latitude <= 90);
    }

    private static boolean longitudeIsValid(double longitude) {
        return (0 <= longitude && longitude <= 360);
    }

    public static boolean locationIsValid(Location loc) {
        return locationIsValid(loc.getLatitude(), loc.getLongitude());
    }

    private void validateLocation(double lt, double lg) throws IllegalArgumentException{
        if (!locationIsValid(lt, lg)){
            throw new IllegalArgumentException("Invalid Latitude or Longitude value");
        }
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) throws IllegalArgumentException {
        if (!latitudeIsValid(latitude)) {
            throw new IllegalArgumentException("Invalid Latitude. Latitude must be between -90 and 90.");
        }
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws IllegalArgumentException {
        if(!longitudeIsValid(longitude)){
            throw new IllegalArgumentException("Invalid Longitude. Longitude must be between 0 and 360. ");
        }
        this.longitude = longitude;
    }
}
