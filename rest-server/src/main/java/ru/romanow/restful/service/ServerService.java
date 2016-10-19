package ru.romanow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.repository.ServerRepository;
import ru.romanow.restful.web.model.ServerRequest;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by romanow on 18.10.16
 */
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public Server getById(@Nonnull Integer id) {
        return serverRepository.findOne(id);
    }

    public List<Server> findAll() {
        return serverRepository.findAll();
    }

    public Server addServer(ServerRequest serverRequest) {
        return serverRepository.save(buildServer(serverRequest));
    }

    private Server buildServer(@Nonnull ServerRequest serverRequest) {
        return new Server()
                .setAddress(serverRequest.getAddress())
                .setBandwidth(serverRequest.getBandwidth())
                .setLatency(serverRequest.getLatency())
                .setPurpose(Purpose.find(serverRequest.getPurpose()));
    }

    public void deleteServer(Integer id) {
        serverRepository.delete(id);
    }
}
