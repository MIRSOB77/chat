package chat.service;

import chat.repository.MessageRepository;
import chat.service.exception.ChatException;
import chat.utils.JsonResponseMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import chat.repository.PersonCrudRepository;
import chat.repository.PersonRepository;
import chat.repository.entity.ChatMessage;
import chat.repository.entity.User;
import chat.service.exception.UserManagementException;
import chat.client.RawMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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


    public Long send(ChatMessage message) throws ChatException {
        ChatMessage newMsg = null;
        newMsg = messageRepository.save(message);

        if(!message.getReceiver().getOnline()){
            newMsg.setNewMsg(Boolean.TRUE);
        }
        messageRepository.save(newMsg);

        return newMsg.getId();
    }

    public List<JsonResponseMap> getAll() throws ChatException {
        List<JsonResponseMap> jsonMessages = new ArrayList<JsonResponseMap>();
        Iterable<ChatMessage> allMessages = messageRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        for(ChatMessage p : allMessages){
            jsonMessages.add(objectMapper.convertValue(p, JsonResponseMap.class));
        }
        return jsonMessages;
    }


    public ChatMessage transformMessage(RawMessage message) throws UserManagementException, InvocationTargetException, IllegalAccessException {
        ChatMessage chatMessage = new ChatMessage();

        User sender = personSearchRepository.findByNickname(message.getSender());

        if(sender==null){
            throw new UserManagementException("user with nickname doesn't exist");
        }

        chatMessage.setSender(sender);


        User receiver = personSearchRepository.findByNickname(message.getReceiver());

        if(receiver==null){
            throw new UserManagementException("user with nickname doesn't exist");
        }

        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);

        chatMessage.setText(message.getText());
        return chatMessage;
    }
}
