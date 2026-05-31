package com.healthcare.notificationservice.controller;

import com.healthcare.notificationservice.dto.NotificationRequestDTO;
import com.healthcare.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping

    public String sendNotification(@RequestBody NotificationRequestDTO dto) {

        notificationService.sendNotification(dto);

        return "Notification Sent";
    }
}