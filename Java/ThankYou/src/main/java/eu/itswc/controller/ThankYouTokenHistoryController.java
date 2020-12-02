package eu.itswc.controller;

import eu.itswc.model.ThankYouToken;
import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.persistance.ThankYouTokenPassedRepository;
import eu.itswc.persistance.ThankYouTokenRepository;
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

    // TODO: it accesses the repo, i feel like that's a mistake, should look into it
    @Autowired
    private ThankYouTokenRepository tyTokenRepo;
    @Autowired
    private ThankYouTokenPassedRepository tytPassedRepo;

    @GetMapping("")
    public Map<String, Iterable<ThankYouTokenPassed>> getAllTokensHistory(){
        Map<String, Iterable<ThankYouTokenPassed>> historyLibrary = new HashMap<>();
        Iterable<ThankYouToken> tyTokens = tyTokenRepo.findAll();
        for (ThankYouToken token : tyTokens) {
            String tokenID = token.getId();
            Iterable<ThankYouTokenPassed> thisTokensPassedList = tytPassedRepo.findAllThankYouTokenPassedByTokenId(tokenID);
            historyLibrary.put(tokenID, thisTokensPassedList);
        }
        return historyLibrary;
    }

    @GetMapping("/{tokenId}")
    public Iterable<ThankYouTokenPassed> getTokensHistoryById(@PathVariable("tokenId") String tokenId) {
        return tytPassedRepo.findAllThankYouTokenPassedByTokenId(tokenId);
    }





}
