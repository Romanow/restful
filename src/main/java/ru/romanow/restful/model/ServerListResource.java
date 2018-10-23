package ru.romanow.restful.model;

import org.springframework.hateoas.ResourceSupport;
import ru.romanow.restful.web.HateoasStateRestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ServerListResource
        extends ResourceSupport {
    private final List<ServerResource> response;

    public ServerListResource(List<ServerResponse> servers) {
        this.response = servers.stream().map(ServerResource::new).collect(toList());
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withSelfRel());
    }
}
