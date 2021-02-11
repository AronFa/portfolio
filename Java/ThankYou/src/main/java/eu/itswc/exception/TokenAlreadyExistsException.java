package eu.itswc.exception;

public class TokenAlreadyExistsException extends RuntimeException{

    public TokenAlreadyExistsException(String id){
        super(String.format("Token with id: %s already exists. To edit an existing token use HTTP Method PUT", id));
    }
}
