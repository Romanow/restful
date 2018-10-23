package ru.romanow.restful.migrations;

import org.flywaydb.core.api.migration.spring.BaseSpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1__InitScript
        extends BaseSpringJdbcMigration {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) {}
}
