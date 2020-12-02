package eu.itswc.persistance;

import eu.itswc.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findFirstByUserName(String userName);
}
