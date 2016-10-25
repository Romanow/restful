package ru.romanow.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.service.ServerService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by ronin on 22.10.16
 */
@RestController
@RequestMapping("/server")
public class HypermediaRestController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private RepositoryEntityLinks entityLinks;

    @GetMapping("/{id}")
    public ResponseEntity<ServerResource> getServer(@PathVariable Integer id) {
        ServerResource server = serverService.getById(id);

        server.add(entityLinks.linkFor(State.class).slash(server.getStateId()).withRel("state"));
        server.add(linkTo(HypermediaRestController.class).slash(id).withSelfRel());
//        server.add(linkTo(HypermediaRestController.class).withRel("list"));
        return ResponseEntity.ok(server);
    }

    @GetMapping
    public ResponseEntity<List<ServerResource>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }
}
