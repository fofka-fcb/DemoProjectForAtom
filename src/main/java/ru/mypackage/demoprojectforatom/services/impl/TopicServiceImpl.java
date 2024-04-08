package ru.mypackage.demoprojectforatom.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mypackage.demoprojectforatom.dao.MessageDAO;
import ru.mypackage.demoprojectforatom.dao.TopicDAO;
import ru.mypackage.demoprojectforatom.dto.CreateTopicDTO;
import ru.mypackage.demoprojectforatom.dto.MessageDTO;
import ru.mypackage.demoprojectforatom.dto.TopicDTO;
import ru.mypackage.demoprojectforatom.models.Message;
import ru.mypackage.demoprojectforatom.models.Topic;
import ru.mypackage.demoprojectforatom.services.TopicService;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicDAO topicDAO;
    private final MessageDAO messageDAO;
    private final ModelMapper modelMapper;


    @Autowired
    public TopicServiceImpl(TopicDAO topicDAO,
                            MessageDAO messageDAO,
                            ModelMapper modelMapper) {
        this.topicDAO = topicDAO;
        this.messageDAO = messageDAO;
        this.modelMapper = modelMapper;
    }

    public List<Topic> findAll() {
        return topicDAO.findAll();
    }

    public void createTopicAndMessage(CreateTopicDTO createTopicDTO) {
        Topic createTopic = convertToTopic(createTopicDTO.getTopicDTO());
        Message createMessage = convertToMessage(createTopicDTO.getMessageDTO());

        topicDAO.findOneByName(createTopic.getName());

        createTopic.setId(topicDAO.lastId());
        createMessage.setId(messageDAO.lastId());
        createMessage.setIdOfTopic(createTopic.getId());
        createMessage.setCreatedAt(new Date());
        createTopic.setListOfMessages(List.of(createMessage.getId()));

        topicDAO.save(createTopic);
        messageDAO.save(createMessage);
    }

    private Topic convertToTopic(TopicDTO topicDTO) {
        return modelMapper.map(topicDTO, Topic.class);
    }

    private Message convertToMessage(MessageDTO messageDTO) {
        return modelMapper.map(messageDTO, Message.class);
    }
}
