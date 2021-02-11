package eu.itswc.exception;

public class InvalidLongitudeException extends RuntimeException {

    public InvalidLongitudeException(double lg){

        super(String.format("Invalid Longitude. Longitude must be between 0 and 360. Longitude given: %s", lg));
    }
}
