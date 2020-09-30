package ru.romanow.restful.model.hateoas;

import org.springframework.hateoas.CollectionModel;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StateListResource
        extends CollectionModel<StateResponse> {
    public StateListResource(@Nonnull List<StateResponse> states) {
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withSelfRel());
    }
}
