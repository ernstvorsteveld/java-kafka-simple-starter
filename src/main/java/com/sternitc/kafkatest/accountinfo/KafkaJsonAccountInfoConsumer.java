package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaJsonAccountInfoConsumer implements AccountInfoConsumer {

    @KafkaListener(topics = "account-info-created-v1", groupId = "my-group-id")
    @Override
    public void listen(AccountInfoProto.BankAccount message, String topic, Integer partition, Long offset) {
        log.info("Received a message contains a user information with id {}, from {} topic, " +
                "{} partition, and {} offset", message.getId(), topic, partition, offset);
        log.info(message.toString());
    }
}
