package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;
import com.sternitc.kafkatest.accountinfo.domain.AccountInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class AccountInfoConfiguration {

    //    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
    @Bean
    public KafkaProtoAccountInfoProducer producer(
            KafkaTemplate<String, AccountInfoProto.BankAccount> kafkaTemplate) {
        return new KafkaProtoAccountInfoProducer(kafkaTemplate);
    }
//
//    @Bean
//    public KafkaTemplate<String, AccountInfoProto.BankAccount> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public DefaultKafkaProducerFactory<String, AccountInfoProto.BankAccount> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigsBankAccountProperties());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigsBankAccountProperties() {
//        Map<String, Object> configProperties = new HashMap<>();
//        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer");
//        configProperties.put("auto.register.schema", false);
//        configProperties.put("schema.registry.url", "http://localhost:8081");
//        return configProperties;
//    }

    @Bean
    public BankAccountReceiverHandler<AccountInfoProto.BankAccount> bankAccountReceiverHandler() {
        return new BankAccountReceiverHandlerImpl();
    }

//    @Bean
//    public ConsumerFactory<String, AccountInfoProto.BankAccount> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigsBankAccountProperties());
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigsBankAccountProperties() {
//        Map<String, Object> configProperties = new HashMap<>();
//        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
//        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer");
//        return configProperties;
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, AccountInfoProto.BankAccount> factory() {
//        ConcurrentKafkaListenerContainerFactory<String, AccountInfoProto.BankAccount> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

    @Bean
    public KafkaProtoAccountInfoConsumer kafkaAccountInfoConsumer() {
        return new KafkaProtoAccountInfoConsumer(bankAccountReceiverHandler());
    }

    @Bean
    @Order(-1)
    public NewTopic createNewTopic() {
        return new NewTopic("account-info-created-v1", 1, (short) 1);
    }

    @Bean
    public KafkaJsonAccountInfoProducer kafkaJsonAccountInfoProducer(
            KafkaTemplate<String, AccountInfo> kafkaTemplate) {
        return new KafkaJsonAccountInfoProducer(kafkaTemplate);
    }
}
