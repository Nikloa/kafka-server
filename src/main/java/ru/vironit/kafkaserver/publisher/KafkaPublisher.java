package ru.vironit.kafkaserver.publisher;

import java.util.ArrayList;
import java.util.List;

public class KafkaPublisher implements Observable{
    private List<Observer> receiver;
    private String message;

    public KafkaPublisher() {
        receiver = new ArrayList<>();
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        receiver.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        receiver.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : receiver) {
            observer.update(message);
        }
    }
}
