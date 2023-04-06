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

import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoicePaymentHistory;
import com.example.demo.repository.InvoicePaymentHistoryRepository;
import com.example.demo.service.InvoicePaymentHistoryService;
import com.example.demo.service.InvoiceService;

@RestController
@RequestMapping(path="/invoice-payment-history")
@CrossOrigin(origins = "*")
public class InvoicePaymentHistoryController {
	
	@Autowired
	private InvoicePaymentHistoryRepository invoicePaymentHistoryRepository;
	
	@Autowired
	private InvoicePaymentHistoryService invoicePaymentHistoryService;
	
	@GetMapping(path = "/all")
	public Iterable<InvoicePaymentHistory> getAllInvoicePaymentHistory() {
		return invoicePaymentHistoryRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<InvoicePaymentHistory> getInvoicePaymentHistoryById(@PathVariable(name = "id") int id) {
		return invoicePaymentHistoryRepository.findById(id);
	}
	
	@GetMapping(path = "fetch-by-id/{id}")
	public InvoicePaymentHistory getInvoicePaymentHistoryByIdService(@PathVariable(name = "id") int id) {
		return invoicePaymentHistoryService.findByIdService(id);
	}
	
	
	@PostMapping(path = "/add")
	public InvoicePaymentHistory addInvoicePaymentHistory(@RequestBody InvoicePaymentHistory invoicePaymentHistoty) {
		return invoicePaymentHistoryRepository.save(invoicePaymentHistoty);
	}
	
	@PutMapping(path = "/update")
	public InvoicePaymentHistory updateInvoicePaymentHistory(@RequestBody InvoicePaymentHistory invoicePaymentHistoty) {
		return invoicePaymentHistoryRepository.save(invoicePaymentHistoty);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteInvoicePaymentHistory(@PathVariable(name = "id") int id) {
		invoicePaymentHistoryRepository.deleteById(id);
	}
	
	@PostMapping(path = "/add-payment-to-invoice")
	public InvoicePaymentHistory addPaymentForInvoice(@RequestBody InvoicePaymentHistory invoicePaymentHistory) {
		return invoicePaymentHistoryService.paymentForInvoice(invoicePaymentHistory);
	}
	
	@PostMapping(path = "/add-payment-to-debit")
	public InvoicePaymentHistory addPaymentForDebit(@RequestBody InvoicePaymentHistory invoicePaymentHistory) {
		return invoicePaymentHistoryService.addPaymentForDebit(invoicePaymentHistory);
	}
	
	@PostMapping(path="/add-payment-to-multiple-invoice")
	public List<InvoicePaymentHistory> addPaymentToMultipleInvoice(@RequestBody List<InvoicePaymentHistory> invoicePaymentHistories) {
		System.out.println(invoicePaymentHistories);
		return invoicePaymentHistoryService.paymentForMultipleInvoice(invoicePaymentHistories);
	}
	
	@PostMapping(path="/penalty-payment-to-invoice")
	public List<InvoicePaymentHistory> penaltyPaymentToInvoice(@RequestBody List<InvoicePaymentHistory> invoicePaymentHistory ) {
		return invoicePaymentHistoryService.penaltyPaymentToInvoice(invoicePaymentHistory);
	}
	
	@PostMapping(path="/penalty-payment-to-invoice-special")
	public List<InvoicePaymentHistory> penaltyPaymentToInvoiceSpecial(@RequestBody List<InvoicePaymentHistory> invoicePaymentHistory ) {
		return invoicePaymentHistoryService.penaltyPaymentToInvoiceSpecial(invoicePaymentHistory);
	}
	
	//
	@GetMapping(path="/filter")
	public List<InvoicePaymentHistory> getFilteredInvoiceNo(	
			@RequestParam(value = "paymentRefNo", required = false) String paymentRefNo,
			@RequestParam(value = "transactionRefNo", required = false) String transactionRefNo,
			@RequestParam(value = "paymentMode", required = false)  List<String> paymentMode,
			@RequestParam(value = "paymentDateFrom", required = false) String paymentDateFrom,
			@RequestParam(value = "paymentDateTo", required = false) String paymentDateTo
			)  {
		
		return invoicePaymentHistoryRepository.findByPaymentDateAndPaymentMode(transactionRefNo,paymentRefNo,paymentMode,paymentDateFrom,paymentDateTo);
	}
	
	@GetMapping(path="/filter-entry-date")
	public List<InvoicePaymentHistory> getFilteredInvoiceNoEntryDate(	
			@RequestParam(value = "paymentRefNo", required = false) String paymentRefNo,
			@RequestParam(value = "transactionRefNo", required = false) String transactionRefNo,
			@RequestParam(value = "paymentMode", required = false) List<String> paymentMode,
			@RequestParam(value = "paymentDateFrom", required = false) String paymentDateFrom,
			@RequestParam(value = "paymentDateTo", required = false) String paymentDateTo
			)  {
		
		return invoicePaymentHistoryRepository.findByPaymentDateAndPaymentModeEntryDate(transactionRefNo,paymentRefNo,paymentMode,paymentDateFrom,paymentDateTo);
	}
	
	@GetMapping(path="/filter-new")
	public List<InvoicePaymentHistory> getFilteredInvoiceNo(	
			@RequestParam(value = "paymentRefNo", required = false) String paymentRefNo,
			@RequestParam(value = "transactionRefNo", required = false) String transactionRefNo,
			@RequestParam(value = "paymentMode", required = false) List<String> paymentMode
			)  {
		
		return invoicePaymentHistoryRepository.findByPaymentMode(transactionRefNo,paymentRefNo,paymentMode);
	}
	
	@GetMapping(path="/collection-report")
	public List<InvoicePaymentHistory> CollectionReport(	
			@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "districtName", required = false) String districtName,
			@RequestParam(value = "invoiceTypeName", required = false) List<String> invoiceTypeName,
			@RequestParam(value = "clinicTypeCode", required = false) String clinicTypeCode,
			@RequestParam(value = "transactionRefNo", required = false) String transactionRefNo,
			@RequestParam(value = "paymentRefNo", required = false) String paymentRefNo,
			@RequestParam(value = "dateFrom", required = false) String dateFrom,
			@RequestParam(value = "dateTo", required = false) String dateTo,
			@RequestParam(value = "paymentMode", required = false) String paymentMode
			)  {		
		return invoicePaymentHistoryService.getCollectionReport(stateName, districtName,invoiceTypeName,clinicTypeCode,transactionRefNo,paymentRefNo,paymentMode,dateFrom,dateTo);
	}
	

	
}
