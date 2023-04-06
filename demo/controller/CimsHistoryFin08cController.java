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

import com.example.demo.EntityModel.CimsHistoryFin08c;
import com.example.demo.repository.CimsHistoryFin08cRepository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/cims-history-fin-08c")
@CrossOrigin(origins = "*")
public class CimsHistoryFin08cController {
	
	@Autowired
	private CimsHistoryFin08cRepository cimsHistoryFin08cRepository;
	
	@Autowired
	private Fin02Service fin02Service;

	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin08c> getAllCimsHistoryFin08c(){
		return cimsHistoryFin08cRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin08c> getCimsHistoryFin08cById(@PathVariable (name = "id") int id) {
		return cimsHistoryFin08cRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin08c addCimsHistoryFin08c(@RequestBody CimsHistoryFin08c cimsHistoryFin08c) {
		return cimsHistoryFin08cRepository.save(cimsHistoryFin08c);
	}
	
	@PutMapping(path = "/update")
	public CimsHistoryFin08c updateCimsHistoryFin08c(@RequestBody CimsHistoryFin08c cimsHistoryFin08c) {
		return cimsHistoryFin08cRepository.save(cimsHistoryFin08c);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin08c(@PathVariable(name = "id") int id) {
		 cimsHistoryFin08cRepository.deleteById(id);
	}
	
	@GetMapping(path = "/fin-08c-create-list")
	public Iterable<CimsHistoryFin08c> getFin08cCreateList(){
		return fin02Service.getFin08cCreateList();
	}
	
	@GetMapping(path = "/clinic-id/{clinicId}")
	public Iterable<CimsHistoryFin08c> getCimsHistoryFin08cByClinicId(@PathVariable(name = "clinicId")int clinicId){
		return cimsHistoryFin08cRepository.findByClinicId(clinicId);
	}
	
}
