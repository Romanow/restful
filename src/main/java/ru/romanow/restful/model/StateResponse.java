package ru.romanow.restful.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StateResponse {
    private Integer id;
    private String city;
    private String country;
}
