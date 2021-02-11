package eu.itswc.controller;

import eu.itswc.model.ThankYouToken;
import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.services.ThankYouTokenPassedService;
import eu.itswc.services.ThankYouTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class ThankYouTokenHistoryController {

    @Autowired
    private ThankYouTokenPassedService tyTokenPassedServoce;
    @Autowired
    private ThankYouTokenService tyTokenService;

    @GetMapping("")
    public Map<String, Iterable<ThankYouTokenPassed>> getAllTokensHistory(){
        return tyTokenPassedServoce.getCompleteHistory(tyTokenService);
    }

    @GetMapping("/{tokenId}")
    public Iterable<ThankYouTokenPassed> getTokensHistoryById(@PathVariable("tokenId") String tokenId) {
        return tyTokenPassedServoce.getAllTokenPassesByTokenId(tokenId);
    }
}
