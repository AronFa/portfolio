package eu.itswc.exception;

public class InvalidLatitudeException extends RuntimeException {

    public InvalidLatitudeException(double lt){

        super(String.format("Invalid Latitude. Latitude must be between -90 and 90. Latitude given: %s", lt));
    }
}
