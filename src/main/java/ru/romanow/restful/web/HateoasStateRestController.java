package ru.romanow.restful.web;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.model.hateoas.StateModel;
import ru.romanow.restful.model.hateoas.StateModelAssembler;
import ru.romanow.restful.service.StateService;

@RestController
@RequestMapping("/hateoas/state")
@AllArgsConstructor
public class HateoasStateRestController {
    private final StateService stateService;
    private final StateModelAssembler modelAssembler;

    @GetMapping("/{id}")
    public StateModel getState(@PathVariable Integer id) {
        return modelAssembler.toModel(stateService.getStateById(id));
    }

    @GetMapping
    public CollectionModel<StateModel> getStates() {
        return modelAssembler.toCollectionModel(stateService.findAllStates());
    }
}
