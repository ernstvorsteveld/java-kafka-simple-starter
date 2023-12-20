package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.accountinfo.domain.AccountInfo;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaJsonAccountInfoProducer implements AccountInfoProducer {

    private KafkaTemplate<String, AccountInfo> kafkaTemplate;

    public KafkaJsonAccountInfoProducer(KafkaTemplate<String, AccountInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(AccountCreatedEventV1 accountCreatedEvent) {
        kafkaTemplate.send("account-info-created-v1", accountCreatedEvent.getPayload());
    }
}
