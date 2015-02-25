package chat.rest;

import org.apache.commons.collections.map.LinkedMap;
import chat.repository.entity.Person;
import chat.service.UserManagementService;
import chat.service.exception.UserManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
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
    public void registerUser(@RequestBody Person userdata) throws UserManagementException{
        userManagementService.registerUser(userdata);
    }

    @RequestMapping("/person/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public void nicknameExists(@PathVariable("nickname") String nickname) throws UserManagementException {
        userManagementService.checkNicknameIsFree(nickname);
    }

    @ExceptionHandler(value = { UserManagementException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(){
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("message", "validation error");
        return errors;
    }

}
