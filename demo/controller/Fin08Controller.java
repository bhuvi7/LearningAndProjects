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

import com.example.demo.EntityModel.Fin08;
import com.example.demo.EntityModel.Fin08b;
import com.example.demo.EntityModel.Fin08c;
import com.example.demo.repository.Fin08Repository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/fin-08")
@CrossOrigin(origins = "*")
public class Fin08Controller {

	@Autowired
	private Fin08Repository fin08Repository;
	
	@Autowired 
	private Fin02Service fin02Service;
	
	@GetMapping(path = "/all")
	public Iterable<Fin08> getAllFin08(){
		return fin08Repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin08> getFin08ById(@PathVariable(name = "id")int id) {
		return fin08Repository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public Fin08 addFin08(@RequestBody Fin08 fin08) {
		return fin08Repository.save(fin08);
	}
	
	@PutMapping(path = "/update")
	public Fin08 updateFin08(@RequestBody Fin08 fin08) {
		return fin08Repository.save(fin08);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin08(@PathVariable(name="id")int id) {
		fin08Repository.deleteById(id);
	}
	
	@PostMapping(path = "/create-fin08/{clinicId}")
	public Fin08 createFin08(@PathVariable(name = "clinicId") int clinicId ){
		return fin02Service.createFin08(clinicId);
	}
	
	@PostMapping(path = "/create-fin08-chf2/{clinicId},{month},{year}/{userId}")
	public Fin08 createFin08UsingChf2(@PathVariable(name = "clinicId") int clinicId ,@PathVariable(name = "month") String month ,
			@PathVariable(name = "year") String year ,@PathVariable(name = "userId") int userId){
		return fin02Service.createFin08UsingChf2(clinicId,month,year,userId);
	}
	
	@PutMapping(path = "/update-status")
	public Fin08 updateFin08Status(@RequestBody Fin08 fin08) {
		return fin02Service.updateFin08Status(fin08);
	}
	
	@PutMapping(path = "/update-status-chf2")
	public Fin08 updateFin08StatusUsingChf2(@RequestBody Fin08 fin08) {
		return fin02Service.updateFin08StatusUsingChf2(fin08);
	}
	
	/*temporary fix for updating status */
	@GetMapping(path = "/fin-08-ref-no-update-in-cims-history-fin-02")
	public void updateFin08RefNoInCimsHistoryFin02() {
		fin02Service.updateFin08RefNoInCimsHistoryFin02();
	}
	
	@GetMapping(path = "/!status")
	public List<Fin08> getFin08ByStatusNot(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08InProgressListUsingChf2(status, stateId, districtId);
	}
	
	@GetMapping(path = "/!status-older")
	public List<Fin08> getFin08ByStatusNotOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08InProgressListUsingChf2Older(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status")
	public List<Fin08> getFin08ByStatus(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08ApprovedListUsingChf2(status, stateId, districtId);
	}
	
	@GetMapping(path = "/status-older")
	public List<Fin08> getFin08ByStatusOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status){
		return fin02Service.getFin08ApprovedListUsingChf2Older(status, stateId, districtId);
	}
	
	@GetMapping(path = "/districtid-clinictypeid/{districtId},{clinicTypeId},{month},{year}")
	public List<Fin08> getFin08ByDistrictIdAndClinicTypeId(@PathVariable(name = "districtId")int districtId, @PathVariable(name = "clinicTypeId")int clinicTypeId
			, @PathVariable(name = "month")String month, @PathVariable(name = "year")String year){
		return fin02Service.getFin08ForFin03Create(districtId, clinicTypeId, month, year);
	}
	
	@GetMapping(path = "/fin-03-create-list")
	public List<Fin08> getFin08ForFin03CreateList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId){
		return fin02Service.getFin03CreateList(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-03-create-list-older")
	public List<Fin08> getFin08ForFin03CreateListOlder(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
			@RequestParam(name = "districtId") Integer districtId){
		return fin02Service.getFin03CreateListOlder(stateId,districtId);
	}
	
}
