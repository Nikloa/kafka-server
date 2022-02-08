package ru.vironit.kafkaserver.publisher;

import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSubscriber implements Observer{

    private String name;
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSubscriber(String name, Observable observable, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.name = name;
        observable.registerObserver(this);
    }

    @Override
    public void update(String message) {
        kafkaTemplate.send(name, message);
    }
}
