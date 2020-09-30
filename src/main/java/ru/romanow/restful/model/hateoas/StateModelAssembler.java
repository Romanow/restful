package ru.romanow.restful.model.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.web.HateoasStateRestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StateModelAssembler
        extends RepresentationModelAssemblerSupport<StateResponse, StateModel> {
    public StateModelAssembler() {
        super(HateoasStateRestController.class, StateModel.class);
    }

    @Override
    public StateModel toModel(StateResponse response) {
        final StateModel stateModel = new StateModel(response);
        stateModel.add(linkTo(methodOn(HateoasStateRestController.class).getState(stateModel.getId())).withSelfRel());
        stateModel.add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withRel("states"));
        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends StateResponse> entities) {
        final CollectionModel<StateModel> models = super.toCollectionModel(entities);
        models.add(linkTo(methodOn(HateoasStateRestController.class).getStates()).withSelfRel());
        return models;
    }
}
