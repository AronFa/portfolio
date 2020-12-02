package eu.itswc.services;

import eu.itswc.model.ThankYouToken;
import eu.itswc.persistance.ThankYouTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThankYouTokenService {

    private ThankYouTokenRepository thankYouTokenRepository;

    ThankYouTokenService(@Autowired ThankYouTokenRepository tr){
        thankYouTokenRepository = tr;
    }

    public Iterable<ThankYouToken> getAllTokens(){
        return thankYouTokenRepository.findAll();
    }

    public boolean containsTokenWithId(String id){
        try {
            thankYouTokenRepository.findById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean containsToken(ThankYouToken token){
        String id = token.getId();
        return containsTokenWithId(id);
    }

    //TODO : decide what to use: try-catch or optional and implement the choice here as well as on the other services.
    public Optional<ThankYouToken> getTokenById(String id) {
        return thankYouTokenRepository.findById(id);
    }

    public void addToken(ThankYouToken thankYouToken){
        thankYouTokenRepository.save(thankYouToken);
    }

    // TODO: Check if the item exists in the repo prior to the update / delete int this and the other services.
    public void updateToken(ThankYouToken tyToken) {
        thankYouTokenRepository.save(tyToken);
    }

    public void deleteTokenById(String id){
        thankYouTokenRepository.deleteById(id);
    }

}
