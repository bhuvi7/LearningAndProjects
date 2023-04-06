package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.example.demo.EntityModel.CimsHistoryFin02b;
import com.example.demo.EntityModel.Fin03;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin05aSiteConformity;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin10b;
import com.example.demo.EntityModel.Fin11ConcessionElements;
import com.example.demo.EntityModel.Invoice;

import com.example.demo.EntityModel.InvoicePaymentHistory;
import com.example.demo.repository.Fin10bRepository;
import com.example.demo.repository.InvoicePaymentHistoryRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.Fin01Service;
import com.example.demo.service.Fin02Service;
import com.example.demo.service.Fin02aService;
import com.example.demo.service.Fin02bService;
import com.example.demo.service.Fin04Service;
import com.example.demo.service.Fin05aService;
import com.example.demo.service.Fin11Service;
import com.example.demo.service.InvoiceService;

@RestController
@RequestMapping(path="/invoice")
@CrossOrigin(origins = "*")
public class InvoiceController {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	
	@Autowired
	private InvoicePaymentHistoryRepository invoicePaymentHistoryRepository;
	
	@Autowired
	private Fin01Service fin01Service;
	
	@Autowired
	private Fin02aService fin02aService;
	
	@Autowired
	private Fin02Service fin02Service;
	
	@Autowired
	private Fin02bService fin02bService;

	@Autowired
	private Fin04Service fin04Service;
	
	@Autowired
	private Fin05aService fin05aService;
	
	@Autowired
	private Fin11Service fin11Service;

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	Fin10bRepository fin10bRepository;
	
	@GetMapping(path = "/all")
	public Iterable<Invoice> getAllInvoice() {
		return invoiceRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Invoice> getInvoiceById(@PathVariable(name = "id") int id) {
		return invoiceRepository.findById(id);
	}
	
	@GetMapping(path = "/find-id-RA/{id}")
	public Invoice getInvoiceByIdForRA(@PathVariable(name = "id") int id) {
		Invoice invoice =  invoiceRepository.findByIdForRA(id);
		invoice.setInvoicePaymentHistory(invoicePaymentHistoryRepository.findByInvoiceNo(invoice.getInvoiceNo()));
		return invoice;
	}
	//
	@GetMapping(path = "/invoice-no/{invoice-no}")
	public Invoice getInvoiceByInvoiceNo(@PathVariable(name = "invoice-no") String invoiceNo) {
		return invoiceRepository.findByInvoiceNo(invoiceNo);
	}
	@GetMapping(path = "/invoice-no/cndn-id{invoice-no}")
	public Invoice getInvoiceByInvoiceNoCndnId(@PathVariable(name = "invoice-no") int id) {
		return invoiceService.findByInvoiceNoCndnId(id);
	}
	
	@GetMapping(path = "/invoice-no/fetchdate{invoice-no}")
	public List<Fin10b> getInvoiceByInvoiceNofetchdate(@PathVariable(name = "invoice-no") int id) {
		return invoiceService.findByInvoiceNofetchdate(id);
	}
	
	@GetMapping(path = "/invoice-no-service/{invoice-no}")
	public Invoice getInvoiceByPaymentHistoryId(@PathVariable(name = "invoice-no") int id) {
		return invoiceService.findByInvoiceByPaymentHistoryId(id);
	}
	
//	@GetMapping(path = "/invoice-nos/{invoice-no}")
//	public List<Invoice> getInvoiceByInvoiceNos(@PathVariable(name = "invoice-no") String invoiceNo) {
//		return invoiceRepository.findByInvoiceNos(invoiceNo);
//	}
	
	@GetMapping(path = "/invoiceTypeId/{invoiceTypeId}")
	public List<Invoice> getInvoiceByInvoiceTypeId(@PathVariable(name = "invoiceTypeId") int invoiceTypeId) {
		return invoiceRepository.findByInvoiceTypeIdOrderByIdDesc(invoiceTypeId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status/{invoiceTypeId},{status}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNot(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status) {
		return invoiceRepository.findByInvoiceTypeIdAndStatusNot(invoiceTypeId,status);
	}
	
	@GetMapping(path = "/invoicetypeid-status/{invoiceTypeId},{status}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatus(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status) {
		return invoiceRepository.findByInvoiceTypeIdAndStatus(invoiceTypeId,status);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid-districtid/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictId(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,
			@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressList(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIds(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressList(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid-older")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdsOlders(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,
			@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid-districtid-older/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdOlder(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,
			@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressListOlder(invoiceTypeId, status, stateId, districtId);
	}
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid-fin01")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdFin01(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListFin01(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid-fin01-older")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdFin01Older(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListFin01Older(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictId(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,
			@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedList(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid-older/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdOlder(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,
			@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid-districtid")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdForUM(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressList(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status-stateid-districtid-older")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdOlderForUM(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdForUM(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedList(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status-stateid-districtid-older")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdOlderForUM(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-!status")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateId(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressListForFin04(invoiceTypeId, status, stateId, districtId);
	}
	
//	@GetMapping(path = "/!status")
//	public List<Fin07> getFin07InProgressList(@RequestParam(name = "stateId") int stateId,@RequestParam(value = "circleId") Integer circleId,
//			@RequestParam(name = "districtId") Integer districtId,@RequestParam(name = "status") String status) {
//		return fin02aService.fin07InProgressListUsingChf2(status,stateId,districtId);
//	}
	
	@GetMapping(path = "/invoicetypeid-!status-older/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusNotAndStateIdOlder(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceInProgressListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/invoicetypeid-status")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateId(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListForFin04(invoiceTypeId, status, stateId, districtId);
	}
//	@GetMapping(path = "/invoicetypeid-status-stateid-districtid")
//	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdForUM(@RequestParam(name = "invoiceTypeId") int invoiceTypeId,@RequestParam(name = "status") String status,
//			@RequestParam(name = "stateId") int stateId,@RequestParam(name = "districtId") int districtId) {
//		return invoiceService.getInvoiceApprovedList(invoiceTypeId, status, stateId, districtId);
//	}
	
	@GetMapping(path = "/invoicetypeid-status-older/{invoiceTypeId},{status},{stateId},{districtId}")
	public List<Invoice> getInvoiceByInvoiceTypeIdAndStatusAndStateIdOlder(@PathVariable(name = "invoiceTypeId") int invoiceTypeId,@PathVariable(name = "status") String status,@PathVariable(name = "stateId") int stateId,@PathVariable(name = "districtId") int districtId) {
		return invoiceService.getInvoiceApprovedListOlder(invoiceTypeId, status, stateId, districtId);
	}
	
	@GetMapping(path = "/payment-status1|2/{pay-status1},{pay-status2}")
	public List<Invoice> getInvoiceByPaymentStatus1OrPaymentStatus2(@PathVariable(name = "pay-status1") String paymentStatus1,@PathVariable(name = "pay-status2") String paymentStatus2) {
		return invoiceRepository.findByPaymentStatusOrPaymentStatus(paymentStatus1,paymentStatus2);
	}
	
	@PostMapping(path = "/add")
	public Invoice addInvoice(@RequestBody Invoice Invoice) {
		return invoiceRepository.save(Invoice);
	}

	@PostMapping(path = "/createFIN01/{userId}")
	public Invoice addInvoice(@RequestBody List<Fin06> Fin06,@PathVariable(name = "userId") int userId) {
		return fin01Service.addFin01Invoice(Fin06,userId);
	}
	
	@PostMapping(path = "/create-fin02a/{userId}")
	public Invoice createFin02a(@RequestBody List<Fin03a> Fin03a,@PathVariable(name = "userId") int userId) {
		return fin02aService.addFin02aInvoice(Fin03a,userId);
	}
	
	@PostMapping(path = "/create-fin02")
	public Invoice createFin02(@RequestBody List<Fin03> Fin03) {
		return fin02Service.addFin02Invoice(Fin03);
	}
	
	@PostMapping(path = "/create-fin02-chf2/{userId}")
	public Invoice createFin02UsingChf2(@RequestBody List<Fin03> Fin03,@PathVariable(name = "userId") int userId) {
		return fin02Service.addFin02InvoiceUsingChf2(Fin03,userId);
	}
	
	@PostMapping(path = "/create-fin02b/{userId}")
	public Invoice createFin02b(@RequestBody CimsHistoryFin02b cimsHistoryFin02b,@PathVariable(name = "userId") int userId) {
		return fin02bService.addFin02bInvoice(cimsHistoryFin02b,userId);
	}

	@PostMapping(path = "/create-fin04")
	public Invoice createFin04(@RequestBody List<Fin10b> Fin10b) {
		return fin04Service.createFin04(Fin10b);
	}
	
	@PostMapping(path = "/create-fin05a")
	public Invoice createFin05a(@RequestBody List<Fin05aSiteConformity> Fin05aSiteConformity) {
		return fin05aService.createFin05a(Fin05aSiteConformity);
	}
	
	@PostMapping(path = "/create-fin11")
	public Invoice createFin11(@RequestBody List<Fin11ConcessionElements> Fin11ConcessionElements) {
		return fin11Service.createFin11(Fin11ConcessionElements);
	}

	@PutMapping(path = "/update-status")
	public Invoice updateInvoiceStatus(@RequestBody Invoice Invoice) {
		return fin01Service.updateFin01InvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin02a-status")
	public Invoice updateFin02aInvoiceStatus(@RequestBody Invoice Invoice) {
		return fin02aService.updateFin02aInvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin02-status")
	public Invoice updateFin02InvoiceStatus(@RequestBody Invoice Invoice) {
		return fin02Service.updateFin02InvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin02b-status")
	public Invoice updateFin02bInvoiceStatus(@RequestBody Invoice Invoice) {
		return fin02bService.updateFin02bInvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin02-status-chf2")
	public Invoice updateFin02InvoiceStatusUsingChf2(@RequestBody Invoice Invoice) {
		return fin02Service.updateFin02InvoiceStatusUsingChf2(Invoice);
	}

	@PutMapping(path = "/update-fin04-status")
	public Invoice updateFin04InvoiceStatus(@RequestBody Invoice Invoice) {
		return fin04Service.updateFin04InvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin05a-status")
	public Invoice updateFin05aInvoiceStatus(@RequestBody Invoice Invoice) {
		return fin05aService.updateFin05aInvoiceStatus(Invoice);
	}
	
	@PutMapping(path = "/update-fin11-status")
	public Invoice updateFin11InvoiceStatus(@RequestBody Invoice Invoice) {
		return fin11Service.updateFin11InvoiceStatus(Invoice);
	}

	@PutMapping(path = "/update")
	public Invoice updateInvoice(@RequestBody Invoice Invoice) {
		return invoiceRepository.save(Invoice);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteInvoice(@PathVariable(name = "id") int id) {
		invoiceRepository.deleteById(id);
	}
	
	@GetMapping(path = "/receivable/{retention}")
	public Iterable<Invoice> getAllReceivable(@PathVariable(name = "retention") String retention) {		         
		return invoiceService.getAllReceivable(retention);
	}
	
//	@GetMapping(path = "/all-district-stateid/{stateId}")
//	public Iterable<Invoice> getAllDistrictByStateId(@PathVariable(name = "stateId") int stateId) {		         
//		return invoiceService.groupByAndSumAllDistrictByStateId(stateId);
//	}
	
	@GetMapping(path = "/receivable-state-name/{retention}/{stateName}")
	public Iterable<Invoice> getAllReceivableByStateName(@PathVariable(name = "retention") String retention,@PathVariable(name = "stateName") String stateName) {		         
		return invoiceService.getAllReceivableByStateName(retention,stateName);
	}
	
	
	@GetMapping(path = "/receivable-circle-code/{retention}/{circleCode}")
	public Iterable<Invoice> getAllReceivableByCircleCode(@PathVariable(name = "retention") String retention,@PathVariable(name = "circleCode") String circleCode) {		         
		return invoiceService.getAllReceivableByCircleCode(retention,circleCode);
	}
	
	@GetMapping(path = "/receivable-district-name/{retention}/{districtName}")
	public Iterable<Invoice> getAllReceivableByDistrictName(@PathVariable(name = "retention") String retention,@PathVariable(name = "districtName") String districtName) {		         
		return invoiceService.getAllReceivableByDistrictName(retention,districtName);
	}
	
	
	@GetMapping(path = "/receivable-invoice/{retention}/{key}/{value}")
	public Iterable<Invoice> getAllReceivableInvoice(@PathVariable(name = "retention") String retention,@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {		         
		return invoiceService.getAllReceivableByInvoiceType(retention,key,value);
	}
	
	@GetMapping(path = "/receivable-quater/{retention}/{key}/{value}")
	public Iterable<Invoice> getAllReceivableQuater(@PathVariable(name = "retention") String retention,@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {
		return invoiceService.getAllReceivableQuater(retention,key,value);
	}


	@GetMapping(path = "/receivable-month/{retention}/{key}/{value}/{quater}/{year}")
	public Iterable<Invoice> getAllReceivableMonth(@PathVariable(name = "retention") String retention,@PathVariable(name = "key") String key,@PathVariable(name = "value") String value,@PathVariable(name = "quater") String quater,@PathVariable(name = "year") String year) {
		return invoiceService.getAllReceivableMonth(retention,key,value,quater,year);
	}
	
	@GetMapping(path = "/retention")
	public Iterable<Invoice> getAllRetention() {		         
		return invoiceService.getAllRetention();
	}
	@GetMapping(path = "/retention-state-name/{stateName}")
	public Iterable<Invoice> getAllRetentionByStateName(@PathVariable(name = "stateName") String stateName) {		         
		return invoiceService.getAllRetentionByStateName(stateName);
	}
	@GetMapping(path = "/retention-circle-code/{circleCode}")
	public Iterable<Invoice> getAllRetentionByCircleCode(@PathVariable(name = "circleCode") String circleCode) {		         
		return invoiceService.getAllRetentionByCircleCode(circleCode);
	}
	
	
	@GetMapping(path = "/retention-district-name/{districtName}")
	public Iterable<Invoice> getAllRetentionByDistrictName(@PathVariable(name = "districtName") String districtName) {		         
		return invoiceService.getAllRetentionByDistrictName(districtName);
	}
	
	
	@GetMapping(path = "/retention-invoice/{key}/{value}")
	public Iterable<Invoice> getAllRetentionInvoice(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {		         
		return invoiceService.getAllRetentionByInvoiceType(key,value);
	}
	
	@GetMapping(path = "/retention-quater/{key}/{value}")
	public Iterable<Invoice> getAllRetentionQuater(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {		         
		return invoiceService.getAllRetentionQuater(key,value);
	}
	@GetMapping(path = "/retention-month/{key}/{value}/{quater}/{year}")
	public Iterable<Invoice> getAllRetentionMonth(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value,@PathVariable(name = "quater") String quater,@PathVariable(name = "year") String year) {		         
		return invoiceService.getAllRetentionMonth(key,value,quater,year);
	}
	@GetMapping(path = "/ra-last-quater")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuater() {		         
		return invoiceService.getAllRevenueAnalysisLastQuater();
	}
	@GetMapping(path = "/ra-last-quater-state-name/{stateName}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterByStateName(@PathVariable(name = "stateName") String stateName) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterByStateName(stateName);
	}
	
	@GetMapping(path = "/ra-last-quater-circle-code/{circleCode}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterByCircleCode(@PathVariable(name = "circleCode") String circleCode) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterByCircleCode(circleCode);
	}
	@GetMapping(path = "/ra-last-quater-district-name/{districtName}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterByDistrictName(@PathVariable(name = "districtName") String districtName) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterByDistrictName(districtName);
	}
	
	
	@GetMapping(path = "/ra-last-quater-invoice/{key}/{value}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterInvoice(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterByInvoiceType(key,value);
	}
	
	@GetMapping(path = "/ra-last-quater-quater/{key}/{value}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterQuater(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterQuater(key,value);
	}
	
	@GetMapping(path = "/ra-last-quater-month/{key}/{value}/{quater}/{year}")
	public Iterable<Invoice> getAllRevenueAnalysisLastQuaterMonth(@PathVariable(name = "key") String key,@PathVariable(name = "value") String value,@PathVariable(name = "quater") String quater,@PathVariable(name = "year") String year) {		         
		return invoiceService.getAllRevenueAnalysisLastQuaterMonth(key,value,quater,year);
	}
	
	
	@GetMapping(path="/filter")
	public Iterable<Invoice> getFilteredInvoice(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "circleCode", required = false) String circleCode,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) List<String> invoiceTypeName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId
			)  {
		
		return invoiceRepository.findByInvoice(stateName,circleCode, districtName,invoiceTypeName,clinicTypeId);
	}
	
	@GetMapping(path="/filter-for-fin09")
	public Iterable<Invoice> getFilteredInvoiceForFin09(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "circleCode", required = false) String circleCode,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) String invoiceTypeName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId
			)  {
		
		return invoiceRepository.findByInvoiceForFin09(stateName,circleCode, districtName,invoiceTypeName,clinicTypeId);
	}
	
	@GetMapping(path="/filterYear")
    public Iterable<Invoice> getFilteredInvoiceByYear(   
            @RequestParam(value = "stateName", required = false) String stateName,
            @RequestParam(value = "circleCode", required = false) String circleCode,
            @RequestParam(value = "districtName", required = false) String districtName,
            @RequestParam(value = "invoiceTypeName", required = false) String invoiceTypeName,
            @RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
            @RequestParam(value = "year", required = false) String year
            )  {
       
        return invoiceRepository.findByInvoiceYear(stateName,circleCode, districtName,invoiceTypeName,clinicTypeId,year);
    }
	
	@GetMapping(path="/filter-inv-rep")
	public Iterable<Invoice> getFilteredInvoice(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "circleCode", required = false) String circleCode,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) List<String> invoiceTypeName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
			@RequestParam(value = "month", required = false) List<String> month,
			@RequestParam(value = "year", required = false) List<String> year
			)  {
		
		return invoiceRepository.findByInvoiceReport(stateName,circleCode, districtName,invoiceTypeName,clinicTypeId,month,year);
	}
	
	@GetMapping(path="/filter/payment-status")
	public Iterable<Invoice> getFilteredInvoiceForBankReceipting(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) String invoiceTypeName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId
			)  {
		
		return invoiceRepository.findByInvoicePaymentStatus(stateName, districtName,invoiceTypeName,clinicTypeId);
	}
	
//	@GetMapping(path="/filter/payment-status-penalty")
//	public Iterable<Invoice> getFilteredInvoiceForBankReceiptingPenalty(	
//			@RequestParam(value = "stateName", required = false) String stateName,
//			@RequestParam(value = "districtName", required = false) String districtName,
//			@RequestParam(value = "invoiceTypeName", required = false) String invoiceTypeName,
//			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
//			@RequestParam(value = "approvalQuater", required = false) String approvalQuater,
//			@RequestParam(value = "approvalYear", required = false) String approvalYear
//			)  {
//		
//		return invoiceService.findByInvoicePaymentStatusPenalty(stateName, districtName,invoiceTypeName,clinicTypeId,approvalQuater,approvalYear);
//	}
	
	@GetMapping(path="/filter/invoice-no")
	public Iterable<Invoice> getFilteredInvoiceNo(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) String invoiceTypeName,
			@RequestParam(value = "clinicTypeId", required = false) Integer clinicTypeId,
			@RequestParam(value = "invoiceNo", required = false) String invoiceNo,
			@RequestParam(value = "paymentRefNo", required = false) String paymentRefNo,
			@RequestParam(value = "invoiceDateFrom", required = false) String invoiceDateFrom,
			@RequestParam(value = "invoiceDateTo", required = false) String invoiceDateTo
			)  {
		
		return invoiceRepository.findByInvoiceNo(stateName, districtName,invoiceTypeName,clinicTypeId,invoiceNo,paymentRefNo,invoiceDateFrom,invoiceDateTo);
	}
	@GetMapping(path="/consolidated-report")	
	public Iterable<Invoice> ConsolidatedReport(				
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "invoiceTypeName", required = false) List<String> invoiceTypeName,
			@RequestParam(value = "month", required = false) List<String> month,	
			@RequestParam(value = "year", required = false) List<String> year)  {	
			
		return invoiceService.getConsolidatedReport(stateName,invoiceTypeName,month,year);	
	}
	
	@GetMapping(path="/monthwise")
	public Iterable<Invoice> getInvoiceByMonthWise(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "circleCode", required = false) String circleCode,
			@RequestParam(value = "districtName", required = false) String districtName,			
			@RequestParam(value = "quater", required = false) String quater,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year
			)  {
		
		return invoiceRepository.findByInvoiceMonthWise(stateName, circleCode,districtName,quater,month,year);
	}
}

