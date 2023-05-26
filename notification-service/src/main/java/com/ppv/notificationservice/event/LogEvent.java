package com.ppv.notificationservice.event;

import com.ppv.notificationservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {
    private String service;
    private Type type;
    private String message;
    private LocalDateTime date;
}
