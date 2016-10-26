package ru.romanow.restful.service;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.StateResource;
import ru.romanow.restful.web.ServerRestController;

/**
 * Created by ronin on 26.10.16
 */
public class StateResourceAssembly
        extends ResourceAssemblerSupport<State, StateResource> {
    private final RepositoryEntityLinks entityLinks;

    public StateResourceAssembly(RepositoryEntityLinks entityLinks) {
        super(ServerRestController.class, StateResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public StateResource toResource(State state) {
        StateResource stateResource = new StateResource(state);
        stateResource.add(entityLinks.linkToSingleResource(State.class, state.getId()).withSelfRel());
        return stateResource;
    }
}
