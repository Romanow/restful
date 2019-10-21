package ru.romanow.restful.web;

import com.google.gson.Gson;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.*;
import ru.romanow.restful.service.StateService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api("State API")
@RestController
@RequestMapping("/api/state")
@RequiredArgsConstructor
public class StateRestController {
    private final StateService stateService;
    private final Gson gson = new Gson();

    @ApiOperation("Get state by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ServerResponse.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<StateResponse> getState(@ApiParam @PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .body(stateService.getStateById(id));
    }

    @ApiOperation("Find all states")
    @ApiResponse(code = 200, message = "OK", response = ServerResponse.class, responseContainer = "list")
    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<StateListResponse> getStates() {
        final List<StateResponse> list = stateService.findAllStates();
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
                .eTag(DigestUtils.md5DigestAsHex(gson.toJson(list).getBytes()))
                .body(new StateListResponse(list));
    }

    @ApiOperation("Save new state")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
    public void addServer(@ApiParam @Valid @RequestBody StateRequest stateRequest, HttpServletResponse response) {
        Integer stateId = stateService.addState(stateRequest);
        response.setHeader(HttpHeaders.LOCATION, "/state/" + stateId);
    }

    @ApiOperation("Edit state by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Server.class),
            @ApiResponse(code = 404, message = "State not found", response = ErrorResponse.class)
    })
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
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
