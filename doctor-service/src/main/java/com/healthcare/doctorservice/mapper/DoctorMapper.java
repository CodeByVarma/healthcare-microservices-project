package com.healthcare.doctorservice.mapper;

import com.healthcare.doctorservice.dto.DoctorRequestDTO;
import com.healthcare.doctorservice.dto.DoctorResponseDTO;
import com.healthcare.doctorservice.entity.Doctor;

public class DoctorMapper {
    public static Doctor toDoctorEntity(DoctorRequestDTO requestDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(requestDTO.getName());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setExperience(requestDTO.getExperience());
        doctor.setAvailability(requestDTO.getAvailability());
        doctor.setEmail(requestDTO.getEmail());
        return doctor;
    }

    public static DoctorResponseDTO toResponseDTO(Doctor saved) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        doctorResponseDTO.setId(saved.getId());
        doctorResponseDTO.setName(saved.getName());
        doctorResponseDTO.setSpecialization(saved.getSpecialization());
        doctorResponseDTO.setExperience(saved.getExperience());
        doctorResponseDTO.setAvailability(saved.getAvailability());
        doctorResponseDTO.setEmail(saved.getEmail());
        return doctorResponseDTO;
    }

    public static void toUpdateEntity(Doctor doctor, DoctorRequestDTO requestDTO) {
        doctor.setName(requestDTO.getName());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setExperience(requestDTO.getExperience());
        doctor.setAvailability(requestDTO.getAvailability());
        doctor.setEmail(requestDTO.getEmail());
    }
}
