package ru.romanow.restful.service;

import ru.romanow.restful.web.model.ServerRequest;
import ru.romanow.restful.web.model.ServerResponse;

import javax.annotation.Nonnull;
import java.util.List;

public interface ServerService {

    @Nonnull
    ServerResponse getById(@Nonnull Integer serverId);

    @Nonnull
    List<ServerResponse> findAll();

    @Nonnull
    Integer addServer(@Nonnull ServerRequest serverRequest);

    void deleteServer(@Nonnull Integer serverId);

    @Nonnull
    ServerResponse editServer(@Nonnull Integer serverId, @Nonnull ServerRequest serverRequest);
}
