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

import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin08;
import com.example.demo.EntityModel.Fin09;
import com.example.demo.EntityModel.Fin09Clinic;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.repository.Fin09Repository;
import com.example.demo.service.Fin09Service;

@RestController
@RequestMapping(path="/fin-09")
@CrossOrigin(origins = "*")
public class Fin09Controller {

	@Autowired
	private Fin09Repository fin09Repository;
	
	@Autowired 
	private Fin09Service fin09Service;
	
	@GetMapping(path = "/all")
	public Iterable<Fin09> getAllFin09(){
		return fin09Repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Fin09> getFin09ById(@PathVariable(name = "id")int id) {
		return fin09Repository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public Fin09 addFin09(@RequestBody Fin09 fin09) {
		return fin09Repository.save(fin09);
	}
	
	@PutMapping(path = "/update")
	public Fin09 updateFin09(@RequestBody Fin09 fin09) {
		return fin09Repository.save(fin09);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin09(@PathVariable(name="id")int id) {
		fin09Repository.deleteById(id);
	}
	
	@PostMapping(path = "/create-fin09")
	public Fin09 createFin09(@RequestBody List<Fin09Clinic> fin09Clinic ){
		return fin09Service.createFin09(fin09Clinic);
	}
	
	@PutMapping(path = "/update-status")
	public Fin09 updateFin09Status(@RequestBody Fin09 fin09) {
		return fin09Service.updateFin09Status(fin09);
	}
	
	@GetMapping(path = "/!status/{status}")
	public List<Fin09> getFin09ByStatusNot(@PathVariable(name = "status")String status){
		return fin09Repository.findByStatusNot(status);
	}
	
	@GetMapping(path="/filter/!status")
	public Iterable<Fin09> getFilteredInvoiceForFin09Adjustment(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
			@RequestParam(value = "approvalQuater", required = false) String approvalQuater,
			@RequestParam(value = "approvalYear", required = false) String approvalYear
			)  {
		
		return fin09Repository.findByFin09Status(stateName, districtName,clinicTypeId,approvalQuater,approvalYear);
	}
	
	@GetMapping(path="/filter/all/Fin09")
	public Iterable<Fin09> getFilteredInvoiceForFin09List(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
			@RequestParam(value = "approvalQuater", required = false) String approvalQuater,
			@RequestParam(value = "approvalYear", required = false) String approvalYear
			)  {
		
		return fin09Repository.findByFin09(stateName, districtName,clinicTypeId,approvalQuater,approvalYear);
	}
	
	@GetMapping(path = "/!status-clinic/{status}/{clinicTypeId}") 
	public Iterable<Fin09> findByStatusNotClinic(
			@RequestParam(name = "status", required = false)String status,
			@RequestParam(name="clinicTypeId", required = false)Integer clinicTypeId){
		return fin09Repository.findByStatusNotClinic(status,clinicTypeId);
	}
	
	
	@GetMapping(path = "/status/{status}")
	public List<Fin09> getFin09ByStatus(@PathVariable(name = "status")String status){
		return fin09Repository.findByStatus(status);
	}
	
	@GetMapping(path = "/fin-03-create/{districtId},{clinicTypeId}")
	public Fin09 getFin09ForFin03Create(@PathVariable(name = "districtId")int districtId,@PathVariable(name="clinicTypeId")int clinicTypeId){
		return fin09Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNull(districtId, clinicTypeId);
	}
	

	
	@GetMapping(path = "/unadjusted-penality-all/{status}")
	public List<Fin09> getUnadjustedPenality(@PathVariable(name = "status") String status) {
		return fin09Service.getAllUnadjustedPenalities(status);
	}
	
	
	@GetMapping(path = "/unadjusted-penality-state-name/{stateName}/{status}")
	public Iterable<Fin09> getAllUnadjustedPenalityByStateName(@PathVariable(name = "stateName") String stateName,@PathVariable(name = "status") String status) {		         
		return fin09Service.getAllUnadjustedPenalityByStateName(stateName,status);
	}
	
	@GetMapping(path = "/unadjusted-penality-circle-code/{circleCode}/{status}")
	public Iterable<Fin09> getAllUnadjustedPenalityByCircleCode(@PathVariable(name = "circleCode") String circleCode,@PathVariable(name = "status") String status) {		         
		return fin09Service.getAllUnadjustedPenalityByCircleCode(circleCode,status);
	}
	
	@GetMapping(path = "/unadjusted-penality-district-name/{stateName}/{circleCode}/{districtName}/{status}")
	public Iterable<Fin09> getAllUnadjustedPenalityByDistrictName(@PathVariable(name = "stateName") String stateName,@PathVariable(name = "circleCode") String circleCode,@PathVariable(name = "districtName") String districtName,@PathVariable(name = "status") String status) {		         
		return fin09Service.getAllUnadjustedPenalityByDistrictName(districtName,status);
	}
	
	@GetMapping(path = "/Unadjusted-Penality-ByQuaterQuater/{key}/{value}/{status}")
	public Iterable<Fin09> getAllUnadjustedPenalityByQuaterQuater(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value,@PathVariable(name = "status") String status) {		         
		return fin09Service.getAllUnadjustedPenalityByQuaterQuater(key,value,status);
	}
	
	@GetMapping(path = "/Unadjusted-Penality-ByQuater-Month/{key}/{value}/{quater}/{year}/{status}")
	public Iterable<Fin09> getAllUnadjustedPenalityByQuaterMonth(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value,@PathVariable(name = "quater") String quater,@PathVariable(name = "year") String year,@PathVariable(name = "status") String status) {		         
		return fin09Service.getAllUnadjustedPenalityByQuaterMonth(key,value,quater,year,status);
	}
	
	@GetMapping(path = "/findBy-state-district-!status/{stateName}/{districtName}/{status}")
	public List<Fin09> findByStateAndDistrictAndStatusNot(@PathVariable(name = "stateName")String stateName,@PathVariable(name = "districtName")String districtName,@PathVariable(name = "status")String status){
		return fin09Repository.findByStateAndDistrictAndStatusNot(stateName,districtName,status);
	}	
	
	@GetMapping(path = "/findBy-state-!status/{stateName}/{status}")
	public List<Fin09> findByStateAndStatusNot(@PathVariable(name = "stateName")String stateName,@PathVariable(name = "status")String status){
		return fin09Repository.findByStateAndStatusNot(stateName,status);
	}
	
	@GetMapping(path = "/findBy-state-district-clinicType-!status/{stateName}/{districtName}/{clinicTypeId}/{status}")
	public List<Fin09> findByStateAndDistrictAndStatusNot(@PathVariable(name = "stateName")String stateName,@PathVariable(name = "districtName")String districtName,@PathVariable(name = "clinicTypeId")Integer clinicTypeId,@PathVariable(name = "status")String status){
		return fin09Repository.findByStateAndDistrictAndClinicTypeAndStatusNot(stateName,districtName,clinicTypeId, status);
	}
	
	/*
	 * @GetMapping(path = "/findBy-id/{id}") public List<Fin09>
	 * findById(@PathVariable(name = "id")String id){ return
	 * fin09Repository.findByStateId(id); }
	 */
	
	
}

