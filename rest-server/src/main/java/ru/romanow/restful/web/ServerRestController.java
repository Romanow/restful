package ru.romanow.restful.web;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ErrorResponse;
import ru.romanow.restful.service.ServerService;
import ru.romanow.restful.web.model.ServerRequest;
import ru.romanow.restful.web.model.ServerResponse;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by romanow on 18.10.16
 */
@Api
@RestController
@RequestMapping("/server")
@AllArgsConstructor
public class ServerRestController {
    private final ServerService serverService;

    @ApiOperation("Get entity by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ServerResponse.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @GetMapping("/{id}")
    public ServerResponse getServer(@ApiParam @PathVariable Integer id) {
        return serverService.getById(id);
    }

    @ApiOperation("Find all entities")
    @ApiResponse(code = 200, message = "OK", response = ServerResponse.class, responseContainer = "list")
    @GetMapping
    public List<ServerResponse> getServers() {
        return serverService.findAll();
    }

    @ApiOperation("Save new entity")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
    })
    @PostMapping
    public ResponseEntity addServer(@ApiParam @Valid @RequestBody ServerRequest serverRequest) {
        Integer serverId = serverService.addServer(serverRequest);
        return ResponseEntity.created(URI.create("/server/" + serverId)).build();
    }

    @ApiOperation("Edit entity by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Server.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @PatchMapping("/{id}")
    public ServerResponse editServer(@ApiParam @PathVariable Integer id,
                                     @ApiParam @RequestBody ServerRequest serverRequest) {
        return serverService.editServer(id, serverRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete entity by Id")
    @ApiResponse(code = 202, message = "OK")
    @DeleteMapping("/{id}")
    public void deleteServer(@ApiParam @PathVariable Integer id) {
        serverService.deleteServer(id);
    }
}
