package eu.itswc.exception;

public class TokenPassedAlreadyExistsException extends RuntimeException {

    public TokenPassedAlreadyExistsException(long id){
        super(String.format("A TokenPassed record already exists with id: %s. \n" +
                            "If you wanted to create a new TokenPassed event no id is needed, it's auto generated. \n ",
                            id));
    }
}
