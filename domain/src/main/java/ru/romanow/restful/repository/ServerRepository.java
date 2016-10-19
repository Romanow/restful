package ru.romanow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanow.restful.domain.Server;

/**
 * Created by romanow on 18.10.16
 */
public interface ServerRepository
        extends JpaRepository<Server, Integer> {}
