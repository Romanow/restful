package ru.romanow.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.service.ServerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by ronin on 22.10.16
 */
@RestController
@RequestMapping("/server")
public class HypermediaRestController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServer(@PathVariable Integer id,
                                            PersistentEntityResourceAssembler assembler) {
        Server server = serverService.getById(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }

        return ResponseEntity.ok(server);
    }

    @GetMapping
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }
}
