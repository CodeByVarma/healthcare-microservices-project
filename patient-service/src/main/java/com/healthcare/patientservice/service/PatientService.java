package com.healthcare.patientservice.service;

import com.healthcare.patientservice.dto.PatientRequestDTO;
import com.healthcare.patientservice.dto.PatientResponseDTO;
import com.healthcare.patientservice.entity.Patient;
import com.healthcare.patientservice.exception.ResourceNotFoundException;
import com.healthcare.patientservice.mapper.PatientMapper;
import com.healthcare.patientservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepo;

    public PatientService(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {

        log.info("Creating Patient With Name : {}", requestDTO.getName());

        Patient patientEntity = PatientMapper.toEntity(requestDTO);
        Patient saved = patientRepo.save(patientEntity);

        log.info("Patient Created Successfully With Id : {}", saved.getId());

        return PatientMapper.toResponseDTO(saved);
    }

    public List<PatientResponseDTO> getAllPatients() {

        log.info("Fetching All Patients");

        return patientRepo.findAll()
                .stream()
                .map(PatientMapper::toResponseDTO)
                .toList();
    }

    public PatientResponseDTO getPatientById(Long id) {

        log.info("Fetching Patient With Id: {}", id);

        Patient patientEntity = patientRepo.findById(id).orElseThrow(() -> {
            log.error("Patient not found with id: {}", id);
            return new ResourceNotFoundException("Patient Not Found");
        });
        return PatientMapper.toResponseDTO(patientEntity);
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO) {

        Patient patientEntity = patientRepo.findById(id).orElseThrow(() -> {
                    log.error("Patient not found with id: {}", id);
                    return new ResourceNotFoundException("Patient Not Found");
                }
        );
        log.info("Updating patient with id: {}", id);
        PatientMapper.updateEntity(patientEntity, requestDTO);
        Patient saved = patientRepo.save(patientEntity);
        log.info("Patient updated successfully with id: {}", id);
        return PatientMapper.toResponseDTO(saved);
    }


    public void deletePatient(Long id) {

        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> {

                    log.error("Patient not found with id: {}", id);

                    return new ResourceNotFoundException(
                            "Patient Not Found");
                });

        log.info("Deleting patient with id: {}", id);

        patientRepo.delete(patient);

        log.info("Patient deleted successfully with id: {}", id);
    }


}
