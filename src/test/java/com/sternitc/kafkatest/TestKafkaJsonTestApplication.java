package com.sternitc.kafkatest;

import com.sternitc.kafkatest.accountinfo.AccountCreatedEventV1;
import com.sternitc.kafkatest.accountinfo.KafkaJsonAccountInfoProducer;
import com.sternitc.kafkatest.accountinfo.KafkaProtoAccountInfoConsumer;
import com.sternitc.kafkatest.accountinfo.KafkaProtoAccountInfoProducer;
import com.sternitc.kafkatest.accountinfo.domain.AccountInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@Testcontainers
@SpringBootTest
public class TestKafkaJsonTestApplication {

    @Container
    static KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private KafkaJsonAccountInfoProducer producer;


    @Test
    public void should_produce_and_consume() {
        producer.send(accountCreatedEvent());
    }

    private AccountCreatedEventV1 accountCreatedEvent() {
        String id = UUID.randomUUID().toString();
        String uuid = UUID.randomUUID().toString();
        String iban = "my-iban";
        AccountInfo accountInfo = AccountInfo
                .builder()
                .id(id)
                .uuid(uuid)
                .iban(iban)
                .balance(1f)
                .build();
        return new AccountCreatedEventV1(accountInfo);
    }
}
