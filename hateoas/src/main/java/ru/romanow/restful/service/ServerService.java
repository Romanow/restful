package ru.romanow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.ServerResource;
import ru.romanow.restful.model.StateResource;
import ru.romanow.restful.repository.ServerRepository;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by romanow on 24.10.16
 */
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RepositoryEntityLinks entityLinks;

    private ServerResourceAssembler serverResourceAssembler;
    private StateResourceAssembly stateResourceAssembly;

    @PostConstruct
    public void init() {
        this.serverResourceAssembler = new ServerResourceAssembler();
        this.stateResourceAssembly = new StateResourceAssembly(entityLinks);
    }

    @Transactional(readOnly = true)
    public ServerResource getById(@Nonnull Integer id) {
        Server server = serverRepository.findOne(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
        return serverResourceAssembler.toResource(server);
    }

    @Transactional(readOnly = true)
    public List<ServerResource> findAll() {
        return serverResourceAssembler.toResources(serverRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StateResource findServerState(@Nonnull Integer id) {
        State state = serverRepository.findServerState(id);
        if (state == null) {
            throw new EntityNotFoundException("State for server id = [" + id + "] not found");
        }
        return stateResourceAssembly.toResource(state);
    }
}
