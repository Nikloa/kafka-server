package ru.vironit.kafkaserver.publisher;

public interface Observer {
    void update (String message);
}
