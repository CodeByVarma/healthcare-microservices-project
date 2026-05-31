package com.healthcare.patientservice.controller;

import com.healthcare.patientservice.dto.PatientRequestDTO;
import com.healthcare.patientservice.dto.PatientResponseDTO;
import com.healthcare.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Api's", description = "Operations Realted To Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Patient", description = "Creates A New Patient Record"

    )
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN')")
    public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO requestDTO) {
        return patientService.createPatient(requestDTO);
    }

    @GetMapping
    @Operation(summary = "Get All Patients", description = "Fetches All Patients Details")
    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN','PATIENT')")
    public List<PatientResponseDTO> getAllPatients() {
        System.out.println("Called getbypatient");
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Patient By Id", description = "Fetches A Patient By Using Id")
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN')")
    public PatientResponseDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patient", description = "Updates Existing Patient Details")
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN','DOCTOR')")
    public PatientResponseDTO updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO requestDTO) {
        return patientService.updatePatient(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient", description = "Deletes Patient Details By Id")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "Patient Deleted Successfully";
    }
}


