package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.CimsHistoryFin01Distinct;
import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.CimsHistoryFin02aDistinct;
import com.example.demo.EntityModel.Clinic;
import com.example.demo.EntityModel.CostCenterCode;
import com.example.demo.EntityModel.Fin01;
import com.example.demo.EntityModel.Fin06Distinct;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin07Distinct;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin01Mapping;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.repository.CimsHistoryFin01Repository;
import com.example.demo.repository.ClinicRepository;
import com.example.demo.repository.CostCenterCodeRepository;
import com.example.demo.repository.Fin06Repository;
import com.example.demo.repository.Fin01Repository;
import com.example.demo.repository.Fin01MappingRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class Fin01Service {
	
	//@CreationTimestamp
	//private LocalDate createdDate = LocalDate.now();
	
	int totalSelectedEquip = 0;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private CimsHistoryFin01Repository cimsHistoryFin01Repository;
	
	@Autowired
	private Fin06Repository fin06Repository;
	
	@Autowired
	private Fin01Repository fin01Repository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private Fin01MappingRepository fin01MappingRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private CostCenterCodeRepository costCenterCodeRepository;
	
	
	public String invoiceQuater(String month) {
		String quater = null;
		if(month.equals("1") || month.equals("2") || month.equals("3")){ quater = "Q1";}
		else if(month.equals("4") || month.equals("5") || month.equals("6")){ quater = "Q2";}
		else if(month.equals("7") || month.equals("8") || month.equals("9")){ quater = "Q3";}
		else if(month.equals("10") || month.equals("11") || month.equals("12")){ quater = "Q4";}
		return quater;
	}
	public List<Fin01> fin01ApprovedList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin01> fin01ApprovedList = new ArrayList<Fin01>();
		if(stateId == 0 && districtId == 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin01ApprovedList.forEach(Fin01 -> {
			Fin01.setFin06(null);
		});
		return fin01ApprovedList;
	}
	
	public List<Fin01> fin01ApprovedListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin01> fin01ApprovedList = new ArrayList<Fin01>();
		if(stateId == 0 && districtId == 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin01ApprovedList.forEach(Fin01 -> {
			Fin01.setFin06(null);
		});
		return fin01ApprovedList;
	}
	
	public List<Fin01> fin01InProgressList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin01> fin01InProgressList = new ArrayList<Fin01>();
		if(stateId == 0 && districtId == 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin01InProgressList.forEach(Fin01 -> {
			Fin01.setFin06(null);
		});
		return fin01InProgressList;
	}
	
	public List<Fin01> fin01InProgressListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin01> fin01InProgressList = new ArrayList<Fin01>();
		if(stateId == 0 && districtId == 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin01InProgressList = fin01Repository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin01InProgressList.forEach(Fin01 -> {
			Fin01.setFin06(null);
		});
		return fin01InProgressList;
	}
	
	
	public Boolean isFin06Created(Integer clinicId) {
		LocalDate createdDate = LocalDate.now();
		String month = String.valueOf(createdDate.getMonthValue());
		String year = String.valueOf(createdDate.getYear());
		Fin06 fin06 = fin06Repository.findByClinicIdAndMonthAndYear(clinicId,month,year);
		if(fin06 != null) { return true; }
		else { return false; }
	}
	
	public List<Fin06> getFin06ForFin01Create(Integer districtId, Integer clinicTypeId, String month , String year){
		List<Fin06> fin06List = fin06Repository.findByDistrictIdAndClinicTypeIdAndStatusAndMonthAndYearAndFin01StatusIsNull(districtId, clinicTypeId, "APPROVED BY MOH", String.valueOf(month), String.valueOf(year));
		Comparator<Fin06> compareByClinicId = (Fin06 o1, Fin06 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		Collections.sort(fin06List, compareByClinicId);
		return fin06List;
	}
	
	
	public Fin06 addFin06( List<CimsHistoryFin01> CimsHistoryFin01List,int userId) {
		LocalDate createdDate = LocalDate.now();
		Fin06 fin06 =new Fin06();
		totalSelectedEquip = 0;
		CimsHistoryFin01 cims = CimsHistoryFin01List.get(0);
		fin06.setCode("FIN06"+ "-" +cims.getClinic().getClinicCode()+ "-" +cims.getMonth()+"-"+cims.getYear());
		fin06.setStateId(cims.getStateId());
		fin06.setDistrictId(cims.getDistrictId());
		fin06.setClinicTypeId(cims.getClinicTypeId());
		fin06.setClinicId(cims.getClinicId());
		fin06.setMonth(cims.getMonth());
		fin06.setYear(cims.getYear());
		fin06.setTotalAmount(0.0);
		fin06.setStatus("IN INTERNAL APPROVAL");
		fin06Repository.save(fin06);
		CimsHistoryFin01List.forEach(cimsData->{
			if(cimsData.getIsFin06Created().equals("Y")) {
				fin06.setTotalAmount(fin06.getTotalAmount()+cimsData.getPurchaseAmount());
				cimsData.setFin06RefNo(fin06.getCode());
				cimsData.setFin06Status("IN INTERNAL APPROVAL");			
				cimsHistoryFin01Repository.save(cimsData);
				totalSelectedEquip++;
			} else if(cimsData.getIsFin06Created().equals("N")) {
				cimsHistoryFin01Repository.save(cimsData);
			}
		});
		fin06.setTotalAmount(Double.valueOf(df.format(fin06.getTotalAmount())));
		fin06.setTotalEquipmentCount(totalSelectedEquip);
		fin06.setSubmittedUserId(userId);
		fin06.setSubmittedDate(createdDate);
		fin06Repository.save(fin06);
		return fin06;
	}
	
	public Fin01 addFin01(List<Fin06> fin06,int userId) {
		LocalDate createdDate = LocalDate.now();
		Fin06 fin06Data = fin06.get(0);
		String prvZero = "0";
		List<Fin01> fin01Check = fin01Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYears(fin06Data.getDistrictId(), fin06Data.getClinicTypeId(), fin06Data.getMonth(), fin06Data.getYear());
		if(fin01Check.size() !=0) {
			Fin01 fin01 = new Fin01();
			fin01.setClinicTypeId(fin06Data.getClinicTypeId());
			CostCenterCode costCenterCode = costCenterCodeRepository.findByDistrictIdAndClinicTypeId(fin06Data.getDistrictId(), fin06Data.getClinicTypeId());
		
			if(fin06Data.getMonth().length() == 1) {
				prvZero = "00";
			}
			
			fin01.setCode("Fin01"+ "-" +fin06Data.getDistrictCode() + costCenterCode.getCostCenterName()+ "-" + prvZero +fin06Data.getMonth()+ "-" +fin06Data.getYear()+ "-" +fin01Check.size());
			fin01.setStateId(fin06Data.getStateId());
			fin01.setDistrictId(fin06Data.getDistrictId());
			fin01.setClinicTypeId(fin06Data.getClinicTypeId());
			fin01.setMonth(fin06Data.getMonth());
			fin01.setYear(fin06Data.getYear());
			fin01.setStatus("IN INTERNAL APPROVAL");
			fin01.setTotalEquipmentCount(0);
			fin01.setTotalAmount(0.0);
			fin01.setSubmittedUserId(userId);
			fin01.setSubmittedDate(createdDate);
			fin01Repository.save(fin01);
			fin06.forEach(fin06Sub -> {
				fin01.setTotalEquipmentCount(fin01.getTotalEquipmentCount() + fin06Sub.getTotalEquipmentCount());
				fin01.setTotalAmount(fin01.getTotalAmount() + fin06Sub.getTotalAmount());
				fin06Sub.setFin01RefNo(fin01.getCode());
				fin06Sub.setFin01Status("IN INTERNAL APPROVAL");
				fin06Repository.save(fin06Sub);
			});
			fin01.setTotalAmount(Double.valueOf(df.format(fin01.getTotalAmount())));
			fin01Repository.save(fin01);
			List <CimsHistoryFin01> cimsHistory = cimsHistoryFin01Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(fin01.getDistrictId(), fin01.getClinicTypeId(), fin06Data.getMonth(), fin06Data.getYear());
			cimsHistory.forEach(cimsData -> {
				if(cimsData.getFin06Status() != null) {
				cimsData.setFin01RefNo(fin01.getCode());
				cimsData.setFin01Status("IN INTERNAL APPROVAL");
				cimsHistoryFin01Repository.save(cimsData);
				}
			});
			
			return fin01;
		}
		else {
		Fin01 fin01 = new Fin01();
		fin01.setClinicTypeId(fin06Data.getClinicTypeId());
		CostCenterCode costCenterCode = costCenterCodeRepository.findByDistrictIdAndClinicTypeId(fin06Data.getDistrictId(), fin06Data.getClinicTypeId());
		if(fin06Data.getMonth().length() == 1) {
			prvZero = "00";
		}
		fin01.setCode("Fin01"+ "-" +fin06Data.getDistrictCode() +costCenterCode.getCostCenterName()+ "-" + prvZero + fin06Data.getMonth()+ "-" +fin06Data.getYear());
		fin01.setStateId(fin06Data.getStateId());
		fin01.setDistrictId(fin06Data.getDistrictId());
		fin01.setClinicTypeId(fin06Data.getClinicTypeId());
		fin01.setMonth(fin06Data.getMonth());
		fin01.setYear(fin06Data.getYear());
		fin01.setStatus("IN INTERNAL APPROVAL");
		fin01.setTotalEquipmentCount(0);
		fin01.setTotalAmount(0.0);
		fin01.setSubmittedUserId(userId);
		fin01.setSubmittedDate(createdDate);
		fin01Repository.save(fin01);
		fin06.forEach(fin06Sub -> {
			fin01.setTotalEquipmentCount(fin01.getTotalEquipmentCount() + fin06Sub.getTotalEquipmentCount());
			fin01.setTotalAmount(fin01.getTotalAmount() + fin06Sub.getTotalAmount());
			fin06Sub.setFin01RefNo(fin01.getCode());
			fin06Sub.setFin01Status("IN INTERNAL APPROVAL");
			fin06Repository.save(fin06Sub);
		});
		fin01.setTotalAmount(Double.valueOf(df.format(fin01.getTotalAmount())));
		fin01Repository.save(fin01);
		List <CimsHistoryFin01> cimsHistory = cimsHistoryFin01Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(fin01.getDistrictId(), fin01.getClinicTypeId(), fin06Data.getMonth(), fin06Data.getYear());
		cimsHistory.forEach(cimsData -> {
			if(cimsData.getFin06Status() != null) {
			cimsData.setFin01RefNo(fin01.getCode());
			cimsData.setFin01Status("IN INTERNAL APPROVAL");
			cimsHistoryFin01Repository.save(cimsData);
			}
		});
		
		return fin01;
		}
	}
	
	public Invoice addFin01Invoice(List<Fin06> fin06,int userId) {
		LocalDate createdDate = LocalDate.now();
		Invoice invoice = new Invoice();
		Fin06 fin06Data = fin06.get(0);
		String clinicCatCode = ""; 
		if(fin06Data.getClinicTypeCode().equals("PKD")) { clinicCatCode = "K"; }
		else if(fin06Data.getClinicTypeCode().equals("PPD")) { clinicCatCode = "P"; }
		List<Invoice> invoiceCheck = invoiceRepository.findByInvoiceTypeIdAndDistrictIdAndClinicTypeIdAndMonthAndYear(1,fin06Data.getDistrictId(),
				fin06Data.getClinicTypeId(),fin06Data.getMonth(),fin06Data.getYear());
		if(invoiceCheck.size() == 0) {
		invoice.setCode("1-"+fin06Data.getStateCode()+"-"+fin06Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin06Data.getMonth()+"-"+fin06Data.getYear());
		invoice.setInvoiceNo("1-"+fin06Data.getStateCode()+"-"+fin06Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin06Data.getMonth()+"-"+fin06Data.getYear());
		invoice.setInvoiceTypeId(1);
		invoice.setStateId(fin06Data.getStateId());
		invoice.setDistrictId(fin06Data.getDistrictId());
		invoice.setMonth(fin06Data.getMonth());
		invoice.setYear(fin06Data.getYear());
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoiceRepository.save(invoice);
		fin06.forEach(fin06Sub -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin06Sub.getTotalAmount()); 
			fin06Sub.setFin01InvNo(invoice.getInvoiceNo());
			fin06Sub.setFin01InvStatus("IN INTERNAL APPROVAL");
			fin06Repository.save(fin06Sub);
		});
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		invoice.setNetRetentionAmount(0.0);
		invoice.setDebitNoteEntry(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(1); 
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));
			invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));
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
		invoice.setClinicTypeId(fin06Data.getClinicTypeId());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoice.setSubmittedUserId(userId);
		invoice.setSubmittedDate(createdDate);
		invoiceRepository.save(invoice);
		Fin01Mapping fin01Mapping = new Fin01Mapping();
		fin01Mapping.setFin01RefNo(fin06Data.getFin01RefNo());
		fin01Mapping.setInvoiceRefNo(invoice.getInvoiceNo());
		fin01MappingRepository.save(fin01Mapping);
		List<CimsHistoryFin01> cimsHistory = cimsHistoryFin01Repository.findByFin01RefNo(fin01Mapping.getFin01RefNo());
		cimsHistory.forEach(cims -> {
			cims.setFin01InvStatus("IN INTERNAL APPROVAL");
			cimsHistoryFin01Repository.save(cims);
		});
		return invoice;
	}
		else {
			invoice.setCode("1-"+fin06Data.getStateCode()+"-"+fin06Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin06Data.getMonth()+"-"+fin06Data.getYear()+"-"+invoiceCheck.size());
			invoice.setInvoiceNo("1-"+fin06Data.getStateCode()+"-"+fin06Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin06Data.getMonth()+"-"+fin06Data.getYear()+"-"+invoiceCheck.size());
			invoice.setInvoiceTypeId(1);
			invoice.setStateId(fin06Data.getStateId());
			invoice.setDistrictId(fin06Data.getDistrictId());
			invoice.setMonth(fin06Data.getMonth());
			invoice.setYear(fin06Data.getYear());
			invoice.setStatus("IN INTERNAL APPROVAL");
			invoice.setInvoiceBaseValue(0.0);
			invoiceRepository.save(invoice);
			fin06.forEach(fin06Sub -> {
				invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin06Sub.getTotalAmount()); 
				fin06Sub.setFin01InvNo(invoice.getInvoiceNo());
				fin06Sub.setFin01InvStatus("IN INTERNAL APPROVAL");
				fin06Repository.save(fin06Sub);
			});
			invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
			invoice.setSst(0.0);
			invoice.setRetentionAmount(0.0);
			invoice.setDebitNoteEntry(0.0);
			InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(1); 
			if(invoiceType.getRetentionAvailable().equals("Y")) {
				invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));
				invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));
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
			invoice.setClinicTypeId(fin06Data.getClinicTypeId());
			invoice.setQuater(invoiceQuater(invoice.getMonth()));
			invoice.setSubmittedUserId(userId);
			invoice.setSubmittedDate(createdDate);
			invoiceRepository.save(invoice);
			Fin01Mapping fin01Mapping = new Fin01Mapping();
			fin01Mapping.setFin01RefNo(fin06Data.getFin01RefNo());
			fin01Mapping.setInvoiceRefNo(invoice.getInvoiceNo());
			fin01MappingRepository.save(fin01Mapping);
			List<CimsHistoryFin01> cimsHistory = cimsHistoryFin01Repository.findByFin01RefNo(fin01Mapping.getFin01RefNo());
			cimsHistory.forEach(cims -> {
				cims.setFin01InvStatus("IN INTERNAL APPROVAL");
				cimsHistoryFin01Repository.save(cims);
			});
			return invoice;
		}
	
}
	
	
//	public Iterable<CimsHistoryFin01> getFin06CreateList() {
//		Set<CimsHistoryFin01Distinct> uniqueSet = new HashSet<>();
//		List<CimsHistoryFin01> uniqueCimsHistoryFin01 = new ArrayList<CimsHistoryFin01>();
//		List<CimsHistoryFin01>  cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findAll();
//		cimsHistoryFin01List.forEach(e->{
//			if(e.getIsFin06Created() == null || e.getIsFin06Created().equals("N")) { 
//			CimsHistoryFin01Distinct CimsTransactionUnique = new CimsHistoryFin01Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin06Status());
//			if(uniqueSet.add(CimsTransactionUnique)) {
//				uniqueCimsHistoryFin01.add(e);
//			}
//			}
//		});
//
//		return uniqueCimsHistoryFin01;
//	}
	public Iterable<CimsHistoryFin01> getFin06CreateList(Integer stateId, Integer districtId) {
		LocalDate createdDate = LocalDate.now();
		Set<CimsHistoryFin01Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin01> uniqueCimsHistoryFin01 = new ArrayList<CimsHistoryFin01>();
		List<CimsHistoryFin01>  cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndMonthAndYear(String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0 ) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndStateIdAndMonthAndYear(stateId,String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndStateIdAndDistrictIdAndMonthAndYear(stateId,districtId,String.valueOf(createdDate.getMonthValue()-1 ),String.valueOf(createdDate.getYear()));	
		}
		cimsHistoryFin01List.forEach(e->{
			if(e.getFin06Status() == null) { 
				CimsHistoryFin01Distinct CimsTransactionUnique = new CimsHistoryFin01Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin06Status());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueCimsHistoryFin01.add(e);
			}
			}
		});

		return cimsHistoryFin01List;
	}
	
	public Iterable<CimsHistoryFin01> getFin06CreateListForOlder(Integer stateId, Integer districtId) {
		LocalDate createdDate = LocalDate.now();
		Set<CimsHistoryFin01Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin01> uniqueCimsHistoryFin01 = new ArrayList<CimsHistoryFin01>();
		List<CimsHistoryFin01>  cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndMonthNotAndYear(String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0 ) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndStateIdAndMonthNotAndYear(stateId,String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByFin06StatusIsNullAndStateIdAndDistrictIdAndMonthNotAndYear(stateId,districtId,String.valueOf(createdDate.getMonthValue()-1 ),String.valueOf(createdDate.getYear()));	
		}
		cimsHistoryFin01List.forEach(e->{
			if(e.getFin06Status() == null) { 
				CimsHistoryFin01Distinct CimsTransactionUnique = new CimsHistoryFin01Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin06Status());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueCimsHistoryFin01.add(e);
			}
			}
		});

		return cimsHistoryFin01List;
	}
	
	
	
	public List<Fin06> fin06InProgressList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin06> fin06InProgressList = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin06InProgressList.forEach(fin06 -> {
			fin06.setCimsHistoryFin01(null);
		});
		return fin06InProgressList;
	}
	
	public List<Fin06> fin06InProgressListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin06> fin06InProgressList = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1 ));
		} else if(stateId != 0 && districtId == 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin06InProgressList = fin06Repository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin06InProgressList.forEach(Fin06 -> {
			Fin06.setCimsHistoryFin01(null);
		});
		return fin06InProgressList;
	}
	
	public List<Fin06> fin06ApprovedList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin06> fin06ApprovedList = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin06ApprovedList.forEach(Fin06 -> {
			Fin06.setCimsHistoryFin01(null);
		});
		return fin06ApprovedList;
	}
	
	public List<Fin06> fin06ApprovedListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin06> fin06ApprovedList = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin06ApprovedList = fin06Repository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin06ApprovedList.forEach(Fin06 -> {
			Fin06.setCimsHistoryFin01(null);
		});
		return fin06ApprovedList;
	}
	
//	public Iterable<CimsHistoryFin01> getFin06CreateList(Integer stateId) {
//		Set<CimsHistoryFin01Distinct> uniqueSet = new HashSet<>();
//		List<CimsHistoryFin01> uniqueCimsHistoryFin01 = new ArrayList<CimsHistoryFin01>();
//		List<CimsHistoryFin01>  cimsHistoryFin01List = (List<CimsHistoryFin01>) cimsHistoryFin01Repository.findByStateId(stateId);
//		cimsHistoryFin01List.forEach(e->{
//			if(e.getIsFin06Created() == null || e.getIsFin06Created().equals("N")) { 
//			CimsHistoryFin01Distinct CimsTransactionUnique = new CimsHistoryFin01Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin06Status());
//			if(uniqueSet.add(CimsTransactionUnique)) {
//				uniqueCimsHistoryFin01.add(e);
//			}
//			}
//		});
//
//		return uniqueCimsHistoryFin01;
//	}
	
	public Fin06 updateFin06Status(Fin06 Fin06) {
		LocalDate createdDate = LocalDate.now();
		
		if(Fin06.getStatus().equals("FOR APPROVAL TO MOH")) {
			Fin06.setApproval1Date(createdDate);			
		}else if(Fin06.getStatus().equals("APPROVED BY MOH")){
			Fin06.setApproval2Date(createdDate);
		}
		fin06Repository.save(Fin06);
		List<CimsHistoryFin01> cimsHistory =  cimsHistoryFin01Repository.findByFin06RefNo(Fin06.getCode());
		cimsHistory.forEach(cims -> {
			cims.setFin06Status(Fin06.getStatus());
			cimsHistoryFin01Repository.save(cims);
		});
		
		return Fin06;
	}

//	public List<Fin06> getFin01CreateList() {
//		Set<Fin06Distinct> uniqueSet = new HashSet<>();
//		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
//		List<Fin06>  fin06List = (List<Fin06>) fin06Repository.findAll();
//		
//		fin06List.forEach(e->{
//			if(e.getStatus() != null) {
//			if(e.getStatus().equals("APPROVED BY MOH") && e.getFin01Status() == null) {
//			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
//			if(uniqueSet.add(CimsTransactionUnique)) {
//				uniqueFin06List.add(e);
//			}			
//			}
//			}
//		});
//
//		return uniqueFin06List;
//	}
	//small fin01 create list
	public List<Fin06> getFin01CreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin06Distinct> uniqueSet = new HashSet<>();
		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
		List<Fin06> fin06List = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));	
		} else if (stateId != 0 && districtId != 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
				
		fin06List.forEach(e->{
			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin06List.add(e);
			}			
		});

		return uniqueFin06List;
	}
	
	public List<Fin06> getFin01CreateListOlders(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin06Distinct> uniqueSet = new HashSet<>();
		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
		List<Fin06> fin06List = new ArrayList<Fin06>();
		if(stateId == 0 && districtId == 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));	
		} else if (stateId != 0 && districtId != 0) {
			fin06List = (List<Fin06>) fin06Repository.findByStatusAndFin01StatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
				
		fin06List.forEach(e->{
			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin06List.add(e);
			}			
		});

		return uniqueFin06List;
	}
	
	
	public List<Fin06> getFIN01CreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin06Distinct> uniqueSet = new HashSet<>();
		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
		List<Fin06> fin06List = new ArrayList<Fin06>(); 
		if(stateId == 0 && districtId == 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		fin06List.forEach(e->{
			if(e.getFin01Status() != null) {
			if(e.getFin01InvStatus() == null && e.getFin01Status().equals("APPROVED BY MOH") ) {
			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin06List.add(e);
			}			
			}
			}
		});
		return uniqueFin06List;
	}
	
	public List<Fin06> getFIN01CreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin06Distinct> uniqueSet = new HashSet<>();
		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
		List<Fin06> fin06List = new ArrayList<Fin06>(); 
		if(stateId == 0 && districtId == 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			fin06List = fin06Repository.findByFin06InvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		fin06List.forEach(e->{
			if(e.getFin01Status() != null) {
			if(e.getFin01InvStatus() == null && e.getFin01Status().equals("APPROVED BY MOH") ) {
			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin06List.add(e);
			}			
			}
			}
		});
		return uniqueFin06List;
	}
	
	
	
//	public List<Fin06> getFIN01CreateList() {
//		Set<Fin06Distinct> uniqueSet = new HashSet<>();
//		List<Fin06> uniqueFin06List = new ArrayList<Fin06>();
//		List<Fin06>  fin06List = (List<Fin06>) fin06Repository.findAll();
//		
//		fin06List.forEach(e->{
//			if(e.getFin01Status() != null) {
//			if(e.getFin01InvStatus() == null && e.getFin01Status().equals("APPROVED BY MOH") ) {
//			Fin06Distinct CimsTransactionUnique = new Fin06Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
//			if(uniqueSet.add(CimsTransactionUnique)) {
//				uniqueFin06List.add(e);
//			}			
//			}
//			}
//		});
//
//		return uniqueFin06List;
//	}
	
	
//	public List<Fin01> fin01ApprovedList(String status,Integer stateId,Integer districtId){
//		LocalDate date = LocalDate.now();
//		List<Fin01> fin01ApprovedList = new ArrayList<Fin01>();
//		if(stateId == 0 && districtId == 0 ) {
//			fin01ApprovedList = fin01Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
//		} else if(stateId != 0 && districtId == 0 ) {
//			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
//		} else if(stateId != 0 && districtId != 0 ) {
//			fin01ApprovedList = fin01Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
//		}
//		fin01ApprovedList.forEach(Fin01 -> {
//			Fin01.setFin06(null);
//		});
//		return fin01ApprovedList;
//	}
	
	public Fin01 updateFin01Status(Fin01 Fin01) {
		LocalDate createdDate = LocalDate.now();
		if(Fin01.getStatus().equals("FOR APPROVAL TO MOH")) {
			Fin01.setApproval1Date(createdDate);			
		}else if(Fin01.getStatus().equals("APPROVED BY MOH")){
			Fin01.setApproval2Date(createdDate);
		}
		fin01Repository.save(Fin01);
		List<Fin06> Fin06 = fin06Repository.findByFin01RefNo(Fin01.getCode());
		Fin06.forEach(fin06 -> {
			fin06.setFin01Status(Fin01.getStatus());
			fin06Repository.save(fin06);
		});
		List<CimsHistoryFin01> cimsHistory =  cimsHistoryFin01Repository.findByFin01RefNo(Fin01.getCode());
		
		cimsHistory.forEach(cims -> {
			cims.setFin01Status(Fin01.getStatus());
			cimsHistoryFin01Repository.save(cims);
		});
		return Fin01;
	}
	
	public Invoice updateFin01InvoiceStatus(Invoice Invoice) {
		LocalDate createdDate = LocalDate.now();
		if(Invoice.getStatus().equals("FOR APPROVAL TO MOH")) {
			Invoice.setApproval1Date(createdDate);			
		}else if(Invoice.getStatus().equals("APPROVED BY MOH")){
			Invoice.setApproval2Date(createdDate);
		}
		invoiceRepository.save(Invoice);
		List<Fin06> Fin06 = fin06Repository.findByFin01InvNo(Invoice.getInvoiceNo());
		Fin06.forEach( fin06 -> {
			fin06.setFin01InvStatus(Invoice.getStatus());
			fin06Repository.save(fin06);
		});
		 Fin01Mapping fin01Mapping = fin01MappingRepository.findByInvoiceRefNo(Invoice.getInvoiceNo());
		 List<CimsHistoryFin01> cimsHistory =  cimsHistoryFin01Repository.findByFin01RefNo(fin01Mapping.getFin01RefNo());
		 cimsHistory.forEach(cims -> {
			 cims.setFin01InvStatus(Invoice.getStatus());
			 cimsHistoryFin01Repository.save(cims);
		 });
		
		return Invoice;
	}
	
	public List<Fin06> fin06ListForInvoiceApproval(String invoiceNo){
		Fin01Mapping fin01Mapping = fin01MappingRepository.findByInvoiceRefNo(invoiceNo);
		Fin01 Fin01 = fin01Repository.findByCode(fin01Mapping.getFin01RefNo());
		List<Fin06> Fin06 = fin06Repository.findByDistrictIdAndClinicTypeId(Fin01.getDistrictId(), Fin01.getClinicTypeId());
		System.out.println(Fin06);
		return  Fin06;
	}
	
	public List<InvoiceGeneration> getInvoiceFin06Initiate(String month,String year,Integer stateId ,Integer districtId){
//		System.out.println(stateId +"==="+districtId);
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
//		System.out.println(stateId +"==="+districtId);
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndIsFin06Created( month, year, null,stateId,districtId);
//		System.out.println(stateId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
   public List<InvoiceGeneration> getInvoiceFin06Inprogress(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndIsFin06CreatedAndFin06StatusNot(month, year,"Y","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

   
   public List<InvoiceGeneration> getInvoiceFin06Approved(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndIsFin06CreatedAndFin06Status(month, year,"Y","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);		
	}
   
	public List<InvoiceGeneration> getInvoiceFin01Initiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin06Status( month, year, "APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
   public List<InvoiceGeneration> getInvoiceFin01Inprogress(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin06StatusAndFin01StatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

   
   public List<InvoiceGeneration> getInvoiceFin01Approved(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin06StatusAndFin01Status(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
   
   

	public List<InvoiceGeneration> getInvoiceFin01InvInitiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin01Status( month, year, "APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
  public List<InvoiceGeneration> getInvoiceFin01InvInprogress(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin01StatusAndFin01InvStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

  
  public List<InvoiceGeneration> getInvoiceFin01InvApproved(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByMonthAndYearAndFin01StatusAndFin01InvStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);		
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
  
  
  public List<InvoiceGeneration> getInvoiceFin06InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
//		System.out.println(stateId +"==="+districtId);
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
//		System.out.println(stateId +"==="+districtId);
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndIsFin06Created( month, year, null,stateId,districtId);
//		System.out.println(stateId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
 public List<InvoiceGeneration> getInvoiceFin06InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndIsFin06CreatedAndFin06StatusNot(month, year,"Y","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

 
 public List<InvoiceGeneration> getInvoiceFin06ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndIsFin06CreatedAndFin06Status(month, year,"Y","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);		
	}
 
	public List<InvoiceGeneration> getInvoiceFin01InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin06Status( month, year, "APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
 public List<InvoiceGeneration> getInvoiceFin01InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin06StatusAndFin01StatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

 
 public List<InvoiceGeneration> getInvoiceFin01ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin06StatusAndFin01Status(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
 
 

	public List<InvoiceGeneration> getInvoiceFin01InvInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin01Status( month, year, "APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}
	
public List<InvoiceGeneration> getInvoiceFin01InvInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin01StatusAndFin01InvStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}


public List<InvoiceGeneration> getInvoiceFin01InvApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin01> cimsHistoryFin01List = new ArrayList<CimsHistoryFin01>();
		cimsHistoryFin01List = cimsHistoryFin01Repository.findByNotMonthAndYearAndFin01StatusAndFin01InvStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);		
		return getUniqueInvoiceGeneration(cimsHistoryFin01List);
		
	}

	public List<InvoiceGeneration> getUniqueInvoiceGeneration(List<CimsHistoryFin01> cimsHistoryFin01List){
		List<InvoiceGeneration> invoiceGenerationList = new ArrayList<InvoiceGeneration>();
		Map<Integer, Double> amountList =cimsHistoryFin01List.stream().collect(Collectors.groupingBy(CimsHistoryFin01::getClinicId,Collectors. summingDouble(CimsHistoryFin01::getPurchaseAmount)));
		
		Map<Integer, Long> countList =cimsHistoryFin01List.stream().collect(Collectors.groupingBy(CimsHistoryFin01::getClinicId,Collectors.counting()));
		amountList.forEach((key,value)->{ 
				
				countList.forEach((key1,value1)->{ 
					
					if(key==key1) {
						Clinic clinic = clinicRepository.findByClinicId(key);	
						var invoiceGenerationUnique = invoiceGenerationList.stream().filter(invGen-> clinic.getStateId() == invGen.getStateId() && clinic.getCircleId() == invGen.getCircleId() && clinic.getDistrictId() == invGen.getDistrictId()).findAny().orElse(null);
						if(invoiceGenerationUnique ==  null) {
							var invoiceGeneration = new InvoiceGeneration();
							invoiceGeneration.setStateId(clinic.getStateId());
							invoiceGeneration.setStateName(clinic.getStateName());
							invoiceGeneration.setCircleId(clinic.getCircleId());
							invoiceGeneration.setCircleName(clinic.getCircleName());
							invoiceGeneration.setDistrictId(clinic.getDistrictId());
							invoiceGeneration.setDistrictName(clinic.getDistrictName());
							invoiceGeneration.setClinicCount(1);
							invoiceGeneration.setEquipmentCount((int) (long)value1);
							invoiceGeneration.setAmount(value);
							invoiceGenerationList.add(invoiceGeneration);
						}else {
							
							invoiceGenerationUnique.setClinicCount(invoiceGenerationUnique.getClinicCount()+1);
							invoiceGenerationUnique.setEquipmentCount(invoiceGenerationUnique.getEquipmentCount()+(int) (long)value1);
							invoiceGenerationUnique.setAmount(invoiceGenerationUnique.getAmount()+value);
							invoiceGenerationList.stream().map(invGen-> (clinic.getStateId() == invGen.getStateId() && clinic.getCircleId() == invGen.getCircleId() && clinic.getDistrictId() == invGen.getDistrictId())? invoiceGenerationUnique : invGen).collect(Collectors.toList());
						}				
						
					}
				});
			});
	return invoiceGenerationList;
	}


}
