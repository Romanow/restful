package ru.romanow.restful.model.hateoas;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.web.HateoasStateRestController;

import javax.annotation.Nonnull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Getter
public class StateModel
        extends RepresentationModel<StateModel> {
    private final Integer id;
    private final String city;
    private final String country;

    public StateModel(@Nonnull StateResponse state) {
        this.id = state.getId();
        this.city = state.getCity();
        this.country = state.getCountry();
    }
}
