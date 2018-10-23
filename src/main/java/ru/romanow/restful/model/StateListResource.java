package ru.romanow.restful.model;

import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StateListResource
        extends ResourceSupport {
    private final List<StateResource> response;

    public StateListResource(@Nonnull List<StateResponse> states) {
        this.response = states.stream().map(StateResource::new).collect(toList());

    }
}
