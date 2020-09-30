package ru.romanow.restful.web;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import ru.romanow.restful.model.api.StateListResponse;
import ru.romanow.restful.model.api.StateRequest;
import ru.romanow.restful.model.api.StateResponse;
import ru.romanow.restful.service.StateService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Tag(name = "State API")
@RestController
@RequestMapping("/api/state")
@RequiredArgsConstructor
public class StateRestController {
    private final StateService stateService;
    private final Gson gson = new Gson();

    @Operation(description = "Get state by Id")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<StateResponse> getState(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .body(stateService.getStateById(id));
    }

    @Operation(description = "Find all states")
    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<StateListResponse> getStates() {
        final List<StateResponse> list = stateService.findAllStates();
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
                .eTag(DigestUtils.md5DigestAsHex(gson.toJson(list).getBytes()))
                .body(new StateListResponse(list));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Save new state")
    @PostMapping(consumes = "application/json", produces = { "application/json", "application/xml" })
    public ResponseEntity<Void> addServer(@Valid @RequestBody StateRequest stateRequest) {
        final Integer id = stateService.addState(stateRequest);
        return ResponseEntity
                .created(
                        fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(id)
                                .toUri())
                .build();
    }


    @Operation(description = "Edit state by Id")
    @PatchMapping(value = "/{id}", consumes = "application/json", produces = { "application/json", "application/xml" })
    public StateResponse editServer(@PathVariable Integer id,
                                    @RequestBody StateRequest stateRequest) {
        return stateService.editState(id, stateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete state by Id")
    @DeleteMapping("/{id}")
    public void deleteServer(@PathVariable Integer id) {
        stateService.deleteState(id);
    }
}
