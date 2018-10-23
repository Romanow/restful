package ru.romanow.restful.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.StateRequest;
import ru.romanow.restful.model.StateResponse;
import ru.romanow.restful.repository.StateRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class StateServiceImpl
        implements StateService {
    private final StateRepository stateRepository;

    @Nullable
    @Override
    @Transactional(readOnly = true)
    public State findStateById(@Nonnull Integer id) {
        return stateRepository.findById(id).orElse(null);
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public StateResponse getStateById(@Nonnull Integer id) {
        return stateRepository.findById(id)
                .map(this::buildStateResponse)
                .orElseThrow(() -> new EntityNotFoundException(format("State not found for ID:%d", id)));
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<StateResponse> findAllStates() {
        return stateRepository
                .findAll()
                .stream()
                .map(this::buildStateResponse)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional
    public Integer addState(@Nonnull StateRequest stateRequest) {
        State state = new State()
                .setCity(stateRequest.getCity())
                .setCountry(stateRequest.getCountry());

        state = stateRepository.save(state);

        return state.getId();
    }

    @Override
    @Transactional
    public void deleteState(@Nonnull Integer id) {
        stateRepository.deleteById(id);
    }

    @Nonnull
    @Override
    @Transactional
    public StateResponse editState(@Nonnull Integer id, @Nonnull StateRequest stateRequest) {
        final Optional<State> byId = stateRepository.findById(id);
        if (byId.isPresent()) {
            State state = byId.get();
            state.setCity(ofNullable(stateRequest.getCity()).orElse(state.getCity()));
            state.setCountry(ofNullable(stateRequest.getCountry()).orElse(state.getCountry()));

            state = stateRepository.save(state);

            return buildStateResponse(state);
        } else {
            throw new EntityNotFoundException(format("State not found for ID:%d", id));
        }
    }

    @Nonnull
    private StateResponse buildStateResponse(@Nonnull State state) {
        return new StateResponse()
                .setId(state.getId())
                .setCity(state.getCity())
                .setCountry(state.getCountry());
    }
}
