package com.mywallapop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@SpringBootApplication
public class SdiEntrega1310Application {

	private static final Logger logger = LogManager.getLogger(SdiEntrega1310Application.class);
	public static void main(String[] args) {
		logger.info("MyWallapop is running");
		SpringApplication.run(SdiEntrega1310Application.class, args);		
	}

}
