package ru.romanow.restful.domain;

import java.util.stream.Stream;

public enum Purpose {
    FRONTEND,
    BACKEND,
    DATABASE;

    public static Purpose find(String purpose) {
        return Stream.of(Purpose.values())
                     .filter(p -> p.name().equals(purpose))
                     .findFirst()
                     .orElseGet(null);
    }
}
