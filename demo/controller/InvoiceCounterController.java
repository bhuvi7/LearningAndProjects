package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.InvoiceCounter;
import com.example.demo.service.InvoiceCounterService;

@RestController
@RequestMapping(path ="/invoice-counter")
@CrossOrigin(origins = "*")
public class InvoiceCounterController {
	
	@Autowired
	InvoiceCounterService invoiceCounterService;
	 
	@GetMapping(path="/fin01")
	public InvoiceCounter getFin01InvoiceCounter() {
		return invoiceCounterService.getFin01InvoiceCounter();
	}
	
	@GetMapping(path="/fin02a")
	public InvoiceCounter getFin02AInvoiceCounter() {
		return invoiceCounterService.getFin02AInvoiceCounter();
	} 
	
	@GetMapping(path="/fin02")
	public InvoiceCounter getFin02InvoiceCounter() {
		return invoiceCounterService.getFin02InvoiceCounter();
	}
	
	@GetMapping(path="/fin02b")
	public InvoiceCounter getFin02BInvoiceCounter() {
		return invoiceCounterService.getFin02BInvoiceCounter();
	}
	
	
	
	
}
