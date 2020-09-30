package ru.romanow.restful.model.hateoas;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import ru.romanow.restful.model.api.ServerResponse;
import ru.romanow.restful.web.HateoasServerRestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class ServerListResource
        extends CollectionModel<ServerResource> {
    public ServerListResource(List<ServerResponse> servers) {
        of(servers.stream().map(ServerResource::new).collect(toList()));
        add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withSelfRel());
    }
}
