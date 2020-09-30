package ru.romanow.restful.model.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.romanow.restful.model.api.ServerResponse;
import ru.romanow.restful.web.HateoasServerRestController;
import ru.romanow.restful.web.HateoasStateRestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ServerModelAssembler
        extends RepresentationModelAssemblerSupport<ServerResponse, ServerModel> {
    public ServerModelAssembler() {
        super(HateoasServerRestController.class, ServerModel.class);
    }

    @Override
    public ServerModel toModel(ServerResponse server) {
        final ServerModel serverModel = new ServerModel(server);
        serverModel.add(linkTo(methodOn(HateoasServerRestController.class).getServer(serverModel.getId())).withSelfRel());
        serverModel.add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withRel("servers"));
        serverModel.add(linkTo(methodOn(HateoasServerRestController.class).getServerState(serverModel.getId())).withRel("state"));
        return serverModel;
    }

    @Override
    public CollectionModel<ServerModel> toCollectionModel(Iterable<? extends ServerResponse> entities) {
        final CollectionModel<ServerModel> models = super.toCollectionModel(entities);
        models.add(linkTo(methodOn(HateoasServerRestController.class).getServers()).withSelfRel());
        return models;
    }
}
