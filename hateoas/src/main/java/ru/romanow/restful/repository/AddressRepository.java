package ru.romanow.restful.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.romanow.restful.domain.State;

/**
 * Created by romanow on 25.10.16
 */
@RepositoryRestResource(path = "/state")
public interface AddressRepository
        extends CrudRepository<State, Integer> {}
