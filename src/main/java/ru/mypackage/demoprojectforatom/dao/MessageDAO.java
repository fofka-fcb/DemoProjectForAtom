package ru.mypackage.demoprojectforatom.dao;

import ru.mypackage.demoprojectforatom.models.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> findAllByIdOfTopic(Integer id);

    Integer lastId();

    Message findOneById(Integer id);

    void save(Message message);

    void update(Message deleteMessage, Message updateMessage);

    void delete(Message deleteMessage);
}
