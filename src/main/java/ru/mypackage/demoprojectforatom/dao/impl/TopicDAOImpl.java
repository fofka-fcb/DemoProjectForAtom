package ru.mypackage.demoprojectforatom.dao.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import ru.mypackage.demoprojectforatom.dao.TopicDAO;
import ru.mypackage.demoprojectforatom.exceptions.TopicNotFoundException;
import ru.mypackage.demoprojectforatom.models.Topic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class TopicDAOImpl implements TopicDAO {

    List<Topic> listOfTopics = new ArrayList<>();

    {
        listOfTopics.add(new Topic(1, "Cars", List.of(1, 2, 3)));
        listOfTopics.add(new Topic(2, "Vegetables", List.of(4, 5, 6)));
        listOfTopics.add(new Topic(3, "Phones", List.of(7)));
    }

    public List<Topic> findAll() {
        return listOfTopics;
    }

    public Topic findOne(int id) {
        return listOfTopics.stream().filter(t -> t.getId().equals(id)).findFirst()
                .orElseThrow(() -> new TopicNotFoundException("Topic not found!", HttpStatus.NOT_FOUND));
    }

    public Optional<Topic> findOneByName(String name) {
        return listOfTopics.stream().filter(t -> t.getName().equals(name)).findFirst();
    }

    public Integer lastId() {
        return listOfTopics.stream().max(Comparator.comparing(Topic::getId)).get().getId() + 1;
    }

    public void save(Topic topic) {
        listOfTopics.add(topic);
    }

    public void update(Topic currentTopic, Topic updateTopic) {
        listOfTopics.remove(currentTopic);
        listOfTopics.add(updateTopic);
    }

    public void deleteTopic(Topic deleteTopic) {
        listOfTopics.remove(deleteTopic);
    }

}
