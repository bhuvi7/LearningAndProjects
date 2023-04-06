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

import com.example.demo.EntityModel.Circle;
import com.example.demo.EntityModel.Clinic;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.repository.ClinicRepository;

@RestController
@RequestMapping(path="/clinic")
@CrossOrigin(origins = "*")
public class ClinicController {

	@Autowired
	ClinicRepository clinicRepository;
	
	@GetMapping(path = "/all")
	public Iterable<Clinic> getAllClinic() {
		return clinicRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Clinic> getClinicById(@PathVariable(name = "id") int id) {
		return clinicRepository.findById(id);
	}
	
	@GetMapping("/district-id/{id}")
	public List<Clinic> getClinicByDistrictId(@PathVariable(name = "id") Integer districtId){
		return clinicRepository.findByDistrictId(districtId);
	}
	
	@GetMapping("/districtid-clinictypeid/{districtId},{clinicTypeId}")
	public List<Clinic> getClinicByDistrictIdAndClinicTypeId(@PathVariable(name = "districtId") Integer districtId,
			@PathVariable(name = "clinicTypeId") Integer clinicTypeId){
		return clinicRepository.findByDistrictIdAndClinicTypeId(districtId,clinicTypeId);
	}
	
	@PostMapping(path = "/add")
	public Clinic addClinic(@RequestBody Clinic clinic) {
		System.out.println(clinic); 
		return clinicRepository.save(clinic);
	}
	
//	@PostMapping(path = "/createFIN01/{userId}")
//	public Invoice addInvoice(@RequestBody List<Fin06> Fin06,@PathVariable(name = "userId") int userId) {
//		return fin01Service.addFin01Invoice(Fin06,userId);
//	}

	@PutMapping(path = "/update")
	public Clinic updateClinic(@RequestBody Clinic clinic) {
		return clinicRepository.save(clinic);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteById(@PathVariable(name = "id") int id) {
		clinicRepository.deleteById(id);
	}
}
