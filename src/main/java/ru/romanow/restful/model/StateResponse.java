package ru.romanow.restful.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class StateResponse {
    private Integer id;
    private String city;
    private String country;
}
