package com.ppv.storageservice.service;

import com.ppv.storageservice.config.MQConfig;
import com.ppv.storageservice.dto.StorageRequest;
import com.ppv.storageservice.dto.StorageResponse;
import com.ppv.storageservice.enums.Type;
import com.ppv.storageservice.event.LogEvent;
import com.ppv.storageservice.event.StorageEvent;
import com.ppv.storageservice.model.Note;
import com.ppv.storageservice.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service(StorageService.QUALIFIER)
@RequiredArgsConstructor
public class StorageService {

    public static final String QUALIFIER = "storage-service";
    private final StorageRepository storageRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, StorageEvent> kafkaTemplate;
    private final RabbitTemplate rabbitTemplate;

    public void saveItem(StorageRequest request) {
        storageRepository.save(Note.builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .build());

        String response = webClientBuilder.build().get()
                .uri("http://notification-service/api/notification")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Received message from Notification service - {}", response);

        kafkaTemplate.send("notificationTopic", new StorageEvent("Greetings from Storage service"));

        LogEvent logEvent = LogEvent.builder()
                .service(QUALIFIER)
                .type(Type.INFO)
                .message("Log from Storage service")
                .date(LocalDateTime.now())
                .build();


        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, logEvent);

    }

    public List<StorageResponse> getAllItems() {
        return storageRepository.findAll().stream().map(this::toResponse).toList();
    }

    private StorageResponse toResponse(Note note) {
        StorageResponse response = new StorageResponse();
        response.setQuestion(note.getQuestion());
        response.setAnswer(note.getAnswer());

        return response;
    }
}
