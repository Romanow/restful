package ru.romanow.restful.model.hateoas;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Getter
public class StateResource
        extends RepresentationModel<StateResource> {
    private final StateResponse response;

    public StateResource(@Nonnull StateResponse state) {
        this.response = state;
        add(linkTo(methodOn(HateoasStateRestController.class).getState(response.getId())).withSelfRel());
        add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withRel("states"));
    }
}
