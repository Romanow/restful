package ru.romanow.restful.web;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ErrorResponse;
import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.service.ServerService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by romanow on 18.10.16
 */
@Api
@RestController
@RequestMapping("/server")
public class ServerRestController {

    @Autowired
    private ServerService serverService;

    @ApiOperation("Get entity by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Server not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Server> getServer(@ApiParam @PathVariable Integer id) {
        return ResponseEntity.ok(serverService.getById(id));
    }

    @ApiOperation("Find all entities")
    @ApiResponse(code = 200, message = "OK", response = Server.class)
    @GetMapping
    public ResponseEntity<List<Server>> getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @ApiOperation("Save new entity")
    @ApiResponse(code = 201, message = "Created", response = Server.class)
    @PostConstruct
    public ResponseEntity addServer(@ApiParam @Valid @RequestBody ServerRequest serverRequest) {
        Server server = serverService.addServer(serverRequest);
        return ResponseEntity.created(URI.create("/" + server.getId())).build();
    }

    @ApiOperation("Edit entity by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Server.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Server> editServer(@ApiParam @PathVariable Integer id,
                                             @ApiParam @RequestBody ServerRequest serverRequest) {
        return ResponseEntity.ok(serverService.editServer(id, serverRequest));
    }

    @ApiOperation("Delete entity by Id")
    @ApiResponse(code = 202, message = "OK")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteServer(@ApiParam @PathVariable Integer id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}
