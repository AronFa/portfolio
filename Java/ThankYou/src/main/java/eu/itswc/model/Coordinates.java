package eu.itswc.model;

import eu.itswc.exception.InvalidLatitudeException;
import eu.itswc.exception.InvalidLongitudeException;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "TOKEN")
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    public Coordinates(){};

    public Coordinates(double latitude, double longitude) {
        makeSureCoordinatesAreValid(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static boolean latitudeIsValid(double lt){
        return (-90 <= lt && lt <= 90);
    }

    private static boolean longitudeIsValid(double lg) {
        return (0 <= lg && lg <= 360);
    }

    private static void makeSureLatitudeIsValid(double lt) throws InvalidLatitudeException{
        if(! latitudeIsValid(lt)){
            throw new InvalidLatitudeException(lt);
        }
    }

    private static void makeSureLongitudeIsValid(double lg)throws InvalidLongitudeException{
        if (! longitudeIsValid(lg)){
            throw new InvalidLongitudeException(lg);
        }
    }

    private static void makeSureCoordinatesAreValid(double lt, double lg) {
        makeSureLatitudeIsValid(lt);
        makeSureLongitudeIsValid(lg);
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lt){
        makeSureLatitudeIsValid(lt);
        this.latitude = lt;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double lg) {
        makeSureLongitudeIsValid(lg);
        this.longitude = lg;
    }

}