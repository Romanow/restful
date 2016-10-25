package ru.romanow.restful.service;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.web.HypermediaRestController;

/**
 * Created by romanow on 25.10.16
 */
public class ServerResourceAssembler
        extends ResourceAssemblerSupport<Server, ServerResource> {

    public ServerResourceAssembler() {
        super(HypermediaRestController.class, ServerResource.class);
    }

    @Override
    public ServerResource toResource(Server server) {
        return new ServerResource(server);
    }
}
