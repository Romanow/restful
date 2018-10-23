package ru.romanow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanow.restful.domain.State;

public interface StateRepository
        extends JpaRepository<State, Integer> {

    State findByCountryAndCity(String country, String city);
}
