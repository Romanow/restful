package ru.romanow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.repository.ServerRepository;

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

    private ServerResourceAssembler resourceAssembler;

    public ServerService() {
        this.resourceAssembler = new ServerResourceAssembler();
    }

    @Transactional(readOnly = true)
    public ServerResource getById(@Nonnull Integer id) {
        Server server = serverRepository.findOne(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
        return resourceAssembler.toResource(server);
    }

    @Transactional(readOnly = true)
    public List<ServerResource> findAll() {
        return resourceAssembler.toResources(serverRepository.findAll());
    }
}
