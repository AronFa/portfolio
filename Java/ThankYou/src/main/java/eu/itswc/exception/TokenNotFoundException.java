package eu.itswc.exception;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(String id){
        super(String.format("Token with id: %s not found.", id));
    }
}
