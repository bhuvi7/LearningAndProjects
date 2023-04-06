package com.example.demo.controller;

import java.time.LocalDate;
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

import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin10b;
import com.example.demo.repository.Fin10bRepository;
import com.example.demo.service.Fin04Service;

@RestController
@RequestMapping(path="/fin-10b")
@CrossOrigin(origins = "*")
public class Fin10bController {
	
	@CreationTimestamp
	private LocalDate createdDate = LocalDate.now();
	
	@Autowired
	Fin10bRepository fin10bRepository;
	
	@Autowired
	Fin04Service fin04Service;
	
	@GetMapping(path = "/all")
	public Iterable<Fin10b> getAllFin10b() {
		return fin10bRepository.findAllOrderByIdDesc();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin10b> getFin10bById(@PathVariable(name = "id") int id) {
		return fin10bRepository.findById(id);
	}
	
	@GetMapping(path = "/fin-04-create-list")
	public List<Fin10b> getFin10bForFin04CreateList() {
		return fin04Service.getFin04CreateList();
	}
	
	@GetMapping(path = "/districtid-clinictypeid/{clinictypeid},{districtid},{month},{year}")
	public List<Fin10b> getFin10bForFin04Create(@PathVariable(name="clinictypeid") int clinicTypeId ,@PathVariable(name = "districtid") int districtId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin10bRepository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(districtId, clinicTypeId, month , year);
	}
	
	@PostMapping(path = "/add")
	public Fin10b addFin10b(@RequestBody Fin10b Fin10b) {
		return fin10bRepository.save(Fin10b);
	}
	
	@PostMapping(path = "/create-fin10b")
	public Fin10b createFin10b(@RequestBody Fin10b Fin10b) {
		return fin04Service.createFin10b(Fin10b);
	}

	@PutMapping(path = "/update")
	public Fin10b updateFin10b(@RequestBody Fin10b Fin10b) {
		return fin10bRepository.save(Fin10b);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin10b(@PathVariable(name = "id") int id) {
		fin10bRepository.deleteById(id);
	}

	@GetMapping(path = "/!status")
	public List<Fin10b> getFin10bInProgressList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin04Service.fin10bInProgressListUsingChf2(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status-fin10b")
	public List<Fin10b> getFin10bApprovedList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin04Service.fin10bApprovedListUsingChf2(status,stateId,districtId);
	}
	
}
