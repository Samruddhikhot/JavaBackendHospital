package com.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long appointmentId;
    private LocalDateTime appointmentDate;
    private String reason;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
}
