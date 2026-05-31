package com.healthcare.doctorservice.controller;

import com.healthcare.doctorservice.dto.DoctorRequestDTO;
import com.healthcare.doctorservice.dto.DoctorResponseDTO;
import com.healthcare.doctorservice.service.DoctorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public DoctorResponseDTO createDoctor(@RequestBody DoctorRequestDTO requestDTO) {
        return doctorService.createDoctor(requestDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    public DoctorResponseDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DoctorResponseDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDTO requestDTO) {
        return doctorService.updateDoctor(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "Deleted Successfully";
    }
}
