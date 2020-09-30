package ru.romanow.restful.model.hateoas;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.model.api.ServerResponse;
import ru.romanow.restful.web.HateoasServerRestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class ServerModel
        extends RepresentationModel<ServerModel> {
    private final Integer id;
    private final String address;
    private final Purpose purpose;
    private final Integer latency;
    private final Integer bandwidth;
    private final Integer stateId;

    public ServerModel(ServerResponse response) {
        this.id = response.getId();
        this.address = response.getAddress();
        this.purpose = response.getPurpose();
        this.latency = response.getLatency();
        this.bandwidth = response.getBandwidth();
        this.stateId = response.getStateId();
    }
}
