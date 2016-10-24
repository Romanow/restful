package ru.romanow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.repository.ServerRepository;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Created by romanow on 18.10.16
 */
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Transactional(readOnly = true)
    public Server getById(@Nonnull Integer id) {
        Server server = serverRepository.findOne(id);
        if (server == null) {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
        return server;
    }

    @Transactional(readOnly = true)
    public List<Server> findAll() {
        return serverRepository.findAll();
    }

    @Transactional
    public Server addServer(@Nonnull ServerRequest serverRequest) {
        return serverRepository.save(buildServer(serverRequest));
    }

    @Transactional
    public void deleteServer(Integer id) {
        serverRepository.delete(id);
    }

    @Transactional
    public Server editServer(Integer id, ServerRequest serverRequest) {
        Server server = serverRepository.findOne(id);
        if (server != null) {
            server.setAddress(Optional.ofNullable(serverRequest.getAddress()).orElse(server.getAddress()));
            server.setBandwidth(Optional.ofNullable(serverRequest.getBandwidth()).orElse(server.getBandwidth()));
            server.setLatency(Optional.ofNullable(serverRequest.getLatency()).orElse(server.getLatency()));
            Purpose purpose = Optional.ofNullable(serverRequest.getPurpose())
                                      .map(Purpose::find)
                                      .orElse(server.getPurpose());
            server.setPurpose(purpose);
            return serverRepository.save(server);
        } else {
            throw new EntityNotFoundException("Server not found for id " + id);
        }
    }

    private Server buildServer(@Nonnull ServerRequest serverRequest) {
        return new Server()
                .setAddress(serverRequest.getAddress())
                .setBandwidth(serverRequest.getBandwidth())
                .setLatency(serverRequest.getLatency())
                .setPurpose(Purpose.find(serverRequest.getPurpose()));
    }
}
