package ru.romanow.restful.web;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.model.StateListResource;
import ru.romanow.restful.model.StateResource;
import ru.romanow.restful.service.StateService;

@RestController
@RequestMapping("/hateoas/state")
@AllArgsConstructor
public class HateoasStateRestController {
    private final StateService stateService;

    @GetMapping("/{id}")
    public StateResource getState(@ApiParam @PathVariable Integer id) {
        return new StateResource(stateService.getStateById(id));
    }

    @GetMapping
    public StateListResource getStates() {
        return new StateListResource(stateService.findAllStates());
    }
}
