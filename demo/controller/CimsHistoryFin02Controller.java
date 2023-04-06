package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.EntityModel.CammsBO;
import com.example.demo.EntityModel.CimsHistoryFin02;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.CammsBORepository;
import com.example.demo.repository.CimsHistoryFin02Repository;
import com.example.demo.service.Fin02Service;

@RestController
@RequestMapping(path="/cims-history-fin-02")
@CrossOrigin(origins = "*")
public class CimsHistoryFin02Controller {
	
	@Autowired
	private CimsHistoryFin02Repository cimsHistoryFin02Repository;
	
	@Autowired
	private CammsBORepository cammsBORepository;
	
	@Autowired
	private Fin02Service fin02Service;
	
	
	@GetMapping(path = "/all")
	public Iterable<CimsHistoryFin02> getAllCimsHistoryFin02a() {
		return cimsHistoryFin02Repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<CimsHistoryFin02> getCimsHistoryFin02aById(@PathVariable(name = "id") int id) {
		return cimsHistoryFin02Repository.findById(id);
	}
	
	@PostMapping(path = "/add")
	public CimsHistoryFin02 addCimsHistoryFin02a(@RequestBody CimsHistoryFin02 CimsHistoryFin02) {
		return cimsHistoryFin02Repository.save(CimsHistoryFin02);
	}

	@PutMapping(path = "/update")
	public CimsHistoryFin02 updateCimsHistoryFin02(@RequestBody CimsHistoryFin02 CimsHistoryFin02) {
		return cimsHistoryFin02Repository.save(CimsHistoryFin02);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCimsHistoryFin02(@PathVariable(name = "id") int id) {
		cimsHistoryFin02Repository.deleteById(id);
	}
	
	@GetMapping(path = "/fin-08b-create-list")
	public List<CimsHistoryFin02> getFin08bCreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08bCreateListUsingChf2(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-08b-create-list-older")
	public List<CimsHistoryFin02> getFin08bCreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08bCreateListUsingChf2Older(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-08c-create-list")
	public List<CimsHistoryFin02> getFin08cCreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08cCreateListUsingChf2(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-08c-create-list-older")
	public List<CimsHistoryFin02> getFin08cCreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08cCreateListUsingChf2Older(stateId,districtId);
	}
	
	@GetMapping(path = "/fin-08-create-list")
	public List<CimsHistoryFin02> getFin08CreateList(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08CreateListUsingChf2(stateId, districtId);
	}
	
	@GetMapping(path = "/fin-03-create-list-pending-list-notification/{districtId}")
	public List<CimsHistoryFin02> getFin08CreateList(@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getFin08CreateLisPendingListNotificaitontUsingChf2(districtId);
	}
	
	@GetMapping(path = "/fin-08-create-list-older")
	public List<CimsHistoryFin02> getFin08CreateListOlder(@RequestParam(value = "stateId", required = false) Integer stateId,@RequestParam(value = "circleId", required = false) Integer circleId,
			@RequestParam(name = "districtId", required = false) Integer districtId,@RequestParam(name = "clinicTypeId", required = false) Integer clinicTypeId) {
		return fin02Service.getFin08CreateListUsingChf2Older(stateId, districtId);
	}

	@GetMapping(path = "/clinic-id/{clinicId}")
	public Iterable<CimsHistoryFin02> getAllCimsHistoryFin02ByClinicId(@PathVariable(name = "clinicId") int clinicId) {
		return cimsHistoryFin02Repository.findByClinicId(clinicId);
	}
	
	@GetMapping(path = "/clinicid-fin08b/{clinicId},{month},{year}")
	public Iterable<CimsHistoryFin02> getAllCimsHistoryFin02ByClinicIdAndIsFin08b(@PathVariable(name = "clinicId") int clinicId,@PathVariable(name = "month") String month,@PathVariable(name = "year") String year) {
		return fin02Service.getEquipmentsForFin08bCreate(clinicId,month,year);
	}
	
	@GetMapping(path = "/clinicid-fin08c/{clinicId},{month},{year}")
	public Iterable<CimsHistoryFin02> getAllCimsHistoryFin02ByClinicIdAndIsFin08c(@PathVariable(name = "clinicId") int clinicId,@PathVariable(name = "month") String month,@PathVariable(name = "year") String year) {
		return fin02Service.getEquipmentsForFin08cCreate(clinicId,month,year);
	}
	
	@GetMapping(path = "/data-deletion")
	public List<CimsHistoryFin02> getCimsHistoryFin02NotCreated() {
		System.out.println(cammsBORepository.findByCammsBO().size());
		List<CammsBO> cammsBoList = cammsBORepository.findByCammsBO();
		List<CimsHistoryFin02> cimsFin02List = new ArrayList<CimsHistoryFin02>();
		cammsBoList.forEach(cammsBo -> {
			CimsHistoryFin02 cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cStatusIsNullAndIsFin08cAndBeNumber("Y", cammsBo.getBENumber());
			if(cimsHistoryFin02 != null) {
			cimsFin02List.add(cimsHistoryFin02);
			cimsHistoryFin02Repository.deleteById(cimsHistoryFin02.getId());
			}
		});
		System.out.println(cimsFin02List.size());
		return null;
	}
	
	@GetMapping(path = "/invoice-generation-fin08b-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bInitiate(@PathVariable(name = "month") String month ,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bInitiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08b-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bInprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08b-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bApproved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08c-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cInitiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cInitiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08c-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cInprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cInprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08c-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cApproved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApproved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08Initiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08Inprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApproved(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin03-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin03Initiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin03-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin03Inprogress(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin03-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApproved(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-initiate/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02Initiate(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin02Initiate(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-inprogress/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02Inprogress(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin02Inprogress(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-approved/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02Approved(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApproved(month,year,stateId,districtId);
	}
	
	
	
	
	
	@GetMapping(path = "/invoice-generation-fin08b-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bInitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08b-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bInprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08b-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08bApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08bApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08c-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cInitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cInitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08c-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cInprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cInprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08c-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08cApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08InitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin08-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08InprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin08-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin08ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApprovedOlder(month,year,stateId,districtId);
	}
	@GetMapping(path = "/invoice-generation-fin03-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin03InitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin03-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin03InprogressOlder(month,year,stateId,districtId);
	}

	@GetMapping(path = "/invoice-generation-fin03-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin03ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApprovedOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-initiate-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02InitiateOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin02InitiateOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-inprogress-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02InprogressOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin02InprogressOlder(month,year,stateId,districtId);
	}
	
	@GetMapping(path = "/invoice-generation-fin02-approved-older/{month}/{year}/{stateId}/{districtId}")
	public Iterable<InvoiceGeneration> getInvoiceFin02ApprovedOlder(@PathVariable(name = "month") String month,@PathVariable(name = "year") String year,@PathVariable(name = "stateId") Integer stateId,@PathVariable(name = "districtId") Integer districtId) {
		return fin02Service.getInvoiceFin08cApprovedOlder(month,year,stateId,districtId);
	}

	
}
