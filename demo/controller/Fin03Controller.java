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

import com.example.demo.EntityModel.Fin03;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.repository.Fin03Repository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/fin-03")
@CrossOrigin(origins = "*")
public class Fin03Controller {
	
	@Autowired
	private Fin03Repository fin03Repository;
	
	@Autowired
	private Fin02Service fin02Service;
	
	
	@GetMapping(path = "/all")
	public Iterable<Fin03> getAllFin03() {
		return fin03Repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin03> getFin03ById(@PathVariable(name = "id") int id) {
		return fin03Repository.findById(id);
	}
	
	@GetMapping(path = "/!status")
	public Iterable<Fin03> getFin03ByStatusNot(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin03InProgressListUsingChf2(status, stateId, districtId);
	}
	
	@GetMapping(path = "/districtid-clinictypeid-duplications/{districtId},{clinicTypeId},{month},{year}")
	public List<Fin03> findByDistrictIdClinicTypeIdMonthYearDuplication(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin03Repository.findByDistrictIdClinicTypeIdMonthYearDuplication(districtId, clinicTypeId, month , year);
	}
	
	@GetMapping(path = "/!status-older")
	public Iterable<Fin03> getFin03ByStatusNotOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin03InProgressListUsingChf2Older(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status")
	public Iterable<Fin03> getFin03ByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin03ApprovedListUsingChf2(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status-older")
	public Iterable<Fin03> getFin03ByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin03ApprovedListUsingChf2Older(status, stateId, districtId);
	}
	
	@PostMapping(path = "/add")
	public Fin03 addFin03(@RequestBody Fin03 Fin03) {
		return fin03Repository.save(Fin03);
	}
	
	@PostMapping(path = "/create-fin03/{districtId},{clinicTypeId}")
	public Fin03 createFin03(@PathVariable(name="districtId")int districtId,@PathVariable(name="clinicTypeId")int clinicTypeId) {
		return fin02Service.createFin03(districtId,clinicTypeId);
	}
	
	@PostMapping(path = "/create-fin03-chf2/{userId}/{districtId},{clinicTypeId},{month},{year},{backLog}")
	public Fin03 createFin03UsingChf2(@PathVariable(name="districtId")int districtId,@PathVariable(name="clinicTypeId")int clinicTypeId,
			@PathVariable(name="month")String month,@PathVariable(name="year")String year,@PathVariable(name = "userId") int userId, @PathVariable(name = "backLog")  int backLog) {
		return fin02Service.createFin03UsingChf2(districtId,clinicTypeId,month,year,userId,backLog);
	}
	
	@GetMapping(path = "/districtid-clinictypeid-duplication/{districtId},{clinicTypeId},{month},{year}")
	public List<Fin03> findByDistrictIdClinicTypeIdMonthYear(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin03Repository.findByDistrictIdClinicTypeIdMonthYearForDuplication(districtId, clinicTypeId, month , year);
	}
	
	@GetMapping(path = "/find-by-fin03-id/{id}")
	public Fin03 getFin03ByFin03Id(@PathVariable(name = "id") int id) {
		return fin02Service.getFin03(id);
	}

	@PutMapping(path = "/update")
	public Fin03 updateFin03(@RequestBody Fin03 Fin03) {
		return fin03Repository.save(Fin03);
	}
	
	@PutMapping(path = "/update-status")
	public Fin03 updateFin03Status(@RequestBody Fin03 Fin03) {
		return fin02Service.updateFin03Status(Fin03);
	}
	
	@PutMapping(path = "/update-status-chf2")
	public Fin03 updateFin03StatusUsingChf2(@RequestBody Fin03 Fin03) {
		return fin02Service.updateFin03StatusUsingChf2(Fin03);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin03(@PathVariable(name = "id") int id) {
		fin03Repository.deleteById(id);
	}
	
	@GetMapping(path = "/districtid-clinictypeid/{districtId},{clinicTypeId}")
	public Iterable<Fin03> getAllFin03ByDistrictIdAndClinicId(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId) {
		return fin03Repository.findByDistrictIdAndClinicTypeId(districtId, clinicTypeId);
	}
	
	@GetMapping(path = "/fin02-create-list")
	public List<Fin03> getFin03ForFin02CreateList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId){
		return fin02Service.getFin02CreateList(stateId,districtId);
	}
	
	@GetMapping(path = "/fin02-create-list-older")
	public List<Fin03> getFin03ForFin02CreateListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId){
		return fin02Service.getFin02CreateListOlder(stateId,districtId);
	}

}

