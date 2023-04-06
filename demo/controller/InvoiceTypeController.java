package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.repository.InvoiceTypeRepository;

@RestController
@RequestMapping(path="/invoice-type")
@CrossOrigin(origins = "*")
public class InvoiceTypeController {

	@Autowired
	InvoiceTypeRepository invoiceTypeRepository;
	
	@GetMapping(path = "/all")
	public Iterable<InvoiceType> getAllInvoiceType() {
		return invoiceTypeRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<InvoiceType> getInvoiceTypeById(@PathVariable(name = "id") int id) {
		return invoiceTypeRepository.findById(id);
	}
	
	@GetMapping(path = "/sst-percentage/{id}")
	public Double getInvoiceTypeSstPercentageById(@PathVariable(name = "id") int id) {
		return invoiceTypeRepository.findByInvoiceType(id).getSstPercentage();
	}
	
	@PostMapping(path = "/add")
	public InvoiceType addInvoiceType(@RequestBody InvoiceType invoiceType) {
		return invoiceTypeRepository.save(invoiceType);
	}

	@PutMapping(path = "/update")
	public InvoiceType updateInvoiceType(@RequestBody InvoiceType invoiceType) {
		return invoiceTypeRepository.save(invoiceType);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteFin10b(@PathVariable(name = "id") int id) {
		invoiceTypeRepository.deleteById(id);
	}
}
