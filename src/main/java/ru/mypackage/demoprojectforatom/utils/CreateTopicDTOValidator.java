package ru.mypackage.demoprojectforatom.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mypackage.demoprojectforatom.dao.TopicDAO;
import ru.mypackage.demoprojectforatom.dto.CreateTopicDTO;

@Component
public class CreateTopicDTOValidator implements Validator {

    private final TopicDAO topicDAO;

    @Autowired
    public CreateTopicDTOValidator(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTopicDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateTopicDTO createTopicDTO = (CreateTopicDTO) target;

        if (topicDAO.findOneByName(createTopicDTO.getTopicDTO().getName()).isPresent()) {
            errors.rejectValue("topicDTO", "", "This name of topic is already taken!");
        }
    }
}
