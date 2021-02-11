package eu.itswc.services;

import eu.itswc.exception.*;
import eu.itswc.model.ThankYouToken;
import eu.itswc.model.ThankYouTokenPassed;
import eu.itswc.persistance.ThankYouTokenPassedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional      // this (for some reason) is required to write custom deleteBy queries
public class ThankYouTokenPassedService {

    private ThankYouTokenPassedRepository tytPassedRepo;

    public ThankYouTokenPassedService(@Autowired ThankYouTokenPassedRepository tpr) {
        tytPassedRepo = tpr;
    }

    public Iterable<ThankYouTokenPassed> getAllTokenPasses() {
        return tytPassedRepo.findAll();
    }

    public Iterable<ThankYouTokenPassed> getAllTokenPassesByTokenId(String tokenId){
        return tytPassedRepo.findAllThankYouTokenPassedByTokenId(tokenId);
    }

    public ThankYouTokenPassed getTytPassedById(Long id){
        makeSureTokenPassedExistsWithId(id);
        return tytPassedRepo.findById(id).get();
    }

    public ThankYouTokenPassed getTytPassedById(String strId){
        Long id = convertStringToLong(strId);
        return getTytPassedById(id);
    }

    public void addTokenPassed(ThankYouTokenPassed tPassed){
        makeSureIdIsNotIncluded(tPassed);
        tytPassedRepo.save(tPassed);
    }

    public void addCoordinatesIdToTokenPassed(Long cId, Long tytPassedId) {
        ThankYouTokenPassed tytPassed = getTytPassedById(tytPassedId);
        tytPassed.setCoordinatesId(cId);
        updateTokenPassed(tytPassedId ,tytPassed);
    }

    public void updateTokenPassed(Long oldId, ThankYouTokenPassed newTPassed) {
        makeSureTokenPassedExistsWithId(oldId);
        addTokenPassed(newTPassed);
        deleteTokenPassedById(oldId);
    }

    public void deleteTokenPassedById(Long tPassedId){
        makeSureTokenPassedExistsWithId(tPassedId);
        tytPassedRepo.deleteById(tPassedId);
    }

    public Map<String, Iterable<ThankYouTokenPassed>> getCompleteHistory(ThankYouTokenService tytService) {
        Map<String, Iterable<ThankYouTokenPassed>> completeHistory = new HashMap<>();
        for (ThankYouToken t : tytService.getAllTokens()){
            completeHistory.put(t.getId(), getAllTokenPassesByTokenId(t.getId()));
        }
        return completeHistory;
    }

    private boolean tokenPassedExistsWhitId(long id){
        return tytPassedRepo.findById(id).isPresent();
    }

    private void makeSureTokenPassedExistsWithId(long id){
        if (! tokenPassedExistsWhitId(id)){
            throw new TokenPassedNotFoundException(id);
        }
    }

    private void makeSureTokenPassedIdIsAvailable(Long id){
        if (tokenPassedExistsWhitId(id)){
            throw new TokenPassedAlreadyExistsException(id);
        }
    }

    private void makeSureIdIsNotIncluded(ThankYouTokenPassed tPassed){
        if (tPassed.getId() != null){
            throw new SuperfluousIDException(tPassed);
        }
    }

    private long convertStringToLong(String id){
        try{
            return Long.parseLong(id);
        } catch (Exception e){
            throw new InvalidIDException(id, ConversionFailedEnum.CAN_NOT_CONVERT_STRING_TO_LONG);
        }
    }
}

