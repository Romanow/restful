package ru.romanow.restful.web;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ErrorResponse;
import ru.romanow.restful.model.ServerResponse;
import ru.romanow.restful.model.StateRequest;
import ru.romanow.restful.model.StateResponse;
import ru.romanow.restful.service.StateService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api("State API")
@RestController
@RequestMapping("/api/state")
@AllArgsConstructor
public class StateRestController {
    private final StateService stateService;

    @ApiOperation("Get state by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ServerResponse.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @GetMapping("/{id}")
    public StateResponse getState(@ApiParam @PathVariable Integer id) {
        return stateService.getStateById(id);
    }

    @ApiOperation("Find all states")
    @ApiResponse(code = 200, message = "OK", response = ServerResponse.class, responseContainer = "list")
    @GetMapping
    public List<StateResponse> getStates() {
        return stateService.findAllStates();
    }

    @ApiOperation("Save new state")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addServer(@ApiParam @Valid @RequestBody StateRequest stateRequest, HttpServletResponse response) {
        Integer stateId = stateService.addState(stateRequest);
        response.setHeader(HttpHeaders.LOCATION, "/state/" + stateId);
    }

    @ApiOperation("Edit state by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Server.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @PatchMapping("/{id}")
    public StateResponse editServer(@ApiParam @PathVariable Integer id,
                                     @ApiParam @RequestBody StateRequest stateRequest) {
        return stateService.editState(id, stateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete state by Id")
    @ApiResponse(code = 204, message = "OK")
    @DeleteMapping("/{id}")
    public void deleteServer(@ApiParam @PathVariable Integer id) {
        stateService.deleteState(id);
    }
}
