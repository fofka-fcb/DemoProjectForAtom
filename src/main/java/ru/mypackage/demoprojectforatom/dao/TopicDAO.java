package ru.mypackage.demoprojectforatom.dao;

import ru.mypackage.demoprojectforatom.models.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDAO {
    List<Topic> findAll();

    Topic findOne(int id);

    Integer lastId();

    Optional<Topic> findOneByName(String name);

    void deleteTopic(Topic deleteTopic);

    void save(Topic topic);

    void update(Topic currentTopic, Topic updateTopic);
}
