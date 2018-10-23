package ru.romanow.restful.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;

@Data
@Accessors(chain = true)
public class ServerResponse {
    private String address;
    private Purpose purpose;
    private Integer latency;
    private Integer bandwidth;

    public ServerResponse() {}

    public ServerResponse(Server server) {
        this.address = server.getAddress();
        this.purpose = server.getPurpose();
        this.latency = server.getLatency();
        this.bandwidth = server.getBandwidth();
    }
}
