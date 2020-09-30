package ru.romanow.restful.model.hateoas;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import ru.romanow.restful.model.api.ServerResponse;
import ru.romanow.restful.web.HateoasServerRestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class ServerResource
        extends RepresentationModel<ServerResource> {
    private final ServerResponse response;

    public ServerResource(ServerResponse response) {
        this.response = response;
        add(linkTo(methodOn(HateoasServerRestController.class).getServer(response.getId())).withSelfRel());
        add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withRel("servers"));
        add(linkTo(methodOn(HateoasServerRestController.class).getServerState(response.getId())).withRel("state"));
    }
}
