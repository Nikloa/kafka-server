package ru.vironit.kafkaserver.service;

import ru.vironit.kafkaserver.dto.StarshipDto;

public interface StarshipService {

    StarshipDto save(StarshipDto dto);

    void send(String topic, String message);

    void consume(StarshipDto dto);
}
