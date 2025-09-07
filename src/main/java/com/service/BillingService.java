package com.service;

import java.util.List;

import com.model.Billing;

public interface BillingService {
	// Billing createBilling(Billing billing);
	    Billing getBillingById(Long id);
	    List<Billing> getAllBillings();
	  Billing updateBilling(Long id, Billing billing);
	    void deleteBilling(Long id);
		Billing createBilling(Billing billing);
}
