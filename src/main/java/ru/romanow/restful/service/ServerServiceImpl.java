package ru.romanow.restful.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.model.ServerResponse;
import ru.romanow.restful.model.StateResponse;
import ru.romanow.restful.repository.ServerRepository;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static ru.romanow.restful.domain.Purpose.find;

@Service
@AllArgsConstructor
public class ServerServiceImpl
        implements ServerService {
    private final ServerRepository serverRepository;
    private final StateService stateService;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public ServerResponse getServerById(@Nonnull Integer id) {
        return serverRepository.findById(id)
                .map(this::buildServerResponse)
                .orElseThrow(() -> new EntityNotFoundException(format("Server not found for ID:%d", id)));
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<ServerResponse> findAllServers() {
        return serverRepository
                .findAll()
                .stream()
                .map(this::buildServerResponse)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional
    public List<ServerResponse> findServersByAddress(@Nonnull String address) {
        return serverRepository
                .findServersByAddress(address)
                .stream()
                .map(this::buildServerResponse)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional
    public Integer addServer(@Nonnull ServerRequest serverRequest) {
        Server server = new Server()
                .setAddress(serverRequest.getAddress())
                .setLatency(serverRequest.getLatency())
                .setBandwidth(serverRequest.getBandwidth())
                .setPurpose(find(serverRequest.getPurpose()));
        State state = stateService.findStateById(serverRequest.getStateId());
        server.setState(state);

        server = serverRepository.save(server);

        return server.getId();
    }

    @Override
    @Transactional
    public void deleteServer(@Nonnull Integer id) {
        serverRepository.deleteById(id);
    }

    @Nonnull
    @Override
    @Transactional
    public ServerResponse editServer(@Nonnull Integer id, @Nonnull ServerRequest serverRequest) {
        final Optional<Server> byId = serverRepository.findById(id);
        if (byId.isPresent()) {
            Server server = byId.get();
            server.setAddress(ofNullable(serverRequest.getAddress()).orElse(server.getAddress()));
            server.setBandwidth(ofNullable(serverRequest.getBandwidth()).orElse(server.getBandwidth()));
            server.setLatency(ofNullable(serverRequest.getLatency()).orElse(server.getLatency()));
            Purpose purpose = ofNullable(serverRequest.getPurpose())
                    .map(Purpose::find)
                    .orElse(server.getPurpose());
            server.setPurpose(purpose);

            if (serverRequest.getStateId() != null) {
                State state = stateService.findStateById(serverRequest.getStateId());
                server.setState(state);
            }

            server = serverRepository.save(server);

            return buildServerResponse(server);
        } else {
            throw new EntityNotFoundException(format("Server not found for ID:%d", id));
        }
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public StateResponse getServerState(@Nonnull Integer id) {
        final ServerResponse server = getServerById(id);
        return stateService.getStateById(server.getStateId());
    }

    @Nonnull
    private ServerResponse buildServerResponse(@Nonnull Server server) {
        return new ServerResponse()
                .setId(server.getId())
                .setAddress(server.getAddress())
                .setBandwidth(server.getBandwidth())
                .setLatency(server.getLatency())
                .setPurpose(server.getPurpose())
                .setStateId(server.getState().getId());
    }
}
