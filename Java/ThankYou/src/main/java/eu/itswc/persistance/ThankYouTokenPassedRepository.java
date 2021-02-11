package eu.itswc.persistance;

import eu.itswc.model.ThankYouTokenPassed;
import org.springframework.data.repository.CrudRepository;

public interface ThankYouTokenPassedRepository extends CrudRepository<ThankYouTokenPassed, Long> {

    Iterable<ThankYouTokenPassed> findAllThankYouTokenPassedByTokenId(String thankYouTokenId);
    void deleteById(String id);
}
