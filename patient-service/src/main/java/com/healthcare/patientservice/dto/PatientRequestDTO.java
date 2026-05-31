package com.healthcare.patientservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50)
    private String name;

    @Min(value = 0)
    @Max(value = 120)
    private int age;

    @NotBlank(message = "Disease cannot be empty")
    private String disease;

}
