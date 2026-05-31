package com.healthcare.notificationservice.service;

import com.healthcare.notificationservice.dto.NotificationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log =

            LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(NotificationRequestDTO dto) {

        log.info(

                "Notification sent to patient {} for doctor {} message {}",

                dto.getPatientId(),

                dto.getDoctorId(),

                dto.getMessage());
    }
}