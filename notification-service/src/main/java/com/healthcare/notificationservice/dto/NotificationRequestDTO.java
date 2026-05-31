package com.healthcare.notificationservice.dto;

import lombok.Data;

@Data
public class NotificationRequestDTO {

    private Long patientId;

    private Long doctorId;

    private String message;
}