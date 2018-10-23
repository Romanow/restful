package ru.romanow.restful;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
public class DatabaseConfiguration {

    @Bean
    @DependsOn("entityManagerFactory")
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("classpath:ru/romanow/restful/migrations");
        flyway.setOutOfOrder(true);
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

    @Bean
    public FlywayMigrationInitializer flywayInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(flyway(dataSource));
    }
}
