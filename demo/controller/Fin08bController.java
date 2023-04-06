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
import com.example.demo.EntityModel.CimsHistoryFin08b;
import com.example.demo.EntityModel.Fin08b;
import com.example.demo.EntityModel.Fin08c;
import com.example.demo.repository.Fin08bRepository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/fin-08b")
@CrossOrigin(origins = "*")
public class Fin08bController {

	@Autowired
	private Fin08bRepository fin08bRepository;
	
	@Autowired 
	private Fin02Service fin02Service;
	
	@GetMapping(path = "/all")
	public Iterable<Fin08b> getAllFin08b(){
		return fin08bRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin08b> getFin08bById(@PathVariable(name = "id")int id) {
		return fin08bRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public Fin08b addFin08b(@RequestBody Fin08b fin08b) {
		return fin08bRepository.save(fin08b);
	}
	
	@PutMapping(path = "/update")
	public Fin08b updateFin08b(@RequestBody Fin08b fin08b) {
		return fin08bRepository.save(fin08b);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin08b(@PathVariable(name="id")int id) {
		fin08bRepository.deleteById(id);
	}
	
	@PostMapping(path = "/create-fin08b")
	public Fin08b createFin08b(@RequestBody List<CimsHistoryFin08b> cimsHistoryFin08b){
		return fin02Service.createFin08b(cimsHistoryFin08b);
	}
	
	@PostMapping(path = "/create-fin08b-chf2/{userId}")
	public Fin08b createFin08bUsingChf2(@RequestBody List<CimsHistoryFin02> cimsHistoryFin02,@PathVariable(name = "userId") int userId){
		return fin02Service.createFin08bUsingChf2(cimsHistoryFin02,userId);
	}
	
	@GetMapping(path = "/find-by-fin08b-id/{id}")
	public Fin08b getFin08bByFin08bId(@PathVariable(name = "id")int id) {
		return fin02Service.getFin08b(id);
	}
	
	@PutMapping(path = "/update-status")
	public Fin08b updateFin08bStatus(@RequestBody Fin08b fin08b) {
		return fin02Service.updateFin08bStatus(fin08b);
	}
	
	@PutMapping(path = "/update-status-chf2")
	public Fin08b updateFin08bStatusUsingChf2(@RequestBody Fin08b fin08b) {
		return fin02Service.updateFin08bStatusUsingChf2(fin08b);
	}
	
	@GetMapping(path = "/!status")
	public List<Fin08b> getFin08bByStatusNot(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08bInProgressListUsingChf2(status,stateId,districtId);
	}
	
	@GetMapping(path = "/!status-older")
	public List<Fin08b> getFin08bByStatusNotOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08bInProgressListUsingChf2Older(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status")
	public List<Fin08b> getFin08bByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08bApprovedListUsingChf2(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status-older")
	public List<Fin08b> getFin08bByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08bApprovedListUsingChf2Older(status,stateId,districtId);
	}
	
	@GetMapping(path = "/fin-08-create-list")
	public List<Fin08c> getForFin08CreateList(){
		return fin02Service.getFin08CreateList();
	}
	
	@GetMapping(path = "clinic-id/{clinicId}")
	public Fin08b getFin08bByClinicIdAndMonthYear(@PathVariable(name = "clinicId")int clinicId) {
		return fin02Service.findFin08bByClinicIdMonthYear(clinicId);
	}
}
