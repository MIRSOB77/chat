package chat.repository;

import chat.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Repository
public interface PersonCrudRepository extends CrudRepository<User, Long> {

}
