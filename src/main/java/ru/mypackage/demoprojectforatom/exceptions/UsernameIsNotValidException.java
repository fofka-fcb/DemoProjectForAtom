package ru.mypackage.demoprojectforatom.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameIsNotValidException extends RuntimeException {

    private final HttpStatus status;

    public UsernameIsNotValidException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

}
