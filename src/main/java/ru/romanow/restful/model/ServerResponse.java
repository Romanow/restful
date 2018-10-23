package ru.romanow.restful.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.romanow.restful.domain.Purpose;

@Data
@Accessors(chain = true)
public class ServerResponse {
    private Integer id;
    private String address;
    private Purpose purpose;
    private Integer latency;
    private Integer bandwidth;
    private Integer stateId;
}
