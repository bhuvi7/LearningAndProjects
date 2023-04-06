package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//Test

import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.repository.CammsBORepository;
import com.example.demo.repository.CimsHistoryFin01Repository;
import com.example.demo.service.CammsDataMigrationService;
import com.example.demo.service.Fin01Service;
import com.google.api.client.util.DateTime;
import com.google.cloud.Date;

@RestController
@RequestMapping(path="/camss-data-migration")
@CrossOrigin(origins = "*")
public class CammsDataMigrationController {
	
	//@CreationTimestamp
	//private LocalDateTime createdDate = LocalDateTime.now();
	
	@Autowired
	private CammsBORepository cammsBORepository;
	
	@Autowired
	private CammsDataMigrationService cammsDataMigrationService;
	
	@GetMapping(path = "/all")
	public String all() {
		LocalDateTime createdDate = LocalDateTime.now();
		return "Test " + createdDate ;
	}
	
	@GetMapping(path = "/fin01-migration")
	public void migrateFin01Data() {
		cammsDataMigrationService.dataMigrateCammsBOToCimsHistoryFin01();
	}
	
	@GetMapping(path = "/fin02a-migration")
	public void migrateFin02aData() {
		cammsDataMigrationService.dataMigrateCammsBOToCimsHistoryFin02a();
	}
	
	@GetMapping(path = "/fin08b-migration")
	public void migrateFin08bData() {
		cammsDataMigrationService.dataMigrateCammsBOToCimsHistoryFin08b();
	}
	
	@GetMapping(path = "/fin08c-migration")
	public void migrateFin08cData() {
		cammsDataMigrationService.dataMigrateCammsBOToCimsHistoryFin08c();
	}

}
