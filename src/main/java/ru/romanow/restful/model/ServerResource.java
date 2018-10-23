package ru.romanow.restful.model;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import ru.romanow.restful.web.HateoasServerRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ServerResource
        extends ResourceSupport {
    private final ServerResponse response;

    public ServerResource(ServerResponse response) {
        this.response = response;
        add(linkTo(methodOn(HateoasServerRestController.class).getServer(response.getId())).withSelfRel());
        add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withRel("servers"));
        add(linkTo(methodOn(HateoasServerRestController.class).getServerState(response.getId())).withRel("state"));
    }
}
