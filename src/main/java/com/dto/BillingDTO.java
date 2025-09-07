package com.dto;

import lombok.Data;

@Data
public class BillingDTO {
	private Long billingId; 
    private String billingDate;
    private Double amount;
    private String description;
    private Long patientId;
    private String status;
}
