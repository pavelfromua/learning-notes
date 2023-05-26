package com.ppv.loggerservice.services;

import com.ppv.loggerservice.config.MQConfig;
import com.ppv.loggerservice.dto.LogRequest;
import com.ppv.loggerservice.model.Log;
import com.ppv.loggerservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogListener {
    private final LogRepository logRepository;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(LogRequest logRequest) {
        logRepository.save(Log.builder()
                .service(logRequest.getService())
                .type(logRequest.getType())
                .message(logRequest.getMessage())
                .date(logRequest.getDate())
                .build());
    }

}
