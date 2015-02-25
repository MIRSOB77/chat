package chat.repository;

import chat.repository.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Mirsad on 22.02.2015.
 */
@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    Person findByNickname(String nickname);
    Person findByNicknameAndPassword(String nickname, String password);
}