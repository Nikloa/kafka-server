package ru.vironit.kafkaserver.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vironit.kafkaserver.service.StarshipService;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Slf4j
@Service
public class KafkaSubscriberSupport implements PropertyChangeListener {

    private StarshipService starshipService;
    private String name;

    public KafkaSubscriberSupport(String name, StarshipService starshipService) {
        this.starshipService = starshipService;
        this.name = name;
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        this.setMessage((String) propertyChangeEvent.getNewValue());
    }

    public void setMessage(String message) {
        starshipService.send(name, message);
    }
}
