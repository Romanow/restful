package ru.romanow.restful.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.romanow.restful.domain.Purpose;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ServerResponse {
    private Integer id;
    private String address;
    private Purpose purpose;
    private Integer latency;
    private Integer bandwidth;
    private Integer stateId;
}
