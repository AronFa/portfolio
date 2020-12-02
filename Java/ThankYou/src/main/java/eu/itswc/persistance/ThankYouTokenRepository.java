package eu.itswc.persistance;

import eu.itswc.model.ThankYouToken;
import org.springframework.data.repository.CrudRepository;

public interface ThankYouTokenRepository extends CrudRepository<ThankYouToken, String> {
}
