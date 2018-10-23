package ru.romanow.restful.migrations;

import org.flywaydb.core.api.migration.spring.BaseSpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class V1__InitScript
        extends BaseSpringJdbcMigration {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) {
        int stateID1 = insertState(jdbcTemplate, new State()
                .setCity("Moscow")
                .setCountry("Russia"));

        int stateID2 = insertState(jdbcTemplate, new State()
                .setCity("SPb")
                .setCountry("Russia"));

        insertServer(jdbcTemplate, new Server()
                .setAddress("Moscow")
                .setBandwidth(1000)
                .setLatency(10)
                .setPurpose(Purpose.BACKEND), stateID1);

        insertServer(jdbcTemplate, new Server()
                .setAddress("Moscow")
                .setBandwidth(10000)
                .setLatency(5)
                .setPurpose(Purpose.DATABASE), stateID1);

        insertServer(jdbcTemplate, new Server()
                .setAddress("Moscow")
                .setBandwidth(5000)
                .setLatency(5)
                .setPurpose(Purpose.FRONTEND), stateID1);

        insertServer(jdbcTemplate, new Server()
                .setAddress("SPb")
                .setBandwidth(10000)
                .setLatency(5)
                .setPurpose(Purpose.BACKEND), stateID2);
    }

    private int insertServer(JdbcTemplate jdbcTemplate, @Nonnull Server server, @Nonnull Integer stateId) {
        final String sql = "INSERT INTO server (address, bandwidth, latency, purpose, state_id) VALUES (?, ?, ?, ?, ?) RETURNING id";

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, server.getAddress());
            ps.setInt(2, server.getBandwidth());
            ps.setInt(3, server.getLatency());
            ps.setString(4, server.getPurpose().name());
            ps.setInt(5, stateId);
            return ps;
        }, key);

        return key.getKey().intValue();
    }

    private int insertState(JdbcTemplate jdbcTemplate, @Nonnull State state) {
        final String sql = "INSERT INTO state (city, country) VALUES (?, ?) RETURNING id";

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, state.getCity());
            ps.setString(2, state.getCountry());
            return ps;
        }, key);

        return key.getKey().intValue();
    }
}
