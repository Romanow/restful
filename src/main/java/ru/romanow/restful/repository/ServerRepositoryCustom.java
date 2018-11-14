package ru.romanow.restful.repository;

import ru.romanow.restful.domain.Server;

import javax.annotation.Nonnull;
import java.util.List;

public interface ServerRepositoryCustom {

    @Nonnull
    List<Server> findServersByAddress(@Nonnull String address);
}
