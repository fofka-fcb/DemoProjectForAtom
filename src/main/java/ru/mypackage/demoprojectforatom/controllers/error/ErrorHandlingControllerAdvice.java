package ru.mypackage.demoprojectforatom.controllers.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mypackage.demoprojectforatom.dto.error.MessageErrorResponse;
import ru.mypackage.demoprojectforatom.dto.error.TopicErrorResponse;
import ru.mypackage.demoprojectforatom.exceptions.MessageNotFoundException;
import ru.mypackage.demoprojectforatom.exceptions.TopicNotFoundException;
import ru.mypackage.demoprojectforatom.exceptions.UsernameIsNotValidException;

@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<MessageErrorResponse> handleMessageNotFoundException(
            MessageNotFoundException e
    ) {
        MessageErrorResponse response = new MessageErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<TopicErrorResponse> handleTopicNotFoundException(
            TopicNotFoundException e
    ) {
        TopicErrorResponse response = new TopicErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(UsernameIsNotValidException.class)
    public ResponseEntity<MessageErrorResponse> handleUsernameIsNotValidException(
            UsernameIsNotValidException e
    ) {
        MessageErrorResponse response = new MessageErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, e.getHttpStatus());
    }
}
