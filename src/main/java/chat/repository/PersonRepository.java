package chat.repository;

import chat.repository.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Mirsad on 22.02.2015.
 */
@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<User, Long> {
    List<User> findByFirstName(String firstName);
    User findByNickname(String nickname);
    User findByNicknameAndPassword(String nickname, String password);
   // User findByBySSOToken(sstoken)
}