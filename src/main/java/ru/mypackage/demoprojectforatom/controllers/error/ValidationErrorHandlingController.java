package ru.mypackage.demoprojectforatom.controllers.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mypackage.demoprojectforatom.dto.error.MessageErrorResponse;
import ru.mypackage.demoprojectforatom.dto.error.TopicErrorResponse;
import ru.mypackage.demoprojectforatom.exceptions.CreateTopicDTOValidationException;
import ru.mypackage.demoprojectforatom.exceptions.MessageDTOValidationException;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandlingController {

    @ExceptionHandler(CreateTopicDTOValidationException.class)
    public ResponseEntity<TopicErrorResponse> handleCreateTopicDTOValidationException(
            CreateTopicDTOValidationException e
    ) {
        StringBuilder exceptionMessage = new StringBuilder();

        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        for (FieldError error : errors) {
            exceptionMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        TopicErrorResponse response = new TopicErrorResponse(
                exceptionMessage.toString(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageDTOValidationException.class)
    public ResponseEntity<MessageErrorResponse> handleMessageDTOValidationException(
            MessageDTOValidationException e
    ) {
        StringBuilder exceptionMessage = new StringBuilder();

        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        for (FieldError error : errors) {
            exceptionMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        MessageErrorResponse response = new MessageErrorResponse(
                exceptionMessage.toString(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
