package com.sternitc.kafkatest.accountinfo;

public interface BankAccountReceiverHandler<T> {

    void handle(T payload);
}
