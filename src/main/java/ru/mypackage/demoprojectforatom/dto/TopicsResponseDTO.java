package ru.mypackage.demoprojectforatom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mypackage.demoprojectforatom.models.Topic;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicsResponseDTO {

    private List<Topic> topics;

}
