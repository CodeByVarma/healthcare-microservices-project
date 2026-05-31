package com.healthcare.appointmentservice.controller;

import com.healthcare.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcare.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcare.appointmentservice.service.AppointmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('PATIENT')")
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentRequestDTO requestDTO) {
        return appointmentService.createAppointment(requestDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public List<AppointmentResponseDTO> getAlLAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN','DOCTOR')")
    public AppointmentResponseDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public AppointmentResponseDTO updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO requestDTO) {
        return appointmentService.updateAppointment(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PATIENT','RECEPTIONIST')")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "CANCELED";
    }
}
