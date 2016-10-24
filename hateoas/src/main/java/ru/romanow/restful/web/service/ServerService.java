package ru.romanow.restful.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.repository.ServerRepository;
import ru.romanow.restful.web.model.ServerResource;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by romanow on 24.10.16
 */
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Transactional(readOnly = true)
    public ServerResource getById(@Nonnull Integer id) {
        Server server = serverRepository.findOne(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
        return new ServerResource(server);
    }

    @Transactional(readOnly = true)
    public List<Server> findAll() {
        return serverRepository.findAll();
    }
}
