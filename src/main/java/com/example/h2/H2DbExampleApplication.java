package com.example.h2;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2DbExampleApplication {

	static Logger logger = Logger.getLogger(H2DbExampleApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(H2DbExampleApplication.class, args);
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		
		logger.info("Info Level Log Message");
		logger.debug("Debug Level Log Message");
		logger.error("Error Level Log Message");
	}

}