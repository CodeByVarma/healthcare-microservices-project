package com.healthcare.doctorservice.dto;

import lombok.Data;

@Data
public class DoctorRequestDTO {

    private String name;
    private String specialization;
    private Integer experience;
    private Boolean availability;
    private String email;

}
