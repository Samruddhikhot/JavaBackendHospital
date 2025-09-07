

package com.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicine;
    private String dosage;
    private String instructions;
    private LocalDate prescribedDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference // Avoid recursion when serializing Patient -> Prescription
    @JsonIgnoreProperties({"appointments","bills","prescriptions","assignedDoctor"})
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties({"patients"})
    private Doctor doctor;
}
