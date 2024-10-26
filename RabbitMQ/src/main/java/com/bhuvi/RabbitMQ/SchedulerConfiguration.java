package com.bhuvi.RabbitMQ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
@EntityScan
public class SchedulerConfiguration{
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SchedulerConfiguration.class, args);
		 
		
	}
}
