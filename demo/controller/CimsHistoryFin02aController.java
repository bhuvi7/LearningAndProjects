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

import com.example.demo.EntityModel.CimsHistoryFin02;
import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.CimsHistoryFin02aRepository;
import com.example.demo.service.Fin02aService;

@RestController
@RequestMapping(path="/cims-histroy-fin-02a")
@CrossOrigin(origins = "*")
public class CimsHistoryFin02aController {
	
	@Autowired
	private CimsHistoryFin02aRepository cimsHistoryFin02aRepository;
	
	@Autowired
	private Fin02aService fin02aService;
	
	
	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin02a> getAllCimsHistoryFin02a() {
		return cimsHistoryFin02aRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin02a> getCimsHistoryFin02aById(@PathVariable(name = "id") int id) {
		return cimsHistoryFin02aRepository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin02a addCimsHistoryFin02a(@RequestBody CimsHistoryFin02a CimsHistoryFin02a) {
		return cimsHistoryFin02aRepository.save(CimsHistoryFin02a);
	}

	@PutMapping(path = "/update")
	public CimsHistoryFin02a updateCimsHistoryFin01(@RequestBody CimsHistoryFin02a CimsHistoryFin02a) {
		return cimsHistoryFin02aRepository.save(CimsHistoryFin02a);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin01(@PathVariable(name = "id") int id) {
		cimsHistoryFin02aRepository.deleteById(id);
	}
	
	
	@GetMapping(path = "/fin-07-pending-list/{districtId},{clinicTypeId},{month},{year}")
	public Iterable<CimsHistoryFin02a> getDataForFin07CreateListPendingClinics(@PathVariable(name = "districtId") Integer districtId,@PathVariable(name = "clinicTypeId") Integer clinicTypeId
			,@PathVariable(name = "month") String month , @PathVariable(name = "year") String year) {
		return fin02aService.getDataForFin07CreateListPendingClinics(districtId, clinicTypeId, month , year);
	}
	
	@GetMapping(path = "/fin-07-create-list")
	public Iterable<CimsHistoryFin02a> getFin07CreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin07CreateListUsingChf2(stateId, districtId);
	}
	
//	@GetMapping(path = "/fin-08b-create-list")
//	public List<CimsHistoryFin02> getFin08bCreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
//			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
//		return fin02Service.getFin08bCreateListUsingChf2(stateId,districtId);
//	}
	
	@GetMapping(path = "/fin-07-create-list-older")
	public Iterable<CimsHistoryFin02a> getFin07CreateListForOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin07CreateListForOlderUsingChf2Older(stateId, districtId);
	}
	
//	@GetMapping(path = "/fin-08b-create-list-older")
//	public List<CimsHistoryFin02> getFin08bCreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
//			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
//		return fin02Service.getFin08bCreateListUsingChf2Older(stateId,districtId);
//	}
	
	@GetMapping(path = "/fin-03a-create-list")
	public Iterable<Fin07> getFin03aCreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin03aCreateList(stateId, districtId);
	}

	
	@GetMapping(path = "/fin-03a-create-list-older")
	public Iterable<Fin07> getFin03aCreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin03aCreateListOlder(stateId, districtId);
	}
	
	@GetMapping(path = "/fin-02a-create-list")
	public Iterable<Fin03a> getFin02aCreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin02aCreateList(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-02a-create-list-older")
	public Iterable<Fin03a> getFin02aCreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02aService.getFin02aCreateListOlder(stateId,districtId);
	}

	@GetMapping(path = "/clinic-id/{clinicId},{month},{year}")
	public Iterable<CimsHistoryFin02a> getCimsHistoryFin02aForFin07CreateByClinicId(@PathVariable(name = "clinicId") int clinicId, @PathVariable(name = "month") String month,@PathVariable(name = "year") String year) {
		return fin02aService.getCimsHistoryFin02aForFin07Create(clinicId, month ,year);
	}
	
	@PostMapping(path = "/fin-07-create/{userId}")
	public Fin07 createFin07(@RequestBody List<CimsHistoryFin02a> CimsHistoryFin02aList,@PathVariable(name = "userId") int userId) {
		return fin02aService.addFin07(CimsHistoryFin02aList,userId);
	}
	
	@GetMapping(path = "/invoice-generation-fin07-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07Initiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin07-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07Inprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin07-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07Approved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin03a-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aInitiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aInitiate(month,year,stateId,districtId);
	}
	
	
	@GetMapping(path = "/invoice-generation-fin03a-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aInprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin03a-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aApproved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02a-inv-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvInitiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvInitiate(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02a-inv-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvInprogress(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02a-inv-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvApproved(month,year,stateId,districtId);
	}
	
	
	
	@GetMapping(path = "/invoice-generation-fin07-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07InitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin07-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07InprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin07-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin07ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin07ApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin03a-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aInitiateOlder(month,year,stateId,districtId);
	}
	
	
	@GetMapping(path = "/invoice-generation-fin03a-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aInprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin03a-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin03aApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin03aApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02a-inv-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvInitiateOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02a-inv-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvInprogressOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin02a-inv-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoicefin02InvApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02aService.getInvoiceFin02InvApprovedOlder(month,year,stateId,districtId);
	}

}
