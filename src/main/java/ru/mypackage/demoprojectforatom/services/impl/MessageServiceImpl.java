package ru.mypackage.demoprojectforatom.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.mypackage.demoprojectforatom.dao.MessageDAO;
import ru.mypackage.demoprojectforatom.dao.TopicDAO;
import ru.mypackage.demoprojectforatom.dto.MessageDTO;
import ru.mypackage.demoprojectforatom.exceptions.UsernameIsNotValidException;
import ru.mypackage.demoprojectforatom.models.Message;
import ru.mypackage.demoprojectforatom.models.Topic;
import ru.mypackage.demoprojectforatom.services.MessageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDAO messageDAO;
    private final TopicDAO topicDAO;
    private final ModelMapper modelMapper;


    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO,
                              TopicDAO topicDAO,
                              ModelMapper modelMapper) {
        this.messageDAO = messageDAO;
        this.topicDAO = topicDAO;
        this.modelMapper = modelMapper;
    }

    public List<Message> findAllByIdOfTopic(Integer id) {
        return messageDAO.findAllByIdOfTopic(id);
    }

    public void createMessage(Integer idOfTopic, MessageDTO messageDTO) {
        Message createMessage = convertToMessage(messageDTO);

        Topic topicFromRepo = topicDAO.findOne(idOfTopic);
        Topic updateTopic = (Topic) topicFromRepo.clone();

        createMessage.setId(messageDAO.lastId());
        createMessage.setIdOfTopic(topicFromRepo.getId());
        createMessage.setCreatedAt(new Date());

        List listOfMessage = new ArrayList(topicFromRepo.getListOfMessages());
        listOfMessage.add(createMessage.getId());

        updateTopic.setListOfMessages(listOfMessage);

        messageDAO.save(createMessage);
        topicDAO.update(topicFromRepo, updateTopic);
    }

    public void updateMessage(Integer idOfMessage, MessageDTO messageDTO) {
        Message updateMessage = convertToMessage(messageDTO);
        Message messageFromRepo = messageDAO.findOneById(idOfMessage);

        if (!updateMessage.getUsername().equals(messageFromRepo.getUsername()))
            throw new UsernameIsNotValidException("Username is not valid!", HttpStatus.BAD_REQUEST);

        updateMessage.setId(messageFromRepo.getId());
        updateMessage.setIdOfTopic(messageFromRepo.getIdOfTopic());
        updateMessage.setCreatedAt(messageFromRepo.getCreatedAt());

        messageDAO.update(messageFromRepo, updateMessage);
    }

    public void deleteMessage(Integer idOfMessage) {
        Message messageFromRepo = messageDAO.findOneById(idOfMessage);

        Topic topicFromRepo = topicDAO.findOne(messageFromRepo.getIdOfTopic());
        Topic updateTopic = (Topic) topicFromRepo.clone();

        List listOfMessage = new ArrayList(topicFromRepo.getListOfMessages());
        listOfMessage.remove(messageFromRepo.getId());

        if (listOfMessage.isEmpty()) {
            topicDAO.deleteTopic(topicFromRepo);
            messageDAO.delete(messageFromRepo);
        } else {
            updateTopic.setListOfMessages(listOfMessage);
            messageDAO.delete(messageFromRepo);
            topicDAO.update(topicFromRepo, updateTopic);
        }
    }

    private Message convertToMessage(MessageDTO messageDTO) {
        return modelMapper.map(messageDTO, Message.class);
    }
}
