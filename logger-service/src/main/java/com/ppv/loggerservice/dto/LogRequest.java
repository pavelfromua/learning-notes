package com.ppv.loggerservice.dto;

import com.ppv.loggerservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {
    private String service;
    private Type type;
    private String message;
    private LocalDateTime date;
}
