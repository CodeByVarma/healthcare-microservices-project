package com.healthcare.appointmentservice.service;

import com.healthcare.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcare.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcare.appointmentservice.dto.NotificationRequestDTO;
import com.healthcare.appointmentservice.entity.Appointment;
import com.healthcare.appointmentservice.exception.ResourceNotFoundException;
import com.healthcare.appointmentservice.exception.SlotAlreadyBookedException;
import com.healthcare.appointmentservice.external.NotificationClient;
import com.healthcare.appointmentservice.mapper.AppointmentMapper;
import com.healthcare.appointmentservice.repository.AppointmentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);

    private final NotificationClient notificationClient;
    private final AppointmentRepository appointmentRepo;

    public AppointmentService(NotificationClient notificationClient, AppointmentRepository appointmentRepo) {
        this.notificationClient = notificationClient;
        this.appointmentRepo = appointmentRepo;
    }

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {

        boolean exists = appointmentRepo.existsByDoctorIdAndAppointmentDateAndAppointmentTime(requestDTO.getDoctorId(), requestDTO.getAppointmentDate(), requestDTO.getAppointmentTime());

        if (exists) {
            throw new SlotAlreadyBookedException("SLOT ALREADY BOOKED");
        }

        Appointment appointment = AppointmentMapper.toEntity(requestDTO);

        appointment.setStatus("BOOKED");

        Appointment saved = appointmentRepo.save(appointment);

        NotificationRequestDTO dto = new NotificationRequestDTO();

        dto.setPatientId(saved.getPatientId());

        dto.setDoctorId(saved.getDoctorId());

        dto.setMessage("Appointment Booked Successfully");

        sendNotification(dto);

        return AppointmentMapper.toResponseDTO(saved);
    }

    @Retry(name = "notificationService", fallbackMethod = "notificationFallBack")
    @CircuitBreaker(name = "notificationService")
    public void sendNotification(NotificationRequestDTO dto) {

        log.info("Calling Notification Service");

        notificationClient.sendNotification(dto);
    }

    public void notificationFallBack(
            NotificationRequestDTO dto,
            Throwable ex
    ) {

        log.error(
                "Notification Service Down : {}",
                ex.getMessage()
        );
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepo.findAll().stream().map(AppointmentMapper::toResponseDTO).toList();
    }

    public AppointmentResponseDTO getAppointmentById(Long id) {
        log.info("Fetching appointment id : {}", id);
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));
        return AppointmentMapper.toResponseDTO(appointment);
    }

    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));
        AppointmentMapper.updateEntity(appointment, requestDTO);
        Appointment saved = appointmentRepo.save(appointment);
        return AppointmentMapper.toResponseDTO(saved);
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));
        appointment.setStatus("CANCELLED");
        appointmentRepo.save(appointment);
    }

}
