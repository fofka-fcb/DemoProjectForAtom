package ru.mypackage.demoprojectforatom.exceptions;

import org.springframework.validation.BindingResult;

public class CreateTopicDTOValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public CreateTopicDTOValidationException() {
    }

    public CreateTopicDTOValidationException(String message) {
        super(message);
    }

    public CreateTopicDTOValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

}
