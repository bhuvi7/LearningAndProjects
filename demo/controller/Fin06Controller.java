package com.example.demo.controller;

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

import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.Fin01;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.repository.Fin06Repository;
import com.example.demo.service.Fin01Service;

@RestController
@RequestMapping(path="/fin-06")
@CrossOrigin(origins = "*")
public class Fin06Controller {
	
	@Autowired
	private Fin06Repository fin06Repository;
	
	@Autowired
	private Fin01Service fin01Service;
	
	
	@GetMapping(path = "/all")
	public Iterable<Fin06> getAllFin06() {
		return fin06Repository.findAllOrderByIdDesc();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin06> getFin06ById(@PathVariable(name = "id") int id) {
		return fin06Repository.findById(id);
	}
	
	@GetMapping(path = "byDistrictId-clinicTypeId/{districtId},{clinicTypeId},{month},{year}")
	public Iterable<Fin06> getFin06byDistrictIdclinicTypeId(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId,
			@PathVariable(name = "month") String month , @PathVariable(name = "year") String year){
		return fin06Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYearAndFin01InvNoIsNullAndFin01InvStatusIsNull(districtId, clinicTypeId, month , year);
	}
	
//	@GetMapping(path = "byId/{id}")
//	public Optional<Fin06> getFin06ByIds(@PathVariable(name = "id") int id) {
//		return fin06Repository.findById(id);
//	}
	
	@PostMapping(path = "/add")
	public Fin06 addFin06(@RequestBody Fin06 Fin06) {
		return fin06Repository.save(Fin06);
	}

	@PutMapping(path = "/update")
	public Fin06 updateFin06(@RequestBody Fin06 Fin06) {
		return fin06Repository.save(Fin06);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin06(@PathVariable(name = "id") int id) {
		fin06Repository.deleteById(id);
	}
	
	@PutMapping(path = "/updateStatus")
	public Fin06 updateFin06Status(@RequestBody Fin06 Fin06) {
		return fin01Service.updateFin06Status(Fin06);
	}
	
	@GetMapping(path = "/is_fin06_created/{clinicId}")
	public Boolean getFin06ByClinicIdAndMonth(@PathVariable(name = "clinicId") int clinicId) {
		return fin01Service.isFin06Created(clinicId);
	}
	
	@GetMapping(path = "/!status")
	public Iterable<Fin06> getFin06ByNotStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin06InProgressList(status,stateId,districtId);
	}
	
	@GetMapping(path = "/!status-older")
	public Iterable<Fin06> getFin06ByNotStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin06InProgressListOlder(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status")
	public Iterable<Fin06> getFin06ByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin06ApprovedList(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status-older")
	public Iterable<Fin06> getFin06ByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin01Service.fin06ApprovedListOlder(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status1|2/{status1},{status2}")
	public Iterable<Fin06> getFin06ByOrStatus(@PathVariable(name = "status1") String status1,@PathVariable(name = "status2") String status2) {
		return fin06Repository.findByStatusOrStatus(status1, status2);
	}
	
	@GetMapping(path = "/districtAndClinicId/{districtId},{clinicTypeId},{month},{year}")
	public Iterable<Fin06> getAllCimsHistoryFin01ByClinicId(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin01Service.getFin06ForFin01Create(districtId, clinicTypeId, month , year);
	}
	
	
	

}

