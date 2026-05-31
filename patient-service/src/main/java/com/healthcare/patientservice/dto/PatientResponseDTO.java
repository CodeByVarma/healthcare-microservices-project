package com.healthcare.patientservice.dto;

import lombok.*;

@Data
public class PatientResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String disease;

}
