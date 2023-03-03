package com.vinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.vinservice.VinService"})
public class VinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinServiceApplication.class, args);
	}

}
