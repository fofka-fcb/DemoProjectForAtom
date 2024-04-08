package ru.mypackage.demoprojectforatom.models;

import java.util.Date;

public class Message {

    private Integer id;
    private Integer idOfTopic;
    private String username;
    private String message;
    private Date createdAt;

    public Message() {
    }

    public Message(Integer id, Integer idOfTopic, String username, String message, Date createdAt) {
        this.id = id;
        this.idOfTopic = idOfTopic;
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIdOfTopic() {
        return idOfTopic;
    }

    public void setIdOfTopic(Integer idOfTopic) {
        this.idOfTopic = idOfTopic;
    }
}
