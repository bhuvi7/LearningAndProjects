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

import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin08b;
import com.example.demo.repository.Fin07Repository;
import com.example.demo.service.Fin02aService;

@RestController
@RequestMapping(path="/fin-07")
@CrossOrigin(origins = "*")
public class Fin07Controller {
	
	@Autowired
	private Fin07Repository fin07Repository;
	
	@Autowired
	private Fin02aService fin02aService;
	
	
	@GetMapping(path = "/all")
	public Iterable<Fin07> getAllFin07() {
		return fin07Repository.findAllOrderByIdDesc();
	}
	
	@GetMapping(path = "/{id}")
	public Fin07 getFin07ById(@PathVariable(name = "id") int id) {
		return fin02aService.getFin07(id);
	}
	
	@PostMapping(path = "/add")
	public Fin07 addFin07(@RequestBody Fin07 Fin07) {
		return fin07Repository.save(Fin07);
	}

	@PutMapping(path = "/update")
	public Fin07 updateFin07(@RequestBody Fin07 Fin07) {
		return fin07Repository.save(Fin07);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin07(@PathVariable(name = "id") int id) {
		fin07Repository.deleteById(id);
	}
	
	@PutMapping(path = "/update-status")
	public Fin07 updateFin07Status(@RequestBody Fin07 Fin07) {
		return fin02aService.updateFin07Status(Fin07);
	}
	
	@GetMapping(path = "/!status")
	public List<Fin07> getFin07InProgressList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin07InProgressListUsingChf2(status,stateId,districtId);
	}
//	@GetMapping(path = "/!status")
//	public List<Fin08b> getFin08bByStatusNot(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
//			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
//		return fin02Service.getFin08bInProgressListUsingChf2(status,stateId,districtId);
//	}
	
	@GetMapping(path = "/!status-older")
	public List<Fin07> getFin07InProgressListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin07InProgressListOlderUsingChf2Older(status,stateId,districtId);
	}
	
	@GetMapping(path = "/status")
	public Iterable<Fin07> getFin07ApprovedList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
		return fin02aService.fin07ApprovedListUsingChf2(status,stateId,districtId);
	}
//	@GetMapping(path = "/status")
//	public List<Fin08b> getFin08bByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
//			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
//		return fin02Service.getFin08bApprovedListUsingChf2(status,stateId,districtId);
//	} 
	
	@GetMapping(path = "/status-older")
	public Iterable<Fin07> getFin07ApprovedListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status)  {
		return fin02aService.fin07ApprovedListOlder(status,stateId,districtId);
	}
//	@GetMapping(path = "/!status-older")
//	public List<Fin08b> getFin08bByStatusNotOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
//			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
//		return fin02Service.getFin08bInProgressListUsingChf2Older(status,stateId,districtId);
//	}
	
	@GetMapping(path = "/districtAndClinicId/{districtId},{clinicTypeId},{month},{year}")
	public Iterable<Fin07> getFin07ListForFin03Create(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin02aService.getFin07ForFin03Create(districtId, clinicTypeId, month , year);
	}
	

}

