package eu.itswc.controller;

import eu.itswc.model.Location;
import eu.itswc.model.ThankYouToken;
import eu.itswc.services.*;
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
    private LocationService locationService;

    @GetMapping("")
    public Iterable<ThankYouToken> getAllTokens(){
        return tyTokenService.getAllTokens();
    }

    //TODO: i don't like how the error handling is making the code a harder to read,
    //      maybe i should take a different approach to exception handling.

    @PostMapping("")
    public ResponseEntity<String> addToken(@RequestBody ThankYouToken token){
        if (! tyTokenService.containsToken(token)) {
            tyTokenService.addToken(token);
            return new ResponseEntity<>("token added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("tokenId taken (to edit tokens use PUT on /token/id)", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    // TODO: i don't like it being <Object>, maybe convert it to JSON manually? or do something with Optional<Token>?
    public ResponseEntity<Object> getTokenById(@PathVariable String id){
        if (tyTokenService.containsTokenWithId(id)) {
            return new ResponseEntity<Object>(tyTokenService.getTokenById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("tokenId does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateToken(@PathVariable String id, @RequestBody ThankYouToken token) {
        if (tyTokenService.containsTokenWithId(id) && token.getId().equals(id)){
            tyTokenService.updateToken(token);
            return new ResponseEntity<>("token updated", HttpStatus.OK);
        } else if(token.getId().equals(id)) {
            return new ResponseEntity<>("tokenId does not exist (to create token use POST on /token)", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("id in request body does not match the one in the path", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToken(@PathVariable String id){
        if (tyTokenService.containsTokenWithId(id)) {
            tyTokenService.deleteTokenById(id);
            return new ResponseEntity<>("token deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("tokenId does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/location")
    public ResponseEntity<String> addLocation(@PathVariable("id") String tokenId, @RequestBody Location loc) {
        try {
            ThankYouToken t = tyTokenService.getTokenById(tokenId).orElseThrow(NoSuchFieldError::new);
            if (Location.locationIsValid(loc)) {
                locationService.addLocation(loc);
                t.setInitialLocationId(locationService.getLocationIdByInstance(loc));
                return new ResponseEntity<>("location added", HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("invalid location");
            }
        } catch (NoSuchFieldError e) {
            return new ResponseEntity<>("tokenId does not exist (to create token use POST on /token)", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>("invalid location ( -90 <= Lat <= 90; 0 <= Long <=360)", HttpStatus.BAD_REQUEST);
        }
    }
}
