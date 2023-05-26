package com.ppv.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppv.notificationservice.config.MQConfig;
import com.ppv.notificationservice.enums.Type;
import com.ppv.notificationservice.event.LogEvent;
import com.ppv.notificationservice.event.StorageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service(NotificationService.QUALIFIER)
@RequiredArgsConstructor
public class NotificationService {

    public static final String QUALIFIER = "notification-service";
    private final RabbitTemplate rabbitTemplate;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(StorageEvent event) {
        log.info("Received Notification message - {}", event.getMessage());

        LogEvent logEvent = LogEvent.builder()
                .service(QUALIFIER)
                .type(Type.INFO)
                .message(event.getMessage())
                .date(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, logEvent);
    }

    public String getSomeNotification() {
        return "Response from Notification service";
    }
}
