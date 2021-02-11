package eu.itswc.exception;

import eu.itswc.model.Coordinates;
import eu.itswc.model.ThankYouTokenPassed;

public class SuperfluousIDException extends RuntimeException {

    public SuperfluousIDException(ThankYouTokenPassed tPassed){
        super(String.format("Superfluous Id. When creating a ThankYouTokenPassed event instance do not give and ID," +
                "as that will be auto-generated. Redundant id given: %s.", tPassed.getId()));
    }

    public SuperfluousIDException(Coordinates c) {
        super(String.format("Superfluous Id. When adding coordinates only give 'latitude', and 'longitude' values. " +
                "IDs are to be auto-generated. Redundant id given: %s.", c.getId()));
    }
}
