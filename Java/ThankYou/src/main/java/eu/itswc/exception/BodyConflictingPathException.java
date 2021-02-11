package eu.itswc.exception;

import eu.itswc.model.ThankYouToken;

public class BodyConflictingPathException extends RuntimeException {

    public BodyConflictingPathException(ThankYouToken tyt, String id){
        super(String.format("Conflicting Request. " +
                "The id of the token found in the body is: %s, while the id specified in the path is: %s.",
                tyt.getId(), id));
    }
}
