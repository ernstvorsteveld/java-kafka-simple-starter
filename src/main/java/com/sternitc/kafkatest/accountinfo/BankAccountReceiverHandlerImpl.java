package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankAccountReceiverHandlerImpl implements BankAccountReceiverHandler<AccountInfoProto.BankAccount> {
    @Override
    public void handle(AccountInfoProto.BankAccount payload) {
        log.debug("Received message: {}.", payload);
    }
}
