package com.sternitc.kafkatest.accountinfo;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class SimpleEvent<T> {

    private final String dateTime = Instant.now().toString();
    private final String version;
    private final T payload;

    protected SimpleEvent(String version, T payload) {
        this.version = version;
        this.payload = payload;
    }
}
