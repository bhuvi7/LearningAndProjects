package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.ClinicType;
import com.example.demo.EntityModel.District;
import com.example.demo.EntityModel.Fin11ConcessionElements;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.repository.ClinicTypeRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.Fin11ConcessionElementsRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

@Service
public class Fin11Service {
	
	//@CreationTimestamp
	//private LocalDate createDate;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ClinicTypeRepository clinicTypeRepository;
	
	@Autowired
	private Fin11ConcessionElementsRepository fin11CERepository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public String clinicTypeCodeForInvoice(String clinicTypeCode) {
		String clinicTypeCodeForInvoiceCode = null;
		if(clinicTypeCode.equals("PKD")) {clinicTypeCodeForInvoiceCode = "K"; }
		else if(clinicTypeCode.equals("PPD")) { clinicTypeCodeForInvoiceCode = "P";}
		return clinicTypeCodeForInvoiceCode;
	}
	
	public String invoiceQuater(String month) {
		String quater = null;
		if(month.equals("1") || month.equals("2") || month.equals("3")){ quater = "Q1";}
		else if(month.equals("4") || month.equals("5") || month.equals("6")){ quater = "Q2";}
		else if(month.equals("7") || month.equals("8") || month.equals("9")){ quater = "Q3";}
		else if(month.equals("10") || month.equals("11") || month.equals("12")){ quater = "Q4";}
		return quater;
	}
	
	public String findDistrictCode(Integer districtId) {
		District district = districtRepository.findByDistrictId(districtId);
		String districtCode = district.getDistrictCode();
		return districtCode;
	}
	
	public String findClinicTypeCode(Integer clinicTypeId) {
		ClinicType clinicType = clinicTypeRepository.findByClinicTypeCode(clinicTypeId);
		String clinicTypeCode = "";
		if(clinicType.getClinicTypeCode().equals("PKD")) {
			clinicTypeCode = "K";
		} else if(clinicType.getClinicTypeCode().equals("PPD")) {
			clinicTypeCode = "P";
		}
		return clinicTypeCode;
	}
	
	public Invoice createFin11(List<Fin11ConcessionElements> Fin11CE){
		Invoice invoice = new Invoice();
		Fin11ConcessionElements Fin11CEData = Fin11CE.get(0);
		BeanUtils.copyProperties(Fin11CEData, invoice );
		invoice.setInvoiceTypeId(7);
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoiceRepository.save(invoice);
		invoice.setMonth(String.valueOf(invoice.getCreatedDate().getMonthValue()));
		invoice.setYear(String.valueOf(invoice.getCreatedDate().getYear()));
		invoice.setCode("FIN11CE-"+findDistrictCode(invoice.getDistrictId())+"-"+findClinicTypeCode(invoice.getClinicTypeId())+"-"+invoice.getMonth()+"-"+invoice.getYear());
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoiceRepository.save(invoice);
		Fin11CE.forEach( fin11CE -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin11CE.getConcessionElements());
			fin11CE.setFin11InvNo(invoice.getInvoiceNo());
			fin11CE.setFin11InvStatus(invoice.getStatus());
			fin11CERepository.save(fin11CE);
		});
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(7);
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
		}
		if(invoiceType.getSstIncluded().equals("Y")) {
			invoice.setSst(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getSstPercentage()/100))));
		}
		invoice.setNetAfterSst(invoice.getInvoiceBaseValue()+invoice.getSst());
		invoice.setNetAfterSst(Double.valueOf(df.format(invoice.getNetAfterSst())));
		invoice.setTotalInvoiceValue(invoice.getNetAfterSst());
		invoice.setOutstandingAmount(invoice.getNetAfterSst());
		invoice.setTotalInvoiceValueWoRetention(invoice.getTotalInvoiceValue()-invoice.getRetentionAmount());
		invoice.setPaymentStatus("PAYMENT-PENDING");
		invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Invoice updateFin11InvoiceStatus(Invoice Invoice) {
		List<Fin11ConcessionElements> Fin11CE = fin11CERepository.findByFin11InvNo(Invoice.getInvoiceNo());
		invoiceRepository.save(Invoice);
		Fin11CE.forEach(fin11CE -> {
			fin11CE.setFin11InvStatus(Invoice.getStatus());
			fin11CERepository.save(fin11CE);
		});
		return Invoice;
	}
	

}
