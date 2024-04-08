package ru.mypackage.demoprojectforatom.models;

import java.util.List;

public class Topic implements Cloneable {

    private Integer id;
    private String name;
    private List<Integer> listOfMessagesId;

    public Topic() {
    }

    public Topic(Integer id, String name, List<Integer> listOfMessages) {
        this.id = id;
        this.name = name;
        this.listOfMessagesId = listOfMessages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getListOfMessages() {
        return listOfMessagesId;
    }

    public void setListOfMessages(List<Integer> listOfMessages) {
        this.listOfMessagesId = listOfMessages;
    }

    @Override
    public Object clone() {
        Topic topic = null;
        try {
            topic = (Topic) super.clone();
        } catch (CloneNotSupportedException e) {
            topic.setId(this.getId());
            topic.setName(this.getName());
            topic.setListOfMessages(this.getListOfMessages());
        }
        return topic;
    }
}
