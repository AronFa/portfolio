package eu.itswc.exception;

import eu.itswc.model.DateStuff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity handleTokenNotFoundException(TokenNotFoundException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BodyConflictingPathException.class)
    public ResponseEntity handleBodyConflictingPathException(BodyConflictingPathException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenAlreadyExistsException.class)
    public ResponseEntity handleTokenAlreadyExistsException(TokenAlreadyExistsException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenPassedAlreadyExistsException.class)
    public ResponseEntity handleTokenPassedAlreadyExistsException(TokenPassedAlreadyExistsException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SuperfluousIDException.class)
    public ResponseEntity handleSuperfluousIDException (SuperfluousIDException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity hadleInvalidIDException (InvalidIDException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongHttpMethodException.class)
    public ResponseEntity handleWrongHttpMethodException (WrongHttpMethodException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLatitudeException.class)
    public ResponseEntity handleInvalidLatitudeEcxeption (InvalidLatitudeException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLongitudeException.class)
    public ResponseEntity handleInvalidLongitudeEcxeption (InvalidLongitudeException ex, WebRequest req){
        Map<String, String> body = getMapWithTimeStampAndExceptionMessage(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getMapWithTimeStampAndExceptionMessage(Exception ex){
        Map<String, String> b = new LinkedHashMap<>();
        b.put("timeStampUTC: ", DateStuff.getNowUTC());
        b.put("message: ", ex.getMessage());
        // TODO: figure out how to post the path here.
        return b;
    }

}
