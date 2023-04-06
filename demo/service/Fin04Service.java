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

import com.example.demo.EntityModel.Clinic;
import com.example.demo.EntityModel.ConstructionNumberingSequence;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin10b;
import com.example.demo.EntityModel.Fin10bConstructionWorks;
import com.example.demo.EntityModel.Fin10bDistinct;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.EntityModel.NumberingSequence;
import com.example.demo.repository.ClinicRepository;
import com.example.demo.repository.ConstructionNumberingSequenceRepository;
import com.example.demo.repository.Fin10bConstructionWorksRepository;
import com.example.demo.repository.Fin10bRepository;
import com.example.demo.repository.InvoicePaymentHistoryRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;
import com.example.demo.repository.NumberingSequenceRepository;

@Service
public class Fin04Service {
	
	//@CreationTimestamp
	//private LocalDate createDate;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private Fin10bRepository fin10bRepository;
	
	@Autowired
	private Fin10bConstructionWorksRepository fin10bConstructionWorksRepository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ConstructionNumberingSequenceRepository constructionNumberingSequenceRepository;
	
	@Autowired
	private NumberingSequenceRepository numberingSequenceRepository;
//	
	
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
	
	public Fin10b createFin10b(Fin10b Fin10b) {
		List<Fin10bConstructionWorks> Fin10bConstructionWorks = Fin10b.getFin10bConstructionWorks();
		
//		List<NumberingSequence> numbSeq = (List<NumberingSequence>) numberingSequenceRepository.findAll();
//		numbSeq.get(0).setCreditNoteSequence(numbSeq.get(0).getCreditNoteSequence() + 1);
//		numberingSequenceRepository.save(numbSeq.get(0));
		System.out.println(Fin10b.getClinicTypeId());
		List<Fin10b> listofInvoice =fin10bRepository.findByClinicTypeIdAndStateId(Fin10b.getClinicTypeId(),Fin10b.getStateId());
		Integer lengthOfInvoice= (listofInvoice.size()+1);
		int length = String.valueOf(lengthOfInvoice).length();
		String zeros="";
		if(length==1) {
			zeros="00";
		}
		else if(length==2) {
			zeros="0";
		}
		else if(length==3) {
			zeros="";
		}
//	System.out.println(length);
		Fin10b.setStatus("IN INTERNAL APPROVAL");
		Fin10b.setTotalAmount(0.0);
		fin10bRepository.save(Fin10b);
		Clinic clinic = clinicRepository.findByClinicId(Fin10b.getClinicId());
		Fin10b.setClinicCode(clinic.getClinicCode());
		//mine
	
//			Fin10b.setCode("FIN10B-"+ Fin10b.getClinicCode()+"-CW"+Fin10b.getId());
		Fin10b.setCode("FIN10B-"+ Fin10b.getClinicCode()+"-"+ zeros+lengthOfInvoice );		
		Fin10b.setCertOfAcceptanceRef(Fin10bConstructionWorks.get(0).getCertOfAcceptanceRef());
		Fin10bConstructionWorks.forEach(e -> {
			e.setFin10bRefNo(Fin10b.getCode());
			Fin10b.setTotalAmount(Fin10b.getTotalAmount()+e.getAgreedTotal());
			fin10bConstructionWorksRepository.save(e);
		});
		Fin10b.setTotalAmount(Double.valueOf(df.format(Fin10b.getTotalAmount())));
		fin10bRepository.save(Fin10b);
		Fin10b.setDate(Fin10b.getDate());
		Fin10b.setMonth(String.valueOf(Fin10b.getDate().getMonthValue()));
		Fin10b.setYear(String.valueOf(Fin10b.getDate().getYear()));
		fin10bRepository.save(Fin10b);
		return Fin10b;
	}
	
	public List<Fin10b> getFin04CreateList() {
		Set<Fin10bDistinct> uniqueSet = new HashSet<>();
		List<Fin10b> uniqueFin1b0List = new ArrayList<Fin10b>(); 
		List<Fin10b>  fin10bList = (List<Fin10b>) fin10bRepository.findByStatusAndFin04InvStatusIsNull("APPROVED BY MOH");
			fin10bList.forEach(e->{
			Fin10bDistinct CimsTransactionUnique = new Fin10bDistinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin1b0List.add(e);
				System.out.println(uniqueFin1b0List);
			}
		});

		return uniqueFin1b0List;
	}
	
	public Invoice createFin04(List<Fin10b> Fin10b){
		Invoice invoice = new Invoice();
		Fin10b fin10bData = Fin10b.get(0);
		BeanUtils.copyProperties(fin10bData, invoice );
		invoice.setId(null);
		invoice.setCreatedDate(null);
		invoice.setUpdatedDate(null);
		invoice.setClinicTypeId(fin10bData.getClinicTypeId());
		invoice.setInvoiceTypeId(5);
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoiceRepository.save(invoice);
		LocalDate date = LocalDate.now();
		ConstructionNumberingSequence conseq = constructionNumberingSequenceRepository.findByStateIdAndClinicTypeId(fin10bData.getStateId(), fin10bData.getClinicTypeId());
		conseq.setConstructionWorkingSequence(conseq.getConstructionWorkingSequence() + 1);
		constructionNumberingSequenceRepository.save(conseq);
		int length = String.valueOf(conseq.getConstructionWorkingSequence()).length();
		String zeros="";
		if(length==1) {
			zeros="00";
		}
		else if(length==2) {
			zeros="0";
		}
		else if(length==3) {
			zeros="";
		}
		invoice.setCode("FIN04"+fin10bData.getStateCode()+fin10bData.getClinicTypeCode()+zeros +conseq.getConstructionWorkingSequence()+"/"+fin10bData.getYear().substring(2));
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoiceRepository.save(invoice);
		Fin10b.forEach( fin10b -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin10b.getTotalAmount());
			fin10b.setFin04InvNo(invoice.getInvoiceNo());
			fin10b.setFin04InvStatus(invoice.getStatus());
			fin10bRepository.save(fin10b);
		});
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(5);
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
		}
		if(invoiceType.getSstIncluded().equals("Y")) {
			invoice.setSst(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getSstPercentage()/100))));
		}
		invoice.setDebitNoteEntry(0.0);
		invoice.setNetAfterSst(invoice.getInvoiceBaseValue()+invoice.getSst());
		invoice.setNetAfterSst(Double.valueOf(df.format(invoice.getNetAfterSst())));
		invoice.setTotalInvoiceValue(invoice.getNetAfterSst());
		invoice.setOutstandingAmount(invoice.getNetAfterSst());
		invoice.setTotalInvoiceValueWoRetention(invoice.getTotalInvoiceValue()-invoice.getRetentionAmount());
		invoice.setPaymentStatus("PAYMENT-PENDING");
		invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Invoice updateFin04InvoiceStatus(Invoice Invoice) {
		List<Fin10b> Fin10b = fin10bRepository.findByFin04InvNo(Invoice.getInvoiceNo());
		invoiceRepository.save(Invoice);
		Fin10b.forEach(fin10b -> {
			fin10b.setFin04InvStatus(Invoice.getStatus());
			fin10bRepository.save(fin10b);
		});
		return Invoice;
	}
	
	public List<Fin10b> fin10bInProgressListUsingChf2(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin10b> fin10bInProgressList = new ArrayList<Fin10b>();
		if(stateId == 0 && districtId == 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusNotAndMonth(status);
		} else if(stateId != 0 && districtId == 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusNotAndStateIdAndMonth(status,stateId);
		} else if(stateId != 0 && districtId != 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId);
		}
//		fin07InProgressList.forEach(fin07 -> {
//			fin07.setCimsHistoryFin02a(null);
//		});
		return fin10bInProgressList;
	}
	
	public List<Fin10b> fin10bApprovedListUsingChf2(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin10b> fin10bInProgressList = new ArrayList<Fin10b>();
		if(stateId == 0 && districtId == 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusAndMonth(status);
		} else if(stateId != 0 && districtId == 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusAndStateIdAndMonth(status,stateId);
		} else if(stateId != 0 && districtId != 0 ) {
			fin10bInProgressList = fin10bRepository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId);
		}
//		fin07InProgressList.forEach(fin07 -> {
//			fin07.setCimsHistoryFin02a(null);
//		});
		return fin10bInProgressList;
	}
	

}
