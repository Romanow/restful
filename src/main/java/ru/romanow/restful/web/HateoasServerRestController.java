package ru.romanow.restful.web;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.model.hateoas.ServerModel;
import ru.romanow.restful.model.hateoas.ServerModelAssembler;
import ru.romanow.restful.model.hateoas.StateModel;
import ru.romanow.restful.model.hateoas.StateModelAssembler;
import ru.romanow.restful.service.ServerService;

@RestController
@RequestMapping("/hateoas/server")
@AllArgsConstructor
public class HateoasServerRestController {
    private final ServerService serverService;
    private final ServerModelAssembler serverModelAssembler;
    private final StateModelAssembler stateModelAssembler;

    @GetMapping("/{id}")
    public ServerModel getServer(@PathVariable Integer id) {
        return serverModelAssembler.toModel(serverService.getServerById(id));
    }

    @GetMapping
    public CollectionModel<ServerModel> getServers() {
        return serverModelAssembler.toCollectionModel(serverService.findAllServers());
    }

    @GetMapping("/{id}/state")
    public StateModel getServerState(@PathVariable Integer id) {
        return stateModelAssembler.toModel(serverService.getServerState(id));
    }
}
