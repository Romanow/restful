package ru.romanow.restful.model;

import org.springframework.hateoas.ResourceSupport;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StateResource
        extends ResourceSupport {
    private final StateResponse response;

    public StateResource(@Nonnull StateResponse state) {
        this.response = state;
        add(linkTo(methodOn(HateoasStateRestController.class).getState(response.getId())).withSelfRel());
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withRel("states"));
    }
}
