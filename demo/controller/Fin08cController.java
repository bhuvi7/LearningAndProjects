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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.CimsHistoryFin02;
import com.example.demo.EntityModel.CimsHistoryFin08c;
import com.example.demo.EntityModel.Fin08c;
import com.example.demo.repository.Fin08cRepository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/fin-08c")
@CrossOrigin(origins = "*")
public class Fin08cController {

	@Autowired
	private Fin08cRepository fin08cRepository;
	
	@Autowired 
	private Fin02Service fin02Service;
	
	@GetMapping(path = "/all")
	public Iterable<Fin08c> getAllFin08c(){
		return fin08cRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin08c> getFin08cById(@PathVariable(name = "id")int id) {
		return fin08cRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public Fin08c addFin08c(@RequestBody Fin08c fin08c) {
		return fin08cRepository.save(fin08c);
	}
	
	@PutMapping(path = "/update")
	public Fin08c updateFin08c(@RequestBody Fin08c fin08c) {
		return fin08cRepository.save(fin08c);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin08c(@PathVariable(name="id")int id) {
		fin08cRepository.deleteById(id);
	}
	
	@PostMapping(path = "/create-fin08c")
	public Fin08c createFin08c(@RequestBody List<CimsHistoryFin08c> cimsHistoryFin08c){
		return fin02Service.createFin08c(cimsHistoryFin08c);
	}
	
	@PostMapping(path = "/create-fin08c-chf2/{userId}")
	public Fin08c createFin08cUsingChf2(@RequestBody List<CimsHistoryFin02> cimsHistoryFin02,@PathVariable(name = "userId") int userId){
		return fin02Service.createFin08cUsingChf2(cimsHistoryFin02,userId);
	}
	
	@GetMapping(path = "/find-by-fin08c-id/{id}")
	public Fin08c getFin08cByFin08cId(@PathVariable(name = "id")Integer id) {
		return fin02Service.getFin08c(id);
	}
	
	@PutMapping(path = "/update-status")
	public Fin08c updateFin08cStatus(@RequestBody Fin08c fin08c) {
		return fin02Service.updateFin08cStatus(fin08c);
	}
	
	@PutMapping(path = "/update-status-chf2")
	public Fin08c updateFin08cStatusUsingChf2(@RequestBody Fin08c fin08c) {
		return fin02Service.updateFin08cStatusUsingChf2(fin08c);
	}
	
	@GetMapping(path = "!status")
	public List<Fin08c> getFin08cByStatusNot(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08cInProgressListUsingChf2(status,stateId,districtId);
	}
	
	@GetMapping(path = "!status-older")
	public List<Fin08c> getFin08cByStatusNotOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08cInProgressListUsingChf2Older(status,stateId,districtId);
	}
	
	@GetMapping(path = "status")
	public List<Fin08c> getFin08cByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08cApprovedListUsingChf2(status,stateId,districtId);
	}
	
	@GetMapping(path = "status-older")
	public List<Fin08c> getFin08cByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08cApprovedListUsingChf2Older(status,stateId,districtId);
	}
	
	@GetMapping(path = "clinic-id/{clinicId}")
	public Fin08c getFin08bByClinicIdAndMonthYear(@PathVariable(name = "clinicId")int clinicId) {
		return fin02Service.findFin08cByClinicIdMonthYear(clinicId);
	}
}
