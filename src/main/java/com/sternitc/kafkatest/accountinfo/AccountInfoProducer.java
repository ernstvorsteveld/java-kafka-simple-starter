package com.sternitc.kafkatest.accountinfo;

public interface AccountInfoProducer {

    void send(AccountCreatedEventV1 accountCreatedEvent);
}
