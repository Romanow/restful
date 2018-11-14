package ru.romanow.restful.service;

import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.model.ServerResponse;
import ru.romanow.restful.model.StateResponse;

import javax.annotation.Nonnull;
import java.util.List;

public interface ServerService {

    @Nonnull
    ServerResponse getServerById(@Nonnull Integer id);

    @Nonnull
    List<ServerResponse> findAllServers();

    @Nonnull
    List<ServerResponse> findServersByAddress(@Nonnull String address);

    @Nonnull
    Integer addServer(@Nonnull ServerRequest serverRequest);

    void deleteServer(@Nonnull Integer id);

    @Nonnull
    ServerResponse editServer(@Nonnull Integer id, @Nonnull ServerRequest serverRequest);

    @Nonnull
    StateResponse getServerState(@Nonnull Integer id);
}
