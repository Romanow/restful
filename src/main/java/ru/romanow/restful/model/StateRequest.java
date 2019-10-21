package ru.romanow.restful.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Accessors(chain = true)
public class StateRequest {

    @NotEmpty(message = "{field.not.empty}")
    private String city;

    @NotEmpty(message = "{field.not.empty}")
    private String country;
}
