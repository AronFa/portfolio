package eu.itswc.exception;

public class CoordinatesNotFoundException extends RuntimeException {

    public CoordinatesNotFoundException(Long id){

        super(String.format("Coordinates not found with id: %s", id));
    }
}
