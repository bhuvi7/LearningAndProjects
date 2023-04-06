package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.ClinicType;
import com.example.demo.EntityModel.District;
import com.example.demo.EntityModel.Fin05aSiteConformity;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.repository.ClinicTypeRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.Fin05aSiteConformityRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

@Service
public class Fin05aService {
	
	//@CreationTimestamp
	//private LocalDate createDate;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ClinicTypeRepository clinicTypeRepository;
	
	@Autowired
	private Fin05aSiteConformityRepository fin05aSCRepository;
	
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
	
	public Invoice createFin05a(List<Fin05aSiteConformity> Fin05aSC){
		Invoice invoice = new Invoice();
		Fin05aSiteConformity Fin05aSCData = Fin05aSC.get(0);
		BeanUtils.copyProperties(Fin05aSCData, invoice );
		invoice.setInvoiceTypeId(6);
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoiceRepository.save(invoice);
		invoice.setMonth(String.valueOf(invoice.getCreatedDate().getMonthValue()));
		invoice.setYear(String.valueOf(invoice.getCreatedDate().getYear()));
		invoice.setCode("FIN05ASC-"+findDistrictCode(invoice.getDistrictId())+"-"+findClinicTypeCode(invoice.getClinicTypeId())+"-"+invoice.getMonth()+"-"+invoice.getYear());
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoiceRepository.save(invoice);
		Fin05aSC.forEach( fin05aSC -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin05aSC.getSiteConformityExpenses());
			fin05aSC.setFin05aInvNo(invoice.getInvoiceNo());
			fin05aSC.setFin05aInvStatus(invoice.getStatus());
			fin05aSCRepository.save(fin05aSC);
		});
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(6);
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
	
	public Invoice updateFin05aInvoiceStatus(Invoice Invoice) {
		List<Fin05aSiteConformity> Fin05aSC = fin05aSCRepository.findByFin05aInvNo(Invoice.getInvoiceNo());
		invoiceRepository.save(Invoice);
		Fin05aSC.forEach(fin05aSC -> {
			fin05aSC.setFin05aInvStatus(Invoice.getStatus());
			fin05aSCRepository.save(fin05aSC);
		});
		return Invoice;
	}
	

}
