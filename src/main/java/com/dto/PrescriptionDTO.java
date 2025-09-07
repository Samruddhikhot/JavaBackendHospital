package com.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private String medicine;
    private String dosage;
    private String instructions;
    private LocalDate prescribedDate;
    private Long patientId;
    private String patientName;
    private Long doctorId;
}
