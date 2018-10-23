package ru.romanow.restful.model;

import lombok.Getter;
import org.springframework.hateoas.Resources;
import ru.romanow.restful.web.HateoasServerRestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ServerListResource
        extends Resources<ServerResource> {
    public ServerListResource(List<ServerResponse> servers) {
        super(servers.stream().map(ServerResource::new).collect(toList()));
        add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withSelfRel());
    }
}
