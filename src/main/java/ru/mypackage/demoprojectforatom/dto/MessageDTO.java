package ru.mypackage.demoprojectforatom.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @Size(min = 2, max = 12, message = "Name of user should be between 2 and 12 characters")
    private String username;

    @Size(min = 6, max = 256, message = "Message should be between 6 and 256 characters")
    private String message;

}
