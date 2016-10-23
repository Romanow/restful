package ru.romanow.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.service.ServerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by ronin on 22.10.16
 */
@RepositoryRestController
@RequestMapping("/server")
public class HypermediaRestController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServer(@PathVariable Integer id) {
        Server server = serverService.getById(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }

        server.add(server.getId());
        return ResponseEntity.ok(server);
    }

    @GetMapping
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }
}
