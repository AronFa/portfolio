package eu.itswc.services;

import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.persistance.ThankYouTokenPassedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThankYouTokenPassedService {

    private ThankYouTokenPassedRepository TytPassedRepo;

    public ThankYouTokenPassedService(@Autowired ThankYouTokenPassedRepository tpr) {
        TytPassedRepo = tpr;
    }

    public Iterable<ThankYouTokenPassed> getAllTokenPasses() {
        return TytPassedRepo.findAll();
    }

    public Iterable<ThankYouTokenPassed> getAllTokenPassesByTokenId(String tokenId){
        return TytPassedRepo.findAllThankYouTokenPassedByTokenId(tokenId);
    }

    public void addTokenPassed(ThankYouTokenPassed thankYouTokenPassed){
        TytPassedRepo.save(thankYouTokenPassed);
    }

    public void updateTonekPassed(ThankYouTokenPassed thankYouTokenPassed) {
        TytPassedRepo.save(thankYouTokenPassed);
    }

    public void deleteTokenPassedById(String thankYouTokenPassedId){
        TytPassedRepo.deleteById(thankYouTokenPassedId);
    }

}
