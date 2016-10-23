package ru.romanow.restful.web;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ErrorResponse;
import ru.romanow.restful.service.ServerService;
import ru.romanow.restful.model.ServerRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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
    public ResponseEntity getServer(@ApiParam @PathVariable Integer id) {
        Server server = serverService.getById(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
        return ResponseEntity.ok(server);
    }

    @ApiOperation("Find all entities")
    @ApiResponse(code = 200, message = "OK", response = Server.class)
    @GetMapping
    public ResponseEntity getServers() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @ApiOperation("Save new entity")
    @ApiResponse(code = 200, message = "OK", response = Server.class)
    @PutMapping
    public ResponseEntity addServer(@ApiParam @Valid @RequestBody ServerRequest serverRequest) {
        return ResponseEntity.ok(serverService.addServer(serverRequest));
    }

    @ApiOperation("Edit entity by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Server.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @PatchMapping("/{id}")
    public ResponseEntity editServer(@ApiParam @PathVariable Integer id,
                                     @ApiParam @RequestBody ServerRequest serverRequest) {
        return ResponseEntity.ok(serverService.editServer(id, serverRequest));
    }

    @ApiOperation("Delete entity by Id")
    @ApiResponse(code = 200, message = "OK")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteServer(@ApiParam @PathVariable Integer id) {
        serverService.deleteServer(id);
        return ResponseEntity.ok().build();
    }
}
