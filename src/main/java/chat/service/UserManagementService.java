package chat.service;

import chat.messaging.ChatClientNotificationUserStatus;
import chat.utils.JsonResponseMap;
import org.apache.commons.lang.time.DateUtils;
import chat.repository.PersonCrudRepository;
import chat.repository.PersonRepository;
import chat.repository.entity.User;
import chat.service.exception.UserManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    @Value("${myproject.sso.token}")
    String ssoToken;

    public User registerUser(User user) throws UserManagementException{
        Assert.notNull(user);

        Date eighteenYearsBefore = new Date();
        eighteenYearsBefore = DateUtils.addYears(eighteenYearsBefore, 18);

        if(user.getBirth().after(eighteenYearsBefore)) {
            throw new UserManagementException("User must be at least 18 years old");
        }

        user = personStoreRepository.save(user);
        return user;
    }

    public Boolean checkNicknameIsFree(String nickname) {
        Assert.hasText(nickname);

        if(personSearchRepository.findByNickname(nickname) == null){
            return false;
        }
        return true;
    }

    private Boolean userExists(String nickname) {
        Assert.hasText(nickname);

        if(personSearchRepository.findByNickname(nickname) == null){
            return false;
        }
        return true;
    }


    public JsonResponseMap logonUser(String username, String password) throws UserManagementException {
        Assert.hasText(username,"Username missing");
        Assert.hasText(password,"Password missing");

        User loggedUser = personSearchRepository.findByNicknameAndPassword(username, password);

        if(loggedUser == null){
            throw new UserManagementException("authentication failed");
        }
        loggedUser.setOnline(true);
        loggedUser.setSsoToken(loggedUser.getId() + ssoToken);
        personStoreRepository.save(loggedUser);

        JsonResponseMap logonResponse = new JsonResponseMap();
        logonResponse.put("ssoToken", loggedUser.getSsoToken());


        return logonResponse;
    }


//    public JsonResponseMap logoutUser(String loginToken) throws UserManagementException {
//
//        User loggedUser = personSearchRepository.findByT(ssoToken);
//
//        if(loggedUser == null){
//            throw new UserManagementException("authentication failed");
//        }
//        loggedUser.setOnline(true);
//        loggedUser.setSsoToken(loggedUser.getId() + ssoToken);
//        personStoreRepository.save(loggedUser);
//
//        JsonResponseMap logonResponse = new JsonResponseMap();
//        logonResponse.put("ssoToken", loggedUser.getSsoToken());
//
//
//        return logonResponse;
//    }


    public void dispatchUserStatusChangeEvent(String nickname, Boolean isOnline){
        Assert.isTrue(userExists(nickname), "no event dispatched because user not found");

        simpMessagingTemplate.convertAndSend("/topic/userStatus", new ChatClientNotificationUserStatus(nickname, isOnline));
    }
}
