package ru.romanow.restful;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;
import ru.romanow.restful.domain.State;
import ru.romanow.restful.repository.ServerRepository;
import ru.romanow.restful.repository.StateRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
@DependsOn("entityManagerFactory")
@RequiredArgsConstructor
public class DatabaseInitializer
        implements ApplicationRunner {
    private static final Logger logger = getLogger(DatabaseInitializer.class);

    private final StateRepository stateRepository;
    private final ServerRepository serverRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        logger.info("========== Start database initializer ==========");

        final State moscow = stateRepository.save(new State()
                .setCity("Moscow")
                .setCountry("Russia"));

        final State spb = stateRepository.save(new State()
                .setCity("SPb")
                .setCountry("Russia"));

        final Server server1 = new Server()
                .setAddress("Moscow")
                .setBandwidth(1000)
                .setLatency(10)
                .setPurpose(Purpose.BACKEND)
                .setState(moscow);

        final Server server2 = new Server()
                .setAddress("Moscow")
                .setBandwidth(10000)
                .setLatency(5)
                .setPurpose(Purpose.DATABASE)
                .setState(moscow);

        final Server server3 = new Server()
                .setAddress("Moscow")
                .setBandwidth(5000)
                .setLatency(5)
                .setPurpose(Purpose.FRONTEND)
                .setState(moscow);

        final Server server4 = new Server()
                .setAddress("SPb")
                .setBandwidth(10000)
                .setLatency(5)
                .setPurpose(Purpose.BACKEND)
                .setState(spb);

        serverRepository.saveAll(List.of(server1, server2, server3, server4));

        logger.info("========== Finish database initializer ==========");
    }
}
