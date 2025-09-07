package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}