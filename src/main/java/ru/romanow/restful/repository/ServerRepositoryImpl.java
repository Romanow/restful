package ru.romanow.restful.repository;

import ru.romanow.restful.domain.Server;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ServerRepositoryImpl
        implements ServerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Nonnull
    @Override
    public List<Server> findServersByAddress(@Nonnull String address) {
        final String sql = "select " +
                "s.id, " +
                "s.address, " +
                "s.latency, " +
                "s.purpose, " +
                "s.bandwidth, " +
                "s.state_id " +
                "from server s " +
                "where s.address = '" + address + "'";
        return entityManager
                .createNativeQuery(sql, Server.class)
                .getResultList();
    }
}