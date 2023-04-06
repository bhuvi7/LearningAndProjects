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
//Test

import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.Fin01;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.CimsHistoryFin01Repository;
import com.example.demo.service.Fin01Service;

@RestController
@RequestMapping(path="/cims-histroy-fin-01")
@CrossOrigin(origins = "*")
public class CimsHistoryFin01Controller {
	
	@Autowired
	private CimsHistoryFin01Repository cimsHistoryFin01Repository;
	
	@Autowired
	private Fin01Service fin01Service;
	
	
	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin01> getAllCimsHistoryFin01() {
		return cimsHistoryFin01Repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin01> getCimsHistoryFin01ById(@PathVariable(name = "id") int id) {
		return cimsHistoryFin01Repository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin01 addCimsHistoryFin01(@RequestBody CimsHistoryFin01 CimsHistoryFin01) {
		return cimsHistoryFin01Repository.save(CimsHistoryFin01);
	}

	@PutMapping(path = "/update")
	public CimsHistoryFin01 updateCimsHistoryFin01(@RequestBody CimsHistoryFin01 CimsHistoryFin01) {
		return cimsHistoryFin01Repository.save(CimsHistoryFin01);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin01(@PathVariable(name = "id") int id) {
		cimsHistoryFin01Repository.deleteById(id);
	}
	
	@GetMapping(path = "/fin-06-create-list")
	public Iterable<CimsHistoryFin01> getFin06CreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFin06CreateList(stateId, districtId);
	}
	
	@GetMapping(path = "/fin-06-create-list-older")
	public Iterable<CimsHistoryFin01> getFin06CreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFin06CreateListForOlder(stateId, districtId);
		
	}
	
//	@GetMapping(path = "/fin-06-create-list")
//	public Iterable<CimsHistoryFin01> getFin06CreateList(
//			@RequestParam(value = "state-id", required = false) Integer stateId) {
//		return fin01Service.getFin06CreateList(stateId);
//	}
	
	
	
	
	@GetMapping(path = "/fin-01-create-list")
	public Iterable<Fin06> getFin01CreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFin01CreateList(stateId, districtId);
	}
	
	@GetMapping(path = "/fin-01-create-list-olders")
	public Iterable<Fin06> getFin01CreateListOlders(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFin01CreateListOlders(stateId, districtId);
	}
	
	
	@GetMapping(path = "/fin-01-create-list-older/{stateId},{districtId}")
	public Iterable<CimsHistoryFin01> getFin01CreateListForOlder(@PathVariable(name = "stateId") int stateId, @PathVariable(name = "districtId") int districtId) {
		return fin01Service.getFin06CreateListForOlder(stateId, districtId);
	}
	
	@GetMapping(path = "/FIN-01-create-list")
	public Iterable<Fin06> getFIN01CreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFIN01CreateList(stateId,districtId);
	}
	
	@GetMapping(path = "/FIN-01-create-list-older")
	public Iterable<Fin06> getFIN01CreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin01Service.getFIN01CreateListOlder(stateId,districtId);
	}

	@GetMapping(path = "/clinic_id-is_fin06_created/{clinicId},{month},{year},{isFin06Created}")
	public Iterable<CimsHistoryFin01> getAllCimsHistoryFin01ByClinicIdAndStatus(@PathVariable(name = "clinicId") int clinicId,@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "isFin06Created") String isFin06Created) {
		return cimsHistoryFin01Repository.findByClinicIdAndIsFin06Created(clinicId, month ,year,isFin06Created);
	}
	
	
	@PostMapping(path = "/fin-06-create/{userId}")
	public Fin06 createFin06(@RequestBody List<CimsHistoryFin01> CimsHistoryFin01List,@PathVariable(name = "userId") int userId) {
		return fin01Service.addFin06(CimsHistoryFin01List,userId);
	}
	@GetMapping(path = "/invoice-generation-fin06-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06Initiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin06-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06Inprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin06-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06Approved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin01-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01Initiate(month,year,stateId,districtId);
	}
	
	
	@GetMapping(path = "/invoice-generation-fin01-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01Inprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin01-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01Approved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin01-inv-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvInitiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvInitiate(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin01-inv-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvInprogress(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin01-inv-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvApproved(month,year,stateId,districtId);
	}
	
	
	
	@GetMapping(path = "/invoice-generation-fin06-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06InitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin06-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06InprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin06-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin06ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin06ApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin01-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InitiateOlder(month,year,stateId,districtId);
	}
	
	
	@GetMapping(path = "/invoice-generation-fin01-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin01-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01ApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin01-inv-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvInitiateOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin01-inv-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvInprogressOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin01-inv-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin01InvApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin01Service.getInvoiceFin01InvApprovedOlder(month,year,stateId,districtId);
	}
	
	
	
	
	
}
