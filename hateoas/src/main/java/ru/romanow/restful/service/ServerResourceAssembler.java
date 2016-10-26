package ru.romanow.restful.service;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.web.ServerRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by romanow on 25.10.16
 */
public class ServerResourceAssembler
        extends ResourceAssemblerSupport<Server, ServerResource> {

    public ServerResourceAssembler() {
        super(ServerRestController.class, ServerResource.class);
    }

    @Override
    public ServerResource toResource(Server server) {
        ServerResource serverResource = new ServerResource(server);
        serverResource.add(linkTo(ServerRestController.class).slash(server.getId()).withSelfRel());
        serverResource.add(linkTo(ServerRestController.class).withRel("create"));
        serverResource.add(linkTo(ServerRestController.class).slash(server.getId()).withRel("delete"));
        serverResource.add(linkTo(methodOn(ServerRestController.class).getState(server.getId())).slash(server.getId()).slash("/state").withRel("state"));
        return serverResource;
    }
}
