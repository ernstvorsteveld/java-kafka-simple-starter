package com.sternitc.kafkatest;

import com.sternitc.kafkatest.accountinfo.AccountCreatedEventV1;
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
public class TestKafkaProtoTestApplication {

    @Container
    static KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
//                    .withKraft();
//                    .withExposedPorts(9092, 9093)
//                    .withCreateContainerCmdModifier((Consumer<CreateContainerCmd>) cmd -> cmd.withHostConfig(
//                            new HostConfig().withPortBindings(
//                                    new PortBinding(Ports.Binding.bindPort(29092), new ExposedPort(9092)),
//                                    new PortBinding(Ports.Binding.bindPort(29093), new ExposedPort(9093)))
//                    ));

//    @Container
//    static GenericContainer schemaRegistryContainer =
//            new GenericContainer(DockerImageName.parse("confluentinc/cp-schema-registry"))
//                    .dependsOn(kafkaContainer)
//                    .withExposedPorts(8081)
//                    .withCreateContainerCmdModifier((Consumer<CreateContainerCmd>) cmd -> cmd.withHostConfig(
//                            new HostConfig().withPortBindings(
//                                    new PortBinding(Ports.Binding.bindPort(28081), new ExposedPort(8081)))
//                    ))
//                    .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
//                    .withEnv("SCHEMA_REGISTRY_LISTENERS", "http://localhost:8081")
//                    .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "http://0.0.0.0:9092");
////                    .withNetwork(kafkaContainer.getNetwork());

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private KafkaProtoAccountInfoProducer producer;

    @Autowired
    private KafkaProtoAccountInfoConsumer consumer;

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
