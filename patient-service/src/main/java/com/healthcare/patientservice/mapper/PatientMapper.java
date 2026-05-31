package com.healthcare.patientservice.mapper;

import com.healthcare.patientservice.dto.PatientRequestDTO;
import com.healthcare.patientservice.dto.PatientResponseDTO;
import com.healthcare.patientservice.entity.Patient;

public final class PatientMapper {

    private PatientMapper(){
    }

    public static Patient toEntity(PatientRequestDTO requestDTO){
        Patient patient = new Patient();
        patient.setName(requestDTO.getName());
        patient.setAge(requestDTO.getAge());
        patient.setDisease(requestDTO.getDisease());
        return patient;
    }

    public static PatientResponseDTO toResponseDTO(Patient patient){
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        responseDTO.setId(patient.getId());
        responseDTO.setName(patient.getName());
        responseDTO.setAge(patient.getAge());
        responseDTO.setDisease(patient.getDisease());
        return responseDTO;
    }


    public static void updateEntity(Patient patientEntity, PatientRequestDTO requestDTO) {
        patientEntity.setName(requestDTO.getName());
        patientEntity.setAge(requestDTO.getAge());
        patientEntity.setDisease(requestDTO.getDisease());
    }
}
