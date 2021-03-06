# RESTful

## Тестовые данные
Есть две тестовые таблицы Сервера и Страны:
```sql
CREATE TABLE state
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY CONSTRAINT state_pkey PRIMARY KEY,
    city    VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE server
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY CONSTRAINT server_pkey PRIMARY KEY,
    address   VARCHAR(255),
    bandwidth INTEGER,
    latency   INTEGER,
    purpose   VARCHAR(255),
    state_id  INTEGER CONSTRAINT fk_server_state REFERENCES state
);
```

При старте они заполняются данными ([DatabaseInitializer](src/main/java/ru/romanow/restful/DatabaseInitializer.java)).

Приложение делится на две части:
* RESTful API (/api/**): [ServerRestController](src/main/java/ru/romanow/restful/web/ServerRestController.java), [StateRestController](src/main/java/ru/romanow/restful/web/StateRestController.java).  
Реализует CRUD операции над ресурсами Server и State.
* HATEOAS (/hateoas/**): [HateoasServerRestController](src/main/java/ru/romanow/restful/web/HateoasServerRestController.java), [HateoasStateRestController](src/main/java/ru/romanow/restful/web/HateoasStateRestController.java).  
Реализует только _операции на чтение_ над ресурсами Server и State.

Для RESTful генерируется OpenAPI, для его просмотра локально можно использовать Swagger UI в docker (`docker run -p8081:8080 swaggerapi/swagger-ui:latest`). 

### SSL
```shell script
openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
openssl pkcs12 -inkey key.pem -in certificate.pem -export -out certificate.p12
```

### HTTP
```shell script
curl --http2 -k https://localhost:8880/actuator/health -v (-k allow self-signed certificates)
```
 
### NGINX кэширование
Запустить два инстанса на разных портах
```shell script
java -jar build/libs/restful.jar --server.port=8081
java -jar build/libs/restful.jar --server.port=8082
```
Конфигурация nginx:
```
upstream api {
 server 127.0.0.1:8081 max_fails=3 weight=5;
 server 127.0.0.1:8082 backup;
}

server {
 listen 80;
 server_name test.balance.local;

 location / {
   proxy_set_header Host $host;
   proxy_set_header X-Real-IP $remote_addr;
   proxy_pass http://api;
   proxy_redirect off;
 }
}
```

### NGINX балансировка

Конфигурация nginx:
```
proxy_cache_path /var/cache/nginx levels=1:2 keys_zone=STATIC:32m max_size=1g;

server {
 listen 80;
 server_name test.cache.local;

 location / {
  proxy_cache STATIC;
  proxy_cache_valid any 48h;
  add_header X-Cached $upstream_cache_status;

  proxy_set_header Host $host;
      proxy_set_header X-Real-IP $remote_addr;
      proxy_pass http://127.0.0.1:8080;
      proxy_redirect off;
 }
}
```

Два раза выполнить запрос через curl:
```shell script
curl http://test.cache.local/api/server/1 -v
```
Второй раз в ответ получим заголовок `X-Cached: HIT`, т.е. сервер ответил 302, а тело запроса nginx достал из кэша.  
Для метода `http://localhost:8880/api/state` мы отдаем заголовок `Cache-Control: 60` (перезапросить через 1 минуту) и `ETag`,
на базе которого строится кэширование.    
Для запроса `http://localhost:8880/api/state/1` устанавливается заголовок `Cache-Control: no-cahce`, который указывает промежуточным прокси,
что запрос нельзя кэшировать, а нужно перезапрашивать каждый раз.

## Сборка
1. Установить Java 11.
1. Установить PostgreSQL 11, создать пользователя `program` / `test` и БД `restful`.
1. `chmod a+x gradlew`
1. `./gradlew clean build bootRun` 