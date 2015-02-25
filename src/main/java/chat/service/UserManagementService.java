package chat.service;

import org.apache.commons.lang.time.DateUtils;
import chat.repository.PersonCrudRepository;
import chat.repository.PersonRepository;
import chat.repository.PersonRepository;
import chat.repository.entity.Person;
import chat.service.exception.UserManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Service
public class UserManagementService {
    @Autowired
    PersonRepository personSearchRepository;

    @Autowired
    PersonCrudRepository personStoreRepository;

    @Value("${myproject.sso.token}")
    String ssoToken;

    public Person registerUser(Person person) throws UserManagementException{
        Assert.notNull(person);

        Date eighteenYearsBefore = new Date();
        eighteenYearsBefore = DateUtils.addYears(eighteenYearsBefore, 18);

        if(!person.getBirth().before(eighteenYearsBefore)) {
            throw new UserManagementException("User must be at least 18 years old");
        }
        person = personStoreRepository.save(person);
        return person;
    }

    public Boolean checkNicknameIsFree(String nickname) throws UserManagementException {
        Assert.hasText(nickname);

        if(personSearchRepository.findByNickname(nickname) == null){
            throw new UserManagementException("nickname doesnt exist");
        }
        return true;
    }


    public String logonUser(String username, String password){
        Assert.hasText(username,"Username missing");
        Assert.hasText(password,"Password missing");

        Person loggedPerson = personSearchRepository.findByNicknameAndPassword(username, password);
        loggedPerson.setOnline(true);
        loggedPerson.setSsoToken(loggedPerson.getId() + ssoToken);
        personStoreRepository.save(loggedPerson);
        return ssoToken;
    }



}
