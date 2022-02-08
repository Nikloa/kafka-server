package ru.vironit.kafkaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.vironit.kafkaserver.dto.StarshipDto;
import ru.vironit.kafkaserver.publisher.KafkaPublisherSupport;
import ru.vironit.kafkaserver.publisher.KafkaSubscriberSupport;
import ru.vironit.kafkaserver.service.StarshipService;

@RestController
@RequestMapping("starship")
public class StarshipController {

    private final StarshipService service;
    KafkaPublisherSupport publisher = new KafkaPublisherSupport();

    @Autowired
    public StarshipController(StarshipService service) {
        this.service = service;
        publisher.addPropertyChangeListener(new KafkaSubscriberSupport("1", this.service));
        publisher.addPropertyChangeListener(new KafkaSubscriberSupport("2", this.service));
        publisher.addPropertyChangeListener(new KafkaSubscriberSupport("3", this.service));
    }

    @PostMapping
    public void send(@RequestParam String topic, String message) {
        if (!topic.equals("")) {
            service.send(topic, message);
        } else {
            publisher.setMessage(message);
        }
    }
}
