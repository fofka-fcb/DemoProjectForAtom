package ru.mypackage.demoprojectforatom.services;

import ru.mypackage.demoprojectforatom.dto.MessageDTO;
import ru.mypackage.demoprojectforatom.models.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAllByIdOfTopic(Integer id);

    void createMessage(Integer idOfTopic, MessageDTO messageDTO);

    void updateMessage(Integer idOfMessage, MessageDTO messageDTO);

    void deleteMessage(Integer idOfMessage);
}
