package ru.vironit.kafkaserver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import ru.vironit.kafkaserver.publisher.KafkaPublisherSupport;
import ru.vironit.kafkaserver.publisher.KafkaSubscriberSupport;

import java.io.IOException;

@SpringBootApplication
@EnableKafka
@ComponentScan(basePackages = {
        "ru.vironit.kafkaserver.config",
        "ru.vironit.kafkaserver.controller",
        "ru.vironit.kafkaserver.service",
})
@PropertySource({
        "classpath:application.properties"
})
public class KafkaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServerApplication.class, args);
    }

    @Bean
    public JsonDeserializer jsonDeserializer() {
        return new JsonDeserializer() {
            @Override
            public Object deserialize(JsonParser p, DeserializationContext context) throws IOException {
                return null;
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
/*
Два приложения kafka-server и kafka-tester обмениваются между собой сообщениями с помощью Kafka
kafka-server имеет порт для входа через Postman на localhost:9092 и принимает два параметра topic и message
в приложении релизовано три топика
при передаче сообщения в топик 1 оно посылается в kafka-tester в котором по этому топику его принимает два слушателя
оба слушателя находятся под одним groupId поэтому Kafka передает сообщение только последнему подключившемуся из них а второй находится в резерве на случай если первый не сможет принять сообщение
при передаче сообщения в топик 2 оно аналогично посылается в kafka-tester двум слушателяям однако в этот раз у слушателей groupId отличается поэтому они оба принимают сообщение
kafka-tester в свою очередь отправляет на kafka-server сообщения с периодичностью в пять секунд которые представляют собой модель StarshipDto и имеют поля name и model
name имеет свой номер и в случае когда этот номер четный kafka-server отправляет в kafka-tester заготовленный list строк [There, is, no, spoon] который принимается в kafka-tester в топике 3 пакетом в четыре сообщения с помощью BatchListener
Если в topic не указывать ничего сообщение будет разослано всем трём топикам по паттерну издатель-подписчик при этом если отправить то же сообщение повторно оно разослано не будет так как паттерн реагирует на измененное сообщение
*SAGA
 */