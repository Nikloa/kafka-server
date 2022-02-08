package ru.vironit.kafkaserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.vironit.kafkaserver.dto.StarshipDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public StarshipDto save(StarshipDto dto) {
        return null;
    }

    @Override
    public  void send(String topic, String message) {
        log.info(String.format("<= sending {topic:%s, message:%s}", topic, message));
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarshipDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
        String substring = dto.getName().substring(9);
        int number = Integer.parseInt(substring);
        if(number % 2 == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add("There");
            list.add("is");
            list.add("no");
            list.add("spoon");
            for (String s : list) {
                kafkaTemplate.send("3", s);
            }
            log.info(String.format("<= callback {%s}", list));
        }
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
