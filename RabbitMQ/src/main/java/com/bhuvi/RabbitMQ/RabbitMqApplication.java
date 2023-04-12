package com.bhuvi.RabbitMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import lombok.Getter;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
@RequestMapping("/api")
public class RabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}
	
	@GetMapping("/display")
	public String display() {
		return "Working";
	}

}
