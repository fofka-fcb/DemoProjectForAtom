package ru.mypackage.demoprojectforatom.exceptions;

import org.springframework.http.HttpStatus;

public class TopicNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public TopicNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

}
