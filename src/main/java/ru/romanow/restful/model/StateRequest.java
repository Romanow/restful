package ru.romanow.restful.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class StateRequest {

    @NotEmpty(message = "{field.not.empty}")
    private String city;

    @NotEmpty(message = "{field.not.empty}")
    private String country;
}
