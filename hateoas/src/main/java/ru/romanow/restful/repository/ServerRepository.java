package ru.romanow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.romanow.restful.domain.Server;

/**
 * Created by romanow on 18.10.16
 */
@RepositoryRestResource(path = "/server")
public interface ServerRepository
        extends JpaRepository<Server, Integer> {}
