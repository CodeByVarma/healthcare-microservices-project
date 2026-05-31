package com.healthcare.appointmentservice.repository;

import com.healthcare.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(Long doctorId, LocalDate appointmentDate, LocalTime appointmentTime);

    Appointment findTopByOrderByIdDesc();
}
