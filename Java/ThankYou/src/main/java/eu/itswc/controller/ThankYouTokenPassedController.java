package eu.itswc.controller;

import eu.itswc.model.Coordinates;
import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.services.CoordinatesService;
import eu.itswc.services.ThankYouTokenPassedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tokenPassed")
public class ThankYouTokenPassedController {

    @Autowired
    private ThankYouTokenPassedService tytPassedService;

    @Autowired
    private CoordinatesService coordinatesService;

    @GetMapping("")
    public Iterable<ThankYouTokenPassed> getAllTokenPasses() {
        return tytPassedService.getAllTokenPasses();
    }

    @GetMapping("/{id}")
    public ThankYouTokenPassed getTytPassedWithId(@PathVariable String id){
        return tytPassedService.getTytPassedById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> addTokenPass(@RequestBody ThankYouTokenPassed tytPassed) {
        tytPassedService.addTokenPassed(tytPassed);
        return new ResponseEntity<>(String.format("TokenPassed event recorded with id: %s, on token: %s",
                tytPassed.getId(), tytPassed.getTokenId()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void updateTokenPass (@PathVariable Long id, @RequestBody ThankYouTokenPassed tytPassed) {
        tytPassedService.updateTokenPassed(id, tytPassed);
    }

    @DeleteMapping("/{id}")
    public void deleteTokenHandOver(@PathVariable long id ) {
        tytPassedService.deleteTokenPassedById(id);
    }

    @PostMapping
    public ResponseEntity<String> addCoordinates (@PathVariable("id") Long tPassedId, @RequestBody Coordinates c) {
        Long cId = coordinatesService.saveCoordinates(c);
        tytPassedService.addCoordinatesIdToTokenPassed(cId, tPassedId);
        return new ResponseEntity<>("coordinates added", HttpStatus.OK);
    }

}
