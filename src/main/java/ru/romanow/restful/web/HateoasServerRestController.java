package ru.romanow.restful.web;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.model.ServerListResource;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.model.StateResource;
import ru.romanow.restful.service.ServerService;

@RestController
@RequestMapping("/hateoas/server")
@AllArgsConstructor
public class HateoasServerRestController {
    private final ServerService serverService;

    @GetMapping("/{id}")
    public ServerResource getServer(@ApiParam @PathVariable Integer id) {
        return new ServerResource(serverService.getServerById(id));
    }

    @GetMapping
    public ServerListResource getServers() {
        return new ServerListResource(serverService.findAllServers());
    }

    @GetMapping("/{id}")
    public StateResource getServerState(@PathVariable Integer id) {
        return new StateResource(serverService.getServerState(id));
    }
}
