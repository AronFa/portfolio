package eu.itswc.exception;

public class TokenPassedNotFoundException extends RuntimeException {

    public TokenPassedNotFoundException(long id){
        super(String.format("TokenPassed event with id: %s not found.", id));
    }

}
