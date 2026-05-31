package com.healthcare.authservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ErrorResponseDTO {

    private String errorCode;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;

}
