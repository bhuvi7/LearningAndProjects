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

import com.example.demo.EntityModel.District;
import com.example.demo.repository.DistrictRepository;

@RestController
@RequestMapping(path="/district")
@CrossOrigin(origins = "*")
public class DistrictController {
	
	@Autowired 
	DistrictRepository districtRepository;
	
	@GetMapping("/all")
	public Iterable<District> getAllDistrict(){
		return districtRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<District> getDistrict(@PathVariable(name = "id") int id){
		return districtRepository.findById(id);
	}
	

	@PostMapping(path = "/add")
	public District addDistrict(@RequestBody District District) {
		return districtRepository.save(District);
	}

	@PutMapping(path = "/update")
	public District updateDistrict(@RequestBody District District) {
		return districtRepository.save(District);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteDistrict(@PathVariable(name = "id") int id) {
		districtRepository.deleteById(id);
	}

}
