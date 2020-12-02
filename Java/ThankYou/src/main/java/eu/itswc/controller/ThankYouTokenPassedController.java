package eu.itswc.controller;

import eu.itswc.model.ThankYouToken;
import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.services.ThankYouTokenPassedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tokenPassed")
public class ThankYouTokenPassedController {

    @Autowired
    private ThankYouTokenPassedService tytPassedService;

    @GetMapping("")
    public Iterable<ThankYouTokenPassed> getAllTokenPasses() {
        return tytPassedService.getAllTokenPasses();
    }

    @PostMapping("")
    public void addTokenPass(@RequestBody ThankYouTokenPassed tytPassed) {
        tytPassedService.addTokenPassed(tytPassed);
    }

    @PutMapping("/{id}")
    public void updateTokenPass (@PathVariable String id, @RequestBody ThankYouTokenPassed tytPassed) {
        tytPassedService.updateTonekPassed(tytPassed);
    }

    @DeleteMapping("/{id}")
    public void deleteTokenHandOver(@PathVariable String id ) {
        tytPassedService.deleteTokenPassedById(id);
    }

}
