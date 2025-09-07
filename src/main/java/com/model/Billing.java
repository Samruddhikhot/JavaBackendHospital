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
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    private LocalDate billingDate;
    private Double amount;
    private String description;
    
    private String status; // "PAID" or "PENDING"

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference // Avoid infinite recursion when serializing Patient -> Billing
    @JsonIgnoreProperties({"appointments","bills","prescriptions","assignedDoctor"})
    private Patient patient;
}
