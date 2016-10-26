package ru.romanow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;

/**
 * Created by romanow on 18.10.16
 */
@RepositoryRestResource(path = "/server")
public interface ServerRepository
        extends JpaRepository<Server, Integer> {

    @Query("select s.state from Server s where s.id = :serverId")
    State findServerState(@Param("serverId") Integer serverId);
}
