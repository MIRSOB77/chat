package chat.rest;

import chat.repository.entity.ChatMessage;
import chat.repository.entity.Person;
import chat.service.MessageService;
import chat.service.UserManagementService;
import chat.service.exception.ChatException;
import chat.service.exception.UserManagementException;
import chat.utils.ChatRole;
import chat.utils.RawMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/message/send/{receiver}",
                    method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> sendMessage(@PathVariable("receiver") String receiver, @RequestBody RawMessage messageJson)
            throws IllegalAccessException, InvocationTargetException, UserManagementException, ChatException {

        Logger.getRootLogger().error(messageJson);

        ChatMessage chatMessage = messageService.transformMessage(messageJson);



        messageService.send(receiver, chatMessage);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");

        return new ResponseEntity<Boolean>(Boolean.TRUE, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/messages",
            method = RequestMethod.GET)
    public ResponseEntity<List<Map<String,String>>> showAllMessages()
            throws IllegalAccessException, InvocationTargetException, UserManagementException, ChatException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");

        return new ResponseEntity<List<Map<String,String>>>(messageService.getAll(), HttpStatus.CREATED);

    }



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
