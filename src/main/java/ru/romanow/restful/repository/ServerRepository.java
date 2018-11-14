package ru.romanow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.romanow.restful.domain.Server;

import java.util.Arrays;
import java.util.List;

/**
 * Created by romanow on 18.10.16
 */
public interface ServerRepository
        extends JpaRepository<Server, Integer>,
                ServerRepositoryCustom {}
