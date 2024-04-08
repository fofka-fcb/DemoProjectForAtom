package ru.mypackage.demoprojectforatom.services;

import ru.mypackage.demoprojectforatom.dto.CreateTopicDTO;
import ru.mypackage.demoprojectforatom.models.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAll();

    void createTopicAndMessage(CreateTopicDTO createTopicDTO);
}
