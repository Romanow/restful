package ru.romanow.restful.service;

import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.StateRequest;
import ru.romanow.restful.model.StateResponse;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface StateService {

    @Nullable
    State findStateById(@Nonnull Integer id);

    @Nonnull
    StateResponse getStateById(@Nonnull Integer id);

    @Nonnull
    List<StateResponse> findAllStates();

    @Nonnull
    Integer addState(@Nonnull StateRequest stateRequest);

    void deleteState(@Nonnull Integer id);

    @Nonnull
    StateResponse editState(@Nonnull Integer id, @Nonnull StateRequest stateRequest);
}
