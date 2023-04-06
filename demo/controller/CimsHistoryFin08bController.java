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
//Test

import com.example.demo.EntityModel.CimsHistoryFin08b;
import com.example.demo.repository.CimsHistoryFin08bRepository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/cims-history-fin-08b")
@CrossOrigin(origins = "*")
public class CimsHistoryFin08bController {
	
	@Autowired
	private CimsHistoryFin08bRepository cimsHistoryFin08bRepository;
	
	@Autowired
	private Fin02Service fin02Service;

	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin08b> getAllCimsHistoryFin08b(){
		return cimsHistoryFin08bRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin08b> getCimsHistoryFin08bById(@PathVariable (name = "id") int id) {
		return cimsHistoryFin08bRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin08b addCimsHistoryFin08b(@RequestBody CimsHistoryFin08b cimsHistoryFin08b) {
		return cimsHistoryFin08bRepository.save(cimsHistoryFin08b);
	}
	
	@PutMapping(path = "/update")
	public CimsHistoryFin08b updateCimsHistoryFin08b(@RequestBody CimsHistoryFin08b cimsHistoryFin08b) {
		return cimsHistoryFin08bRepository.save(cimsHistoryFin08b);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin08b(@PathVariable(name = "id") int id) {
		 cimsHistoryFin08bRepository.deleteById(id);
	}
	
	@GetMapping(path = "/fin-08b-create-list")
	public Iterable<CimsHistoryFin08b> getFin08bCreateList(){
		return fin02Service.getFin08bCreateList();
	}
	
	@GetMapping(path = "/clinic-id/{clinicId}")
	public Iterable<CimsHistoryFin08b> getCimsHistoryFin08bByClinicId(@PathVariable(name = "clinicId")int clinicId){
		return cimsHistoryFin08bRepository.findByClinicId(clinicId);
	}
	
}
