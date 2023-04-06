package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.ClinicType;
import com.example.demo.repository.ClinicTypeRepository;

@RestController
@RequestMapping(path="/clinic-type")
@CrossOrigin(origins = "*")
public class ClinicTypeController {

	@Autowired
	ClinicTypeRepository clinicTypeRepository;
	
	@GetMapping(path = "/all")
	public Iterable<ClinicType> getAllClinic() {
		return clinicTypeRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<ClinicType> getClinicById(@PathVariable(name = "id") int id) {
		return clinicTypeRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public ClinicType addClinic(@RequestBody ClinicType clinicType) {
		return clinicTypeRepository.save(clinicType);
	}

	@PutMapping(path = "/update")
	public ClinicType updateClinic(@RequestBody ClinicType clinicType) {
		return clinicTypeRepository.save(clinicType);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin10b(@PathVariable(name = "id") int id) {
		clinicTypeRepository.deleteById(id);
	}
}
