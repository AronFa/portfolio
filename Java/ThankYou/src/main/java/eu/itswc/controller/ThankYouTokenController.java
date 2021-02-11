package eu.itswc.controller;

import eu.itswc.exception.BodyConflictingPathException;
import eu.itswc.model.Coordinates;
import eu.itswc.model.ThankYouToken;
import eu.itswc.services.CoordinatesService;
import eu.itswc.services.ThankYouTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class ThankYouTokenController {

    @Autowired
    private ThankYouTokenService tyTokenService;

    @Autowired
    private CoordinatesService coordinatesService;

    @GetMapping("")
    public Iterable<ThankYouToken> getAllTokens(){
        return tyTokenService.getAllTokens();
    }

    @GetMapping("/{id}")
    public ThankYouToken getTokenById(@PathVariable String id){
        return tyTokenService.getTokenById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> addToken(@RequestBody ThankYouToken t){
        tyTokenService.addToken(t);
        return new ResponseEntity<String>("token added", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateToken(@PathVariable String id, @RequestBody ThankYouToken t) {
        makeSureBodyDoesNotConflictIdInPath(t, id);
        tyTokenService.updateToken(t);
        return new ResponseEntity<>("token updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToken(@PathVariable String id){
        tyTokenService.deleteTokenById(id);
        return new ResponseEntity<>("token deleted", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    // TODO: Why am i not getting the custom exception message when the req body contains invalid coordinates?
    public ResponseEntity<String> addCoordinates (@PathVariable("id") String tokenId, @RequestBody Coordinates c) {
        Long cId = coordinatesService.saveCoordinates(c);
        tyTokenService.addCoordinatesIdToToken(cId, tokenId);
        return new ResponseEntity<>("coordinates added", HttpStatus.OK);
    }

    private void makeSureBodyDoesNotConflictIdInPath(ThankYouToken t, String id) {
        if (! t.getId().equals(id)){
            throw new BodyConflictingPathException(t, id);
        }
    }


}
