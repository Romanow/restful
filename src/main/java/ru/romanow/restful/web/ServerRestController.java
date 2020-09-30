package ru.romanow.restful.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.model.api.ServerRequest;
import ru.romanow.restful.model.api.ServerResponse;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.service.ServerService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Tag(name = "Server API")
@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerRestController {
    private final ServerService serverService;

    @Operation(description = "Get server by Id")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
    public ServerResponse getServer(@PathVariable Integer id) {
        return serverService.getServerById(id);
    }

    @Operation(description = "Get server state")
    @GetMapping(value = "/{id}/state", produces = { "application/json", "application/xml" })
    public StateResponse getServerState(@PathVariable Integer id) {
        return serverService.getServerState(id);
    }

    @Operation(description = "Find all servers")
    @GetMapping(produces = { "application/json", "application/xml" })
    public List<ServerResponse> getServers() {
        return serverService.findAllServers();
    }

    @Operation(description = "Find servers by address")
    @GetMapping(params = "address", produces = { "application/json", "application/xml" })
    public List<ServerResponse> findServersByAddress(@RequestParam String address) {
        return serverService.findServersByAddress(address);
    }

    @Operation(description = "Save new server")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = { "application/json", "application/xml" })
    public ResponseEntity<Void> addServer(@Valid @RequestBody ServerRequest serverRequest) {
        final Integer id = serverService.addServer(serverRequest);
        return ResponseEntity
                .created(
                        fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(id)
                                .toUri())
                .build();
    }

    @Operation(description = "Edit server by Id")
    @PatchMapping(value = "/{id}", consumes = "application/json", produces = { "application/json", "application/xml" })
    public ServerResponse editServer(@PathVariable Integer id,
                                     @RequestBody ServerRequest serverRequest) {
        return serverService.editServer(id, serverRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete server by Id")
    @DeleteMapping("/{id}")
    public void deleteServer(@PathVariable Integer id) {
        serverService.deleteServer(id);
    }
}
