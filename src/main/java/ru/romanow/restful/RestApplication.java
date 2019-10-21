package ru.romanow.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Swagger docker run -p8081:8080 swaggerapi/swagger-ui:latest
 *
 * # SSL
 * openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
 * openssl pkcs12 -inkey key.pem -in certificate.pem -export -out certificate.p12
 *
 * # HTTP/2 curl --http2 -k https://localhost:8880/actuator/health -v (-k allow self-signed certificates)
 * # HAL browser docker run -p8081:80 -e ENTRY_POINT=http://localhost:8880/hateoas/server jcassee/hal-browser

 * java -jar build/libs/restful.jar --server.port=8081
 ## nginx cache
 GET http://test.cache.local/api/server/1
 X-Cached: HIT
 PATCH http://test.cache.local/api/server/1
 {
     "id": 1,
     "address": "SPB",
     "purpose": "BACKEND",
     "latency": 5,
     "bandwidth": 5,
     "stateId": 1
 }
 GET http://test.cache.local/api/server/1

 ## ETag http://localhost:8880/api/state
 ## No cache http://localhost:8880/api/state/1
 */
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
