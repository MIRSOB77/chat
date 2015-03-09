package chat.rest;

import chat.client.UserLogin;
import chat.repository.entity.User;
import chat.service.UserManagementService;
import chat.service.exception.UserManagementException;
import chat.utils.JsonResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirsad on 23.02.2015.
 */
@RestController
public class UserManagementRestController {
    @Autowired
    UserManagementService userManagementService;

    @RequestMapping(value = "/person/register", method = RequestMethod.POST )
    @ResponseStatus(HttpStatus.OK)
    public JsonResponseMap registerUser(@RequestBody User userdata) throws UserManagementException{
        JsonResponseMap jsonResponse = new JsonResponseMap();
        jsonResponse.put("success", Boolean.TRUE);
        userManagementService.registerUser(userdata);

        return jsonResponse;
    }

    @RequestMapping("/person/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> nicknameExists(@PathVariable("nickname") String nickname) throws UserManagementException {
        Map<String,Object> response = new HashMap();
        response.put("success", Boolean.FALSE);
        if(userManagementService.checkNicknameIsFree(nickname)){
            response.put("success", Boolean.TRUE);
        }
        return response;
    }

    @RequestMapping("/person/register")
    @ResponseStatus(HttpStatus.OK)
    public JsonResponseMap registerUser(@RequestBody UserLogin userLogin) throws UserManagementException{
        JsonResponseMap logonResponse = userManagementService.logonUser(userLogin.getNickname(), userLogin.getPassword());

        userManagementService.dispatchUserStatusChangeEvent(userLogin.getNickname(), Boolean.TRUE);

        return logonResponse;
    }


    @RequestMapping("/person/login")
    @ResponseStatus(HttpStatus.OK)
    public JsonResponseMap loginUser(@RequestBody UserLogin userLogin) throws UserManagementException{
        JsonResponseMap logonResponse = userManagementService.logonUser(userLogin.getNickname(), userLogin.getPassword());

        userManagementService.dispatchUserStatusChangeEvent(userLogin.getNickname(), Boolean.TRUE);

        return logonResponse;
    }

    @ExceptionHandler(value = { UserManagementException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(Exception ex){
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("message", ex.getMessage());
        return errors;
    }

}
