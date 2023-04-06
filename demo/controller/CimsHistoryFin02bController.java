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
//Test

import com.example.demo.EntityModel.CimsHistoryFin02b;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.CimsHistoryFin02bRepository;
import com.example.demo.service.Fin02bService;

@RestController
@RequestMapping(path="/cims-history-fin-02b")
@CrossOrigin(origins = "*")
public class CimsHistoryFin02bController {
	
	@Autowired
	private CimsHistoryFin02bRepository cimsHistoryFin02bRepository;
	
	@Autowired
	private Fin02bService fin02bService;
	
	
	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin02b> getAllCimsHistoryFin02b() {
		return cimsHistoryFin02bRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin02b> getCimsHistoryFin02bById(@PathVariable(name = "id") int id) {
		return cimsHistoryFin02bRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin02b addCimsHistoryFin02b(@RequestBody CimsHistoryFin02b CimsHistoryFin02b) {
		return cimsHistoryFin02bRepository.save(CimsHistoryFin02b);
	}

	@PutMapping(path = "/update")
	public CimsHistoryFin02b updateCimsHistoryFin02b(@RequestBody CimsHistoryFin02b CimsHistoryFin02b) {
		return cimsHistoryFin02bRepository.save(CimsHistoryFin02b);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin02b(@PathVariable(name = "id") int id) {
		cimsHistoryFin02bRepository.deleteById(id);
	}
	
	@GetMapping(path = "/fin-02b-create-list")
	public Iterable<CimsHistoryFin02b> getFin02bCreateList(	@RequestParam(name = "stateId") int stateId, @RequestParam(name = "districtId") int districtId,@RequestParam(value = "circleId") Integer circleId) {
		return fin02bService.getFin02bCreateList(stateId, districtId);
	}
	
	@GetMapping(path = "/fin-02b-create-list-older")
	public Iterable<CimsHistoryFin02b> getFin02bCreateListOlder(@RequestParam(name = "stateId") int stateId, @RequestParam(name = "districtId") int districtId) {
		return fin02bService.getFin02bCreateListOlder(stateId, districtId);
	}

	@GetMapping(path = "/districtid-clinictypeid/{districtId},{clinicTypeId},{month},{year}")
	public CimsHistoryFin02b getAllCimsHistoryFin02bByDistrictIdAndClinicId(@PathVariable(name = "districtId") int districtId,@PathVariable(name = "clinicTypeId") int clinicTypeId,
			@PathVariable(name = "month") String month,@PathVariable(name = "year") String year) {
		return cimsHistoryFin02bRepository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(districtId,clinicTypeId,String.valueOf(month),String.valueOf(year));
	}
	
	@GetMapping(path = "/invoice-generation-fin02b-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvInitiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvInitiate(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02b-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvInprogress(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02b-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvApproved(month,year,stateId,districtId);
	}
	
	
	@GetMapping(path = "/invoice-generation-fin02b-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvInitiateOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02b-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvInprogressOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02b-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02bInvApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02bService.getInvoiceFin02bInvApprovedOlder(month,year,stateId,districtId);
	}
	
	
	
}
