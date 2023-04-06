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

import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.repository.Fin03aRepository;
import com.example.demo.service.Fin02aService;

@RestController
@RequestMapping(path="/fin-03a")
@CrossOrigin(origins = "*")
public class Fin03aController {
	
	@Autowired
	private Fin03aRepository fin03aRepository;
	
	@Autowired
	private Fin02aService fin02aService;
	
	
	@GetMapping(path = "/all")
	public Iterable<Fin03a> getAllFin03a() {
		return fin03aRepository.findAllOrderByIdDesc();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin03a> getFin03aById(@PathVariable(name = "id") int id) {
		return fin03aRepository.findById(id);
	}
	
	@GetMapping(path = "/!status")
	public List<Fin03a> getFin03aInProgressList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin03aInProgressList(status, stateId, districtId);
	}
	
	@GetMapping(path = "/!status-older")
	public List<Fin03a> getFin03aInProgressListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin03aInProgressListOlder(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status")
	public List<Fin03a> getFin03aApprovedList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin03aApprovedList(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status-older")
	public List<Fin03a> getFin03aApprovedListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin03aApprovedListOlder(status, stateId, districtId);
	}
	
	@PostMapping(path = "/add")
	public Fin03a addFin03a(@RequestBody Fin03a Fin03a) {
		return fin03aRepository.save(Fin03a);
	}
	
	@PostMapping(path = "/create-fin03a/{userId},{backLog}")
	public Fin03a createFin03a(@RequestBody List<Fin07> Fin07,@PathVariable(name = "userId") int userId, @PathVariable(name = "backLog")  int backLog) {
		return fin02aService.addFin03a(Fin07,userId,backLog);
	}
	
	@GetMapping(path = "/districtAndClinicId/{districtId},{clinicTypeId},{month},{year}")
	public List<Fin03a> findByDistrictIdClinicTypeIdMonthYear(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin03aRepository.findByDistrictIdClinicTypeIdMonthYear(districtId, clinicTypeId, month , year);
	}
	
	@GetMapping(path = "/find-by-fin03-id/{id}")
	public Fin03a getFin03aByFin03aId(@PathVariable(name = "id") int id) {
		return fin02aService.getFin03a(id);
	}

	@PutMapping(path = "/update")
	public Fin03a updateFin03a(@RequestBody Fin03a Fin03a) {
		return fin03aRepository.save(Fin03a);
	}
	
	@PutMapping(path = "/update-status")
	public Fin03a updateFin01Status(@RequestBody Fin03a Fin03a) {
		return fin02aService.updateFin03aStatus(Fin03a);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin03a(@PathVariable(name = "id") int id) {
		fin03aRepository.deleteById(id);
	}
	
	@GetMapping(path = "/districtid-clinicid/{districtId},{clinicTypeId}")
	public Iterable<Fin03a> getAllCimsHistoryFin03aByDistrictIdAndClinicId(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId) {
		return fin03aRepository.findByDistrictIdAndClinicTypeId(districtId, clinicTypeId);
	}

}

