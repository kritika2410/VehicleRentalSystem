package com.cg.rent;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class RentDrive {
	public static void main(String[] args) {
		final Logger logger = LogManager.getLogger(RentDrive.class);
		
		SpringApplication.run(RentDrive.class, args);
		
		logger.info("Rent App Drive started");
		
	}

}
