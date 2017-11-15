package ru.romanow.restful.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.model.StateResource;
import ru.romanow.restful.service.ServerService;

import java.util.List;

/**
 * Created by ronin on 22.10.16
 */
@RestController
@RequestMapping("/server")
@AllArgsConstructor
public class ServerRestController {
    private final ServerService serverService;

    @GetMapping("/{id}")
    public ResponseEntity<ServerResource> getServer(@PathVariable Integer id) {
        return ResponseEntity.ok(serverService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServerResource>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @GetMapping("/{id}/state")
    public ResponseEntity<StateResource> getState(@PathVariable Integer id) {
        return ResponseEntity.ok(serverService.findServerState(id));
    }
}
