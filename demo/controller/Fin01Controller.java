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

import com.example.demo.EntityModel.Fin01;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.repository.Fin01Repository;
import com.example.demo.service.Fin01Service;

@RestController
@RequestMapping(path="/fin-01")
@CrossOrigin(origins = "*")
public class Fin01Controller {
	
	@Autowired
	private Fin01Repository fin01Repository;
	
	@Autowired
	private Fin01Service fin01Service;
	
	
	@GetMapping(path = "/all")
	public Iterable<Fin01> getAllFin01() {
		return fin01Repository.findAllOrderByIdDesc();
	}
	@GetMapping(path = "/{id}")
	public Optional<Fin01> getAllFin01ById(@PathVariable(name = "id") int id) {
		return fin01Repository.findById(id);
	}
	
	
	
	@GetMapping(path = "/!status")
	public List<Fin01> getFin01InProgressList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin01InProgressList(status, stateId, districtId);
	}
	
	@GetMapping(path = "/!status-older")
	public List<Fin01> getFin01InProgressListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin01InProgressListOlder(status, stateId, districtId);
	}
	
	
	@GetMapping(path = "/status")
	public Iterable<Fin01> getFin01ByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin01ApprovedList(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status-older")
	public Iterable<Fin01> getFin01ByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin01ApprovedListOlder(status, stateId, districtId);
	}	
	
	
	@PostMapping(path = "/add")
	public Fin01 addFin01(@RequestBody Fin01 Fin01) {
		return fin01Repository.save(Fin01);
	}
	
	@PostMapping(path = "/create-fin01/{userId}")
	public Fin01 createFin01(@RequestBody List<Fin06> Fin06,@PathVariable(name = "userId") int userId) {
		return fin01Service.addFin01(Fin06,userId);
	}

	@PutMapping(path = "/update")
	public Fin01 updateFin01(@RequestBody Fin01 Fin01) {
		return fin01Repository.save(Fin01);
	}
	
	@PutMapping(path = "/update-status")
	public Fin01 updateFin01Status(@RequestBody Fin01 Fin01) {
		return fin01Service.updateFin01Status(Fin01);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin01(@PathVariable(name = "id") int id) {
		fin01Repository.deleteById(id);
	}
	
	

}

