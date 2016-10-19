package ru.romanow.restful.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.service.ServerService;
import ru.romanow.restful.web.model.ServerRequest;

import javax.validation.Valid;

/**
 * Created by romanow on 18.10.16
 */
@Validated
@RestController
public class ServerRestController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/{id}")
    public ResponseEntity getServer(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(serverService.getById(id));
    }

    @GetMapping
    public ResponseEntity getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @PutMapping
    public ResponseEntity addServer(@Valid @RequestBody ServerRequest serverRequest) {
        return ResponseEntity.ok(serverService.addServer(serverRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServer(@PathVariable("id") Integer id) {
        serverService.deleteServer(id);
        return ResponseEntity.ok().build();
    }
}
