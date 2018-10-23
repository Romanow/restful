package ru.romanow.restful.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.repository.ServerRepository;
import ru.romanow.restful.repository.StateRepository;
import ru.romanow.restful.model.ServerRequest;
import ru.romanow.restful.model.ServerResponse;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.springframework.util.Assert.notNull;

/**
 * Created by romanow on 18.10.16
 */
@Service
@AllArgsConstructor
public class ServerServiceImpl
        implements ServerService {
    private final ServerRepository serverRepository;
    private final StateRepository stateRepository;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public ServerResponse getById(@Nonnull Integer serverId) {
        return serverRepository.findById(serverId)
                .map(ServerResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("Server not found for serverId " + serverId));
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<ServerResponse> findAll() {
        return serverRepository.findAll()
                               .stream()
                               .map(ServerResponse::new)
                               .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional
    public Integer addServer(@Nonnull ServerRequest serverRequest) {
        Server server = buildServer(serverRequest);
        State state = getOrCreateState(serverRequest.getCountry(), serverRequest.getCity());
        server.setState(state);

        server = serverRepository.save(server);

        return server.getId();
    }

    @Override
    @Transactional
    public void deleteServer(@Nonnull Integer serverId) {
        serverRepository.deleteById(serverId);
    }

    @Nonnull
    @Override
    @Transactional
    public ServerResponse editServer(@Nonnull Integer serverId, @Nonnull ServerRequest serverRequest) {
        final Optional<Server> byId = serverRepository.findById(serverId);
        if (byId.isPresent()) {
            Server server = byId.get();
            server.setAddress(ofNullable(serverRequest.getAddress()).orElse(server.getAddress()));
            server.setBandwidth(ofNullable(serverRequest.getBandwidth()).orElse(server.getBandwidth()));
            server.setLatency(ofNullable(serverRequest.getLatency()).orElse(server.getLatency()));
            Purpose purpose = ofNullable(serverRequest.getPurpose())
                    .map(Purpose::find)
                    .orElse(server.getPurpose());
            server.setPurpose(purpose);

            if (serverRequest.getCountry() != null && serverRequest.getCity() != null) {
                State state = getOrCreateState(serverRequest.getCountry(), serverRequest.getCity());
                server.setState(state);
            }

            server = serverRepository.save(server);
            return new ServerResponse(server);
        } else {
            throw new EntityNotFoundException("Server not found for serverId " + serverId);
        }
    }

    @Nonnull
    private Server buildServer(@Nonnull ServerRequest serverRequest) {
        return new Server()
                .setAddress(serverRequest.getAddress())
                .setBandwidth(serverRequest.getBandwidth())
                .setLatency(serverRequest.getLatency())
                .setPurpose(Purpose.find(serverRequest.getPurpose()));
    }

    @Nonnull
    private State getOrCreateState(@Nonnull String country, @Nonnull String city) {
        notNull(city, "Country can't be null");
        notNull(city, "City can't be null");

        State state = stateRepository.findByCountryAndCity(country, city);
        if (state == null) {
            state = new State()
                    .setCity(city)
                    .setCountry(country);
            state = stateRepository.save(state);
        }

        return state;
    }
}
