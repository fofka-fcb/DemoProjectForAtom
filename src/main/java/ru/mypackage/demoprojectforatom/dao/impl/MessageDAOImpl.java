package ru.mypackage.demoprojectforatom.dao.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import ru.mypackage.demoprojectforatom.dao.MessageDAO;
import ru.mypackage.demoprojectforatom.exceptions.MessageNotFoundException;
import ru.mypackage.demoprojectforatom.exceptions.TopicNotFoundException;
import ru.mypackage.demoprojectforatom.models.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    List<Message> messageList = new ArrayList<>();

    {
        messageList.add(new Message(1, 1, "user_1", "some message about cars", new Date()));
        messageList.add(new Message(2, 1, "user_1", "some message about cars", new Date()));
        messageList.add(new Message(3, 1, "user_3", "some message about cars", new Date()));

        messageList.add(new Message(4, 2, "user_2", "some message about vegetables", new Date()));
        messageList.add(new Message(5, 2, "user_3", "some message about vegetables", new Date()));
        messageList.add(new Message(6, 2, "user_3", "some message about vegetables", new Date()));

        messageList.add(new Message(7, 3, "user_1", "some message about phones", new Date()));
    }

    public List<Message> findAllByIdOfTopic(Integer id) {
        List list = messageList.stream().filter(m -> m.getIdOfTopic().equals(id)).toList();
        if (list.isEmpty())
            throw new TopicNotFoundException("Topic and messages not found!", HttpStatus.NOT_FOUND);
        else return list;
    }

    public Integer lastId() {
        return messageList.stream().max(Comparator.comparing(Message::getId)).get().getId() + 1;
    }

    public void save(Message message) {
        messageList.add(message);
    }

    public Message findOneById(Integer id) {
        return messageList.stream().filter(m -> m.getId().equals(id)).findFirst()
                .orElseThrow(() -> new MessageNotFoundException("Message with this id not found!", HttpStatus.NOT_FOUND));
    }

    public void update(Message deleteMessage, Message updateMessage) {
        messageList.remove(deleteMessage);
        messageList.add(updateMessage);
    }

    public void delete(Message deleteMessage) {
        messageList.remove(deleteMessage);
    }
}
