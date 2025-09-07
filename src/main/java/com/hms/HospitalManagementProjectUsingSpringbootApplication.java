package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = "com.hms")
@ComponentScan({"com.config","com.hms","com.controller","com.dao","com.model","com.service","com.exception","com.dto","com.security"})
@EntityScan("com.model")

@EnableJpaRepositories(basePackages="com.dao")


public class HospitalManagementProjectUsingSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementProjectUsingSpringbootApplication.class, args);
	}

}
