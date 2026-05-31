package com.healthcare.appointmentservice.mapper;

import com.healthcare.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcare.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcare.appointmentservice.entity.Appointment;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentRequestDTO requestDTO) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(requestDTO.getPatientId());
        appointment.setDoctorId(requestDTO.getDoctorId());
        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setAppointmentTime(requestDTO.getAppointmentTime());
        return appointment;
    }

    public static AppointmentResponseDTO toResponseDTO(Appointment saved) {
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setPatientId(saved.getPatientId());
        responseDTO.setDoctorId(saved.getDoctorId());
        responseDTO.setAppointmentDate(saved.getAppointmentDate());
        responseDTO.setAppointmentTime(saved.getAppointmentTime());
        responseDTO.setStatus(saved.getStatus());
        return responseDTO;

    }

    public static void updateEntity(Appointment appointment, AppointmentRequestDTO requestDTO) {
        appointment.setPatientId(requestDTO.getPatientId());
        appointment.setDoctorId(requestDTO.getDoctorId());
        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setAppointmentTime(requestDTO.getAppointmentTime());
    }
}
