package ru.mypackage.demoprojectforatom.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mypackage.demoprojectforatom.dto.*;
import ru.mypackage.demoprojectforatom.exceptions.CreateTopicDTOValidationException;
import ru.mypackage.demoprojectforatom.exceptions.MessageDTOValidationException;
import ru.mypackage.demoprojectforatom.services.MessageService;
import ru.mypackage.demoprojectforatom.services.TopicService;
import ru.mypackage.demoprojectforatom.utils.CreateTopicDTOValidator;


@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;
    private final MessageService messageService;
    private final CreateTopicDTOValidator createTopicDTOValidator;

    @Autowired
    public TopicController(TopicService topicService,
                           MessageService messageService,
                           CreateTopicDTOValidator createTopicDTOValidator
    ) {
        this.topicService = topicService;
        this.messageService = messageService;
        this.createTopicDTOValidator = createTopicDTOValidator;
    }

    //Получение списка топиков
    @GetMapping("/all")
    public ResponseEntity<TopicsResponseDTO> getAllTopics() {
        TopicsResponseDTO topicResponseDTO = new TopicsResponseDTO(
                topicService.findAll()
        );

        return new ResponseEntity<>(topicResponseDTO, HttpStatus.OK);
    }

    //Получение списка сообщений одного топика
    @GetMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> getAllMessageFromTopic(
            @PathVariable("id") Integer id
    ) {
        MessagesResponseDTO messageResponseDTO = new MessagesResponseDTO(
                messageService.findAllByIdOfTopic(id)
        );

        return new ResponseEntity<>(messageResponseDTO, HttpStatus.OK);
    }

    //Создание нового топика с одним сообщением
    /* request: (json)
           {
            "topicDTO" {
                    "name": "some name"
                },
            "messageDTO": {
                    "username": "some name of user",
                    "message": "some message"
                }
           }
    */
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createTopicWithMessage(
            @RequestBody @Valid CreateTopicDTO createTopicDTO,
            BindingResult bindingResult
    ) {
        createTopicDTOValidator.validate(createTopicDTO, bindingResult);

        if (bindingResult.hasErrors())
            throw new CreateTopicDTOValidationException(bindingResult);

        topicService.createTopicAndMessage(createTopicDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Создание нового сообщения
    /* request: (json)
           {
            "username": "some name of user",
            "message": "some message"
           }
    */
    @PostMapping("/create/message/{id}")
    public ResponseEntity<HttpStatus> createMessage(
            //Id топика
            @PathVariable Integer id,
            @RequestBody @Valid MessageDTO messageDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new MessageDTOValidationException(bindingResult);

        messageService.createMessage(id, messageDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Редактирование сообщения
    /* request: (json)
           {
            "username": "some name of user",
            "message": "some message"
           }
    */
    @PatchMapping("/message/{id}")
    public ResponseEntity<HttpStatus> refactorMessage(
            //Id изменяемого сообщения
            @PathVariable Integer id,
            @RequestBody @Valid MessageDTO messageDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new MessageDTOValidationException(bindingResult);

        messageService.updateMessage(id, messageDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Удаление сообщения
    @DeleteMapping("/message/{id}")
    public ResponseEntity<HttpStatus> deleteMessage(
            //Id удаляемого сообщения
            @PathVariable Integer id
    ) {
        messageService.deleteMessage(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
