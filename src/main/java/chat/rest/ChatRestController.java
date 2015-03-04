package chat.rest;

import chat.repository.entity.ChatMessage;
import chat.service.MessageService;
import chat.service.exception.ChatException;
import chat.service.exception.UserManagementException;
import chat.messaging.ChatClientNotification;
import chat.messaging.ChatClientNotificationUserMessage;
import chat.client.RawMessage;
import chat.utils.JsonResponseMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mirsad on 22.02.2015.
 */
@RestController
public class ChatRestController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/messages",
            method = RequestMethod.GET)
    public ResponseEntity<List<JsonResponseMap>> showAllMessages()
            throws IllegalAccessException, InvocationTargetException, UserManagementException, ChatException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");

        return new ResponseEntity<List<JsonResponseMap>>(messageService.getAll(), HttpStatus.OK);
    }


//    @RequestMapping(value = "/send",
//            method = RequestMethod.POST)
//    public JsonResponseMap sendSingleMessage(@RequestBody RawMessage rawMessage)
//            throws IllegalAccessException, InvocationTargetException, UserManagementException, ChatException {
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("MyResponseHeader", "MyValue");
//
//        return new ResponseEntity<List<JsonResponseMap>>(, HttpStatus.OK);
//    }


    @ExceptionHandler(value = { UserManagementException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(UserManagementException uEx){
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("message", uEx.getMessage() );
        return errors;
    }

    @ExceptionHandler(value = { ChatException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSendException(ChatException chatEx){
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("message", chatEx.getMessage());
        return errors;
    }


}
