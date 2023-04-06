package com.example.demo.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.EntityModel.CimsHistoryFin02b;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.repository.CimsHistoryFin02bRepository;
import com.example.demo.repository.ClinicRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class Fin02bService {
	
	@CreationTimestamp
	private LocalDate createdDate = LocalDate.now();
	
	int totalSelectedEquip = 0;
	
	DecimalFormat df = new DecimalFormat("###.##");
	
	@Autowired
	private CimsHistoryFin02bRepository cimsHistoryFin02bRepository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	public String invoiceQuater(String month) {
		String quater = null;
		if(month.equals("1") || month.equals("2") || month.equals("3")){ quater = "Q1";}
		else if(month.equals("4") || month.equals("5") || month.equals("6")){ quater = "Q2";}
		else if(month.equals("7") || month.equals("8") || month.equals("9")){ quater = "Q3";}
		else if(month.equals("10") || month.equals("11") || month.equals("12")){ quater = "Q4";}
		return quater;
	}
	
//	public List<CimsHistoryFin02b> getFin02bCreateList(Integer stateId){
//		List<CimsHistoryFin02b> cimsHistoryFin02b = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndStateId(stateId);
//		return cimsHistoryFin02b;
//	}
	
	public List<CimsHistoryFin02b> getFin02bCreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		return cimsHistoryFin02bList;
	}
	
	public List<CimsHistoryFin02b> getFin02bCreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		return cimsHistoryFin02bList;
	}
	
	
	
	public Invoice addFin02bInvoice(CimsHistoryFin02b cimsHistoryFin02b,int userId) {
		Invoice invoice = new Invoice();
		String clinicCatCode = ""; 
		if(cimsHistoryFin02b.getClinicTypeCode().equals("PKD")) { clinicCatCode = "K"; }
		else if(cimsHistoryFin02b.getClinicTypeCode().equals("PPD")) { clinicCatCode = "P"; }
		invoice.setCode("2B-"+cimsHistoryFin02b.getStateCode()+"-"+cimsHistoryFin02b.getDistrictCode()+"-"+clinicCatCode+"-"+cimsHistoryFin02b.getMonth()+"-"+cimsHistoryFin02b.getYear());
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setInvoiceTypeId(4);
		invoice.setStateId(cimsHistoryFin02b.getStateId());
		invoice.setDistrictId(cimsHistoryFin02b.getDistrictId());
		invoice.setMonth(cimsHistoryFin02b.getMonth());
		invoice.setYear(cimsHistoryFin02b.getYear());
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue() + cimsHistoryFin02b.getTotalEbeValue());
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		invoice.setNetRetentionAmount(0.0);
		invoice.setDebitNoteEntry(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(4);
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			df.setRoundingMode(RoundingMode.HALF_UP);
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));
			invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
		}
		if(invoiceType.getSstIncluded().equals("Y")) {
			df.setRoundingMode(RoundingMode.HALF_UP);
			invoice.setSst(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getSstPercentage()/100))));
		}
		
		invoice.setNetAfterSst(invoice.getInvoiceBaseValue()+invoice.getSst());
		df.setRoundingMode(RoundingMode.HALF_UP);
		invoice.setNetAfterSst(Double.valueOf(df.format(invoice.getNetAfterSst())));
		invoice.setTotalInvoiceValue(invoice.getNetAfterSst());
		invoice.setOutstandingAmount(invoice.getNetAfterSst());
		df.setRoundingMode(RoundingMode.HALF_UP) ;
		invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValue()-invoice.getRetentionAmount())));
		invoice.setPaymentStatus("PAYMENT-PENDING");
		invoice.setClinicTypeId(cimsHistoryFin02b.getClinicTypeId());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoice.setSubmittedUserId(userId);
		invoice.setSubmittedDate(createdDate);
		invoiceRepository.save(invoice);
		cimsHistoryFin02b.setFin02bInvNo(invoice.getCode());
		cimsHistoryFin02b.setFin02bInvStatus(invoice.getStatus());
		cimsHistoryFin02bRepository.save(cimsHistoryFin02b);
		return invoice;
	}
	
	public Invoice updateFin02bInvoiceStatus(Invoice invoice) {
		
		if(invoice.getStatus().equals("FOR APPROVAL TO MOH")) {
			invoice.setApproval1Date(createdDate);			
		}else if(invoice.getStatus().equals("APPROVED BY MOH")){
			invoice.setApproval2Date(createdDate);
		}
		invoiceRepository.save(invoice);
		CimsHistoryFin02b cimsHistoryFin02b = cimsHistoryFin02bRepository.findByFin02bInvNo(invoice.getCode());
		cimsHistoryFin02b.setFin02bInvStatus(invoice.getStatus());
		cimsHistoryFin02bRepository.save(cimsHistoryFin02b);
		return invoice;
	}
	
	public List<InvoiceGeneration> getInvoiceFin02bInvInitiate(String month,String year,Integer stateId ,Integer districtId){
		List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
		if(stateId==0) {
			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonthAndYear( month, year);
			
		}else {

			cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonthAndYearAndStateId( month, year,stateId);
		}
//		if(districtId==0) {
//			districtId = null;
//		}
//			
			return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
			
		}
		
 	  public List<InvoiceGeneration> getInvoiceFin02bInvInprogress(String month,String year,Integer stateId ,Integer districtId){
 			List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
 			if(stateId==0) {
 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusNotAndMonthAndYear("APPROVED BY MOH",month, year);
 				
 			}else {
 
 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusNotAndMonthAndYearAndStateId("APPROVED BY MOH", month, year,stateId);
 			}
 			return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
 			
 		}
	  
		  
	  
	  
 	  public List<InvoiceGeneration> getInvoiceFin02bInvApproved(String month,String year,Integer stateId ,Integer districtId){
 			
 		List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
 		if(stateId==0) {
 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusAndMonthAndYear("APPROVED BY MOH",month, year);	
 				
 			}else {
 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusAndMonthAndYearAndStateId( "APPROVED BY MOH",month, year,stateId);
 			
 			}
 				
 			return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
 			
 		}
	  
 		public List<InvoiceGeneration> getInvoiceFin02bInvInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
 			List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
 			if(stateId==0) {
 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonthNotAndYear( month, year);
 				
 			}else {

 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusIsNullAndMonthNotAndYearAndStateId( month, year,stateId);
 			}
// 			if(districtId==0) {
// 				districtId = null;
// 			}
// 				
 				return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
 				
 			}
 			
 	 	  public List<InvoiceGeneration> getInvoiceFin02bInvInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
 	 			List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
 	 			if(stateId==0) {
 	 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusNotAndMonthNotAndYear("APPROVED BY MOH",month, year);
 	 				
 	 			}else {
 	 
 	 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusNotAndMonthNotAndYearAndStateId("APPROVED BY MOH", month, year,stateId);
 	 			}
 	 			return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
 	 			
 	 		}
 		  
 			  
 		  
 		  
 	 	  public List<InvoiceGeneration> getInvoiceFin02bInvApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
 	 			
 	 		List<CimsHistoryFin02b> cimsHistoryFin02bList = new ArrayList<CimsHistoryFin02b>();
 	 		if(stateId==0) {
 	 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusAndMonthNotAndYear("APPROVED BY MOH",month, year);	
 	 				
 	 			}else {
 	 				cimsHistoryFin02bList = cimsHistoryFin02bRepository.findByFin02bInvStatusAndMonthNotAndYearAndStateId( "APPROVED BY MOH",month, year,stateId);
 	 			
 	 			}
 	 				
 	 			return getUniqueInvoiceGeneration(cimsHistoryFin02bList);
 	 			
 	 		}
	  
	  
		public List<InvoiceGeneration> getUniqueInvoiceGeneration(List<CimsHistoryFin02b> cimsHistoryFin02bList){
			List<InvoiceGeneration> invoiceGenerationList = new ArrayList<InvoiceGeneration>();		
				
			for (CimsHistoryFin02b cimsHistoryFin02b : cimsHistoryFin02bList) {
				   Integer clinicCount= clinicRepository.findClinicCountByDistrictId(cimsHistoryFin02b.getDistrictId());
				   var invoiceGenerationUnique = invoiceGenerationList.stream().filter(invGen-> cimsHistoryFin02b.getStateId() == invGen.getStateId() && cimsHistoryFin02b.getCircleId() == invGen.getCircleId() && cimsHistoryFin02b.getDistrictId() == invGen.getDistrictId()).findAny().orElse(null);
					if(invoiceGenerationUnique ==  null) {

						var invoiceGeneration = new InvoiceGeneration();
						invoiceGeneration.setStateId(cimsHistoryFin02b.getStateId());
						invoiceGeneration.setStateName(cimsHistoryFin02b.getStateName());
						invoiceGeneration.setCircleId(cimsHistoryFin02b.getCircleId());
						invoiceGeneration.setCircleName(cimsHistoryFin02b.getCircleName());
						invoiceGeneration.setDistrictId(cimsHistoryFin02b.getDistrictId());
						invoiceGeneration.setDistrictName(cimsHistoryFin02b.getDistrictName());
						invoiceGeneration.setClinicCount(clinicCount);
						invoiceGeneration.setEquipmentCount((int) (long)cimsHistoryFin02b.getTotalEbe());
						invoiceGeneration.setAmount(cimsHistoryFin02b.getTotalEbeValue());
						invoiceGenerationList.add(invoiceGeneration);
					}else {
						invoiceGenerationUnique.setClinicCount(clinicCount);
						invoiceGenerationUnique.setEquipmentCount(invoiceGenerationUnique.getEquipmentCount()+(int) (long)cimsHistoryFin02b.getTotalEbe());
						invoiceGenerationUnique.setAmount(invoiceGenerationUnique.getAmount()+cimsHistoryFin02b.getTotalEbeValue());
						invoiceGenerationList.stream().map(invGen-> (cimsHistoryFin02b.getStateId() == invGen.getStateId() && cimsHistoryFin02b.getCircleId() == invGen.getCircleId() && cimsHistoryFin02b.getDistrictId() == invGen.getDistrictId())? invoiceGenerationUnique : invGen).collect(Collectors.toList());
					}	
				}
			
		return invoiceGenerationList;
		}

}
