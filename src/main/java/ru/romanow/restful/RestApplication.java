package ru.romanow.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HAL browser docker run -p8081:80 -e ENTRY_POINT=http://localhost:8880/hateoas/server jcassee/hal-browser
// java -jar build/libs/restful-1.0.0.jar --server.port=8081
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
