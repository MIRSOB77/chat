package chat.repository;

import chat.repository.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Repository
public interface PersonCrudRepository extends CrudRepository<Person, Long> {

}
