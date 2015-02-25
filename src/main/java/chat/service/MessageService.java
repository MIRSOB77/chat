package chat.service;

import chat.repository.MessageRepository;
import chat.service.exception.ChatException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.apache.commons.beanutils.BeanUtils;
import chat.repository.PersonCrudRepository;
import chat.repository.PersonRepository;
import chat.repository.entity.ChatMessage;
import chat.repository.entity.Person;
import chat.service.exception.UserManagementException;
import chat.utils.RawMessage;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mirsad on 22.02.2015.
 */
@Service
public class MessageService{
    @Autowired
    PersonRepository personSearchRepository;

    @Autowired
    PersonCrudRepository personRepository;

    @Autowired
    MessageRepository messageRepository;


    public void send(String receiver, ChatMessage message) throws ChatException {
        Person receiverObj = personSearchRepository.findByNickname(receiver);
        if(receiverObj == null){
            throw new ChatException();
        }

        message.setReceiver(receiverObj);

        messageRepository.save(message);

        if(message.getReceiver().getOnline()){

        } else {

        }
    }


    public List<Map<String,String>> getAll() throws ChatException {
        List<Map<String,String>> jsonMessages = new ArrayList<Map<String,String>>();
        Iterable<ChatMessage> allMessages = messageRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        for(ChatMessage p : allMessages){
            jsonMessages.add(objectMapper.convertValue(p, Map.class));
        }
        return jsonMessages;
    }


    public ChatMessage transformMessage(RawMessage message) throws UserManagementException, InvocationTargetException, IllegalAccessException {
        ChatMessage chatMessage = new ChatMessage();

        Person sender = personSearchRepository.findByNickname(message.getSender());

        if(sender==null){
            throw new UserManagementException("user with nickname doesn't exist");
        }

        chatMessage.setSender(sender);

        chatMessage.setText(message.getText());



        return chatMessage;

    }
}
