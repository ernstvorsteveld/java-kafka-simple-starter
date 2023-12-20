package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
public class KafkaProtoAccountInfoConsumer implements AccountInfoConsumer {

    private final BankAccountReceiverHandler<AccountInfoProto.BankAccount> bankAccountReceiverHandler;

    public KafkaProtoAccountInfoConsumer(BankAccountReceiverHandler<AccountInfoProto.BankAccount> bankAccountReceiverHandler) {
        this.bankAccountReceiverHandler = bankAccountReceiverHandler;
    }

    @Override
    @KafkaListener(topics = "account-info-created-v1", groupId = "my-group-id")
    public void listen(
            @Payload AccountInfoProto.BankAccount message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received a message contains a user information with id {}, from {} topic, " +
                "{} partition, and {} offset", message.getId(), topic, partition, offset);
        bankAccountReceiverHandler.handle(message);
    }
}
