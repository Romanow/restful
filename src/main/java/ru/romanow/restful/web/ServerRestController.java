package ru.romanow.restful.web;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ErrorResponse;
import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.model.ServerResponse;
import ru.romanow.restful.model.StateResponse;
import ru.romanow.restful.service.ServerService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by romanow on 18.10.16
 */
@Api("Server API")
@RestController
@RequestMapping("/api/server")
public class ServerRestController {
    private static final Logger logger = LoggerFactory.getLogger(ServerRestController.class);

    @Autowired
    private ServerService serverService;

    @Value("${server.port}")
    private String port;

    @ApiOperation("Get server by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ServerResponse.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @GetMapping("/{id}")
    public ServerResponse getServer(@ApiParam @PathVariable Integer id) {
        logger.info("Request to localhost:{}/{}", port, id);
        return serverService.getServerById(id);
    }

    @ApiOperation("Find all servers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ServerResponse.class),
            @ApiResponse(code = 404, message = "Server not found", response = ErrorResponse.class)
    })
    @GetMapping("/{id}/state")
    public StateResponse getServerState(@PathVariable Integer id) {
        return serverService.getServerState(id);
    }

    @ApiOperation("Find all servers")
    @ApiResponse(code = 200, message = "OK", response = ServerResponse.class, responseContainer = "list")
    @GetMapping
    public List<ServerResponse> getServers() {
        logger.info("Request to localhost:{}", port);
        return serverService.findAllServers();
    }

    @ApiOperation("Find servers by address")
    @ApiResponse(code = 200, message = "OK", response = ServerResponse.class, responseContainer = "list")
    @GetMapping(params = "address")
    public List<ServerResponse> findServersByAddress(@RequestParam String address) {
        return serverService.findServersByAddress(address);
    }

    @ApiOperation("Save new server")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addServer(@ApiParam @Valid @RequestBody ServerRequest serverRequest, HttpServletResponse response) {
        Integer id = serverService.addServer(serverRequest);
        response.setHeader(HttpHeaders.LOCATION, "/server/" + id);
    }

    @ApiOperation("Edit server by Id")
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
    @ApiOperation("Delete server by Id")
    @ApiResponse(code = 204, message = "OK")
    @DeleteMapping("/{id}")
    public void deleteServer(@ApiParam @PathVariable Integer id) {
        serverService.deleteServer(id);
    }
}
