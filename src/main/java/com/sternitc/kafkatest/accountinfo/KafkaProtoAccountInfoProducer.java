package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;
import com.sternitc.kafkatest.accountinfo.domain.AccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;

@Slf4j
public class KafkaProtoAccountInfoProducer implements AccountInfoProducer {

    private KafkaTemplate<String, AccountInfoProto.BankAccount> kafkaTemplate;

    public KafkaProtoAccountInfoProducer(KafkaTemplate<String, AccountInfoProto.BankAccount> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(AccountCreatedEventV1 accountCreatedEvent) {
        log.info("About to produce AccountInfoCreated event: {}", accountCreatedEvent);
        AccountInfo payload = accountCreatedEvent.getPayload();
        AccountInfoProto.BankAccount account = AccountInfoProto.BankAccount
                .newBuilder()
                .setId(payload.getId())
                .setIban(payload.getIban())
                .setBalance(payload.getBalance())
                .setDateTime(Instant.now().toString())
                .build();
        kafkaTemplate.send("account-info-created-v1", account);
    }
}
