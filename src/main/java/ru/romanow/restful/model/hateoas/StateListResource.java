package ru.romanow.restful.model.hateoas;

import org.springframework.hateoas.CollectionModel;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StateListResource
        extends CollectionModel<StateResource> {
    public StateListResource(@Nonnull List<StateResponse> states) {
        of(states.stream().map(StateResource::new).collect(toList()));
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withSelfRel());
    }
}
