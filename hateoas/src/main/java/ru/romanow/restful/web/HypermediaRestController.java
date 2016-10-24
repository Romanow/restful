package ru.romanow.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.web.model.ServerResource;
import ru.romanow.restful.web.service.ServerService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by ronin on 22.10.16
 */
@ExposesResourceFor(ServerResource.class)
@RepositoryRestController
@RequestMapping("/server")
public class HypermediaRestController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/{id}")
    public ResponseEntity<ServerResource> getServer(@PathVariable Integer id) {
        ServerResource server = serverService.getById(id);
        server.add(linkTo(HypermediaRestController.class).withSelfRel());
        return ResponseEntity.ok(server);
    }

    @GetMapping
    @RequestMapping("/list")
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }
}
