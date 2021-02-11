package eu.itswc.services;

import eu.itswc.exception.TokenAlreadyExistsException;
import eu.itswc.exception.TokenNotFoundException;
import eu.itswc.model.ThankYouToken;
import eu.itswc.persistance.CoordinatesRepository;
import eu.itswc.persistance.ThankYouTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThankYouTokenService {

    private ThankYouTokenRepository tyTokenRepository;

    ThankYouTokenService(@Autowired ThankYouTokenRepository tr){
        tyTokenRepository = tr;
    }

    public Iterable<ThankYouToken> getAllTokens(){
        return tyTokenRepository.findAll();
    }

    public ThankYouToken getTokenById(String id) {
        makeSureTokenExistsWithId(id);
        return tyTokenRepository.findById(id).get();
    }

    public void addToken(ThankYouToken t){
        makeSureTokenIdIsNotTaken(t.getId());
        tyTokenRepository.save(t);
    }

    public void addCoordinatesIdToToken(Long coordinatesId, String tokenId){
        ThankYouToken t = getTokenById(tokenId);
        t.setInitialCoordinatesId(coordinatesId);
        tyTokenRepository.save(t);
    }

    public void updateToken(ThankYouToken t) {
        makeSureTokenExistsWithId(t.getId());
        tyTokenRepository.save(t);
    }

    public void deleteTokenById(String id) {
        makeSureTokenExistsWithId(id);
        tyTokenRepository.deleteById(id);
    }

    private boolean tokenExistsWithId(String id){
        Optional o = tyTokenRepository.findById(id);
        return o.isPresent();
    }

    private void makeSureTokenExistsWithId(String id){
        if(! tokenExistsWithId(id)){
            throw new TokenNotFoundException(id);
        }
    }

    private void makeSureTokenIdIsNotTaken(String id){
        if(tokenExistsWithId(id)){
            throw new TokenAlreadyExistsException(id);
        }
    }

}
