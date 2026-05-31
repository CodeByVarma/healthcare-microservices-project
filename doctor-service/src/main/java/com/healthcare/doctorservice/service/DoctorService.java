package com.healthcare.doctorservice.service;

import com.healthcare.doctorservice.dto.DoctorRequestDTO;
import com.healthcare.doctorservice.dto.DoctorResponseDTO;
import com.healthcare.doctorservice.entity.Doctor;
import com.healthcare.doctorservice.exception.ResourceNotFoundException;
import com.healthcare.doctorservice.mapper.DoctorMapper;
import com.healthcare.doctorservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;

    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO) {
        Doctor doctor = DoctorMapper.toDoctorEntity(requestDTO);
        Doctor saved = doctorRepo.save(doctor);
        return DoctorMapper.toResponseDTO(saved);
    }

    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepo.findAll().stream().map(DoctorMapper::toResponseDTO).toList();
    }

    public DoctorResponseDTO getDoctorById(Long id) {

        Doctor doctor = doctorRepo.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));

        return DoctorMapper.toResponseDTO(doctor);
    }

    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorRepo.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));

        DoctorMapper.toUpdateEntity(doctor, requestDTO);
        Doctor saved = doctorRepo.save(doctor);
        return DoctorMapper.toResponseDTO(saved);
    }

    public void deleteDoctor(Long id) {

        Doctor doctor = doctorRepo.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));

        doctorRepo.delete(doctor);
    }
}
