package ru.romanow.restful.model;

import org.springframework.hateoas.Resources;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StateListResource
        extends Resources<StateResource> {
    public StateListResource(@Nonnull List<StateResponse> states) {
        super(states.stream().map(StateResource::new).collect(toList()));
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withSelfRel());
    }
}
