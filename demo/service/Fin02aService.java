package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import com.example.demo.EntityModel.Clinic;
import java.util.Collections;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.ClinicRepository;
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

import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.CimsHistoryFin02aDistinct;
import com.example.demo.EntityModel.CostCenterCode;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin07Distinct;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin03aMapping;
import com.example.demo.repository.CimsHistoryFin02aRepository;
import com.example.demo.repository.CostCenterCodeRepository;
import com.example.demo.repository.Fin07Repository;
import com.example.demo.repository.Fin03aRepository;
import com.example.demo.repository.Fin03aMappingRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class Fin02aService {
	
	@CreationTimestamp
	private LocalDate createdDate = LocalDate.now();
	
	int totalSelectedEquip = 0;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private CostCenterCodeRepository costCenterCodeRepository;
	
	@Autowired
	private CimsHistoryFin02aRepository cimsHistoryFin02aRepository;
	
	@Autowired
	private Fin07Repository fin07Repository;
	
	@Autowired
	private Fin03aRepository fin03aRepository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private Fin03aMappingRepository fin03aMappingRepository;
	
	public String invoiceQuater(String month) {
		String quater = null;
		if(month.equals("1") || month.equals("2") || month.equals("3")){ quater = "Q1";}
		else if(month.equals("4") || month.equals("5") || month.equals("6")){ quater = "Q2";}
		else if(month.equals("7") || month.equals("8") || month.equals("9")){ quater = "Q3";}
		else if(month.equals("10") || month.equals("11") || month.equals("12")){ quater = "Q4";}
		return quater;
	}
	
	public Invoice addFin02aInvoice(List<Fin03a> fin03a,int userId) {
		Invoice invoice = new Invoice();
		Fin03a fin03aData = fin03a.get(0);
		String clinicCatCode = ""; 
		if(fin03aData.getClinicTypeCode().equals("PKD")) { clinicCatCode = "K"; }
		else if(fin03aData.getClinicTypeCode().equals("PPD")) { clinicCatCode = "P"; }
		List<Invoice> invoiceCheck = invoiceRepository.findByInvoiceTypeIdAndDistrictIdAndClinicTypeIdAndMonthAndYear(2,fin03aData.getDistrictId(),
				fin03aData.getClinicTypeId(),fin03aData.getMonth(),fin03aData.getYear());
		if(invoiceCheck.size() == 0) {
			invoice.setCode("2A-"+fin03aData.getStateCode()+"-"+fin03aData.getDistrictCode()+"-"+clinicCatCode+"-"+fin03aData.getMonth()+"-"+fin03aData.getYear());
		} else {
			invoice.setCode("2A-"+fin03aData.getStateCode()+"-"+fin03aData.getDistrictCode()+"-"+clinicCatCode+"-"+fin03aData.getMonth()+"-"+fin03aData.getYear()+"-"+invoiceCheck.size());
		}
			invoice.setInvoiceNo(invoice.getCode());
			invoice.setInvoiceTypeId(2);
			invoice.setStateId(fin03aData.getStateId());
			invoice.setDistrictId(fin03aData.getDistrictId());
			invoice.setMonth(fin03aData.getMonth());
			invoice.setYear(fin03aData.getYear());
			invoice.setStatus("IN INTERNAL APPROVAL");
			invoice.setInvoiceBaseValue(0.0);
			invoiceRepository.save(invoice);
			fin03a.forEach(fin03aSub -> {
				invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin03aSub.getTotalRentalCharge());
				fin03aSub.setFin02aInvNo(invoice.getInvoiceNo());
				fin03aSub.setFin02aInvStatus("IN INTERNAL APPROVAL");
				fin03aRepository.save(fin03aSub);
				List<Fin07> fin07Data = fin07Repository.findByFin03aRefNo(fin03aSub.getCode());
				fin07Data.forEach(fin07 -> {
					fin07.setFin02aInvNo(invoice.getCode());
					fin07.setFin02aInvStatus(invoice.getStatus());
					fin07Repository.save(fin07);
				});
				List<CimsHistoryFin02a> cimsHistoryFin02a = cimsHistoryFin02aRepository.findByFin03aRefNo(fin03aSub.getCode());
				cimsHistoryFin02a.forEach(cims -> {
					cims.setFin02aInvStatus(invoice.getStatus());
					cimsHistoryFin02aRepository.save(cims);
				});
			});
			invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
			invoice.setSst(0.0);
			invoice.setRetentionAmount(0.0);
			invoice.setNetRetentionAmount(0.0);
			invoice.setDebitNoteEntry(0.0);
			InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(2);
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
			invoice.setClinicTypeId(fin03aData.getClinicTypeId());
			invoice.setQuater(invoiceQuater(invoice.getMonth()));
			invoice.setSubmittedUserId(userId);
			invoice.setSubmittedDate(createdDate);
			invoiceRepository.save(invoice);
			Fin03aMapping fin03aMapping = new Fin03aMapping();
			fin03aMapping.setFin03aRefNo(fin03aData.getCode());
			fin03aMapping.setInvoiceRefNo(invoice.getInvoiceNo());
			fin03aMappingRepository.save(fin03aMapping);
			return invoice;
	}
	
	public Iterable<CimsHistoryFin02a> getFin07CreateListUsingChf2(Integer stateId, Integer districtId) {
		LocalDate createdDate = LocalDate.now();
		Set<CimsHistoryFin02aDistinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02a> uniqueCimsHistoryFin02a = new ArrayList<CimsHistoryFin02a>();
		List<CimsHistoryFin02a>  cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndMonthAndYear(String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0 ) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndStateIdAndMonthAndYear(stateId,String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndStateIdAndDistrictIdAndMonthAndYear(stateId,districtId,String.valueOf(createdDate.getMonthValue()-1 ),String.valueOf(createdDate.getYear()));	
		}
		cimsHistoryFin02aList.forEach(e->{
			if(e.getFin07Status() == null) { 
			CimsHistoryFin02aDistinct CimsTransactionUnique = new CimsHistoryFin02aDistinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin07Status());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueCimsHistoryFin02a.add(e);
			}
			}
		});

		return uniqueCimsHistoryFin02a;
	}
	
	public Iterable<CimsHistoryFin02a> getFin07CreateListForOlderUsingChf2Older(Integer stateId, Integer districtId) {
		LocalDate createdDate = LocalDate.now();
		Set<CimsHistoryFin02aDistinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02a> uniqueCimsHistoryFin02a = new ArrayList<CimsHistoryFin02a>();
		List<CimsHistoryFin02a>  cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndMonthNotAndYear(String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0 ) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndStateIdAndMonthNotAndYear(stateId,String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndStateIdAndDistrictIdAndMonthNotAndYear(stateId,districtId,String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));	
		}
		cimsHistoryFin02aList.forEach(e->{
			if(e.getFin07Status() == null) { 
			CimsHistoryFin02aDistinct CimsTransactionUnique = new CimsHistoryFin02aDistinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin07Status());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueCimsHistoryFin02a.add(e);
			}
			}
		});

		return uniqueCimsHistoryFin02a;
	}
	
	public List<CimsHistoryFin02a> getCimsHistoryFin02aForFin07Create(Integer clinicId, String month ,String year ){
		//LocalDate date = LocalDate.now();
		List<CimsHistoryFin02a> equipListForFin07Create = cimsHistoryFin02aRepository.findByClinicIdAndMonthAndYear(clinicId,String.valueOf(month),String.valueOf(year));
		Comparator<CimsHistoryFin02a> compareByBatchNumber = (CimsHistoryFin02a o1, CimsHistoryFin02a o2) -> o1.getBatchNumber().compareTo( o2.getBatchNumber() );
		Collections.sort(equipListForFin07Create, compareByBatchNumber);
		return equipListForFin07Create;
	}
	
	public Fin07 addFin07( List<CimsHistoryFin02a> CimsHistoryFin02aList,int userId) {
		Fin07 fin07 =new Fin07();
		String prvZero = "0";
		totalSelectedEquip = 0;
		CimsHistoryFin02a cims = CimsHistoryFin02aList.get(0);
		System.out.println(cims.getMonth().length());
		if(cims.getMonth().length() == 1) {
			prvZero = "00";
		}
		fin07.setCode("FIN07"+ cims.getClinic().getClinicCode()+ "-" + prvZero + cims.getMonth()+"-"+cims.getYear());
		fin07.setStateId(cims.getStateId());
		fin07.setDistrictId(cims.getDistrictId());
		fin07.setClinicTypeId(cims.getClinicTypeId());
		fin07.setClinicId(cims.getClinicId());
		fin07.setMonth(cims.getMonth());
		fin07.setYear(cims.getYear());
		fin07.setTotalRentalCharge(0.0);
		fin07.setStatus("IN INTERNAL APPROVAL");
		fin07Repository.save(fin07);
		CimsHistoryFin02aList.forEach(cimsData->{
				fin07.setTotalRentalCharge(fin07.getTotalRentalCharge()+cimsData.getRentalCharges());
				cimsData.setFin07RefNo(fin07.getCode());
				cimsData.setFin07Status("IN INTERNAL APPROVAL");			
				cimsHistoryFin02aRepository.save(cimsData);
		});
		fin07.setTotalRentalCharge(Double.valueOf(df.format(fin07.getTotalRentalCharge())));
		fin07.setTotalEquipmentCount(CimsHistoryFin02aList.size());
		fin07.setSubmittedUserId(userId);
		fin07.setSubmittedDate(createdDate);
		fin07Repository.save(fin07);
		return fin07;
	}
	
	public List<Fin07> fin07InProgressListUsingChf2(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin07> fin07InProgressList = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin07InProgressList.forEach(fin07 -> {
			fin07.setCimsHistoryFin02a(null);
		});
		return fin07InProgressList;
	}
	
	public List<Fin07> fin07InProgressListOlderUsingChf2Older(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin07> fin07InProgressList = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1 ));
		} else if(stateId != 0 && districtId == 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin07InProgressList = fin07Repository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin07InProgressList.forEach(fin07 -> {
			fin07.setCimsHistoryFin02a(null);
		});
		return fin07InProgressList;
	}
	
	public Fin07 getFin07(Integer id) {
		Fin07 fin07 = fin07Repository.findByFin07(id);
		Comparator<CimsHistoryFin02a> compareByBatchNumber = (CimsHistoryFin02a o1, CimsHistoryFin02a o2) -> o1.getBatchNumber().compareTo( o2.getBatchNumber() );
		Collections.sort(fin07.getCimsHistoryFin02a(), compareByBatchNumber);
		return fin07;
	}
	
	public Fin07 updateFin07Status(Fin07 Fin07) {
		if(Fin07.getStatus().equals("FOR APPROVAL TO MOH")) {
			Fin07.setApproval1Date(createdDate);			
		}else if(Fin07.getStatus().equals("APPROVED BY MOH")){
			Fin07.setApproval2Date(createdDate);
		}
		fin07Repository.save(Fin07);
		List<CimsHistoryFin02a> cimsHistory =  cimsHistoryFin02aRepository.findByFin07RefNo(Fin07.getCode());
		cimsHistory.forEach(cims -> {
			cims.setFin07Status(Fin07.getStatus());
			cimsHistoryFin02aRepository.save(cims);
		});
		
		return Fin07;
	}
	
	public List<Fin07> fin07ApprovedListUsingChf2(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin07> fin07ApprovedList = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin07ApprovedList.forEach(fin07 -> {
			fin07.setCimsHistoryFin02a(null);
		});
		return fin07ApprovedList;
	}
	
	public List<Fin07> fin07ApprovedListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin07> fin07ApprovedList = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin07ApprovedList = fin07Repository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin07ApprovedList.forEach(fin07 -> {
			fin07.setCimsHistoryFin02a(null);
		});
		return fin07ApprovedList;
	}

	public List<Fin07> getFin03aCreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin07Distinct> uniqueSet = new HashSet<>();
		List<Fin07> uniqueFin07List = new ArrayList<Fin07>();
		List<Fin07> fin07List = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));	
		} else if (stateId != 0 && districtId != 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
				
		fin07List.forEach(e->{
			Fin07Distinct CimsTransactionUnique = new Fin07Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin07List.add(e);
			}			
		});

		return uniqueFin07List;
	}
	
	public List<CimsHistoryFin02a> getDataForFin07CreateListPendingClinics (Integer districtId,Integer clinicTypeId,String month ,String year){
		
		Set<CimsHistoryFin02aDistinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02a> uniqueCimsHistoryFin02a = new ArrayList<CimsHistoryFin02a>();
		List<CimsHistoryFin02a>  cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
		
		cimsHistoryFin02aList = (List<CimsHistoryFin02a>) cimsHistoryFin02aRepository.findByFin07StatusIsNullAndDistrictIdAndClinicTypeIdAndMonthAndYear(districtId,clinicTypeId,String.valueOf(month),String.valueOf(year));
		
		cimsHistoryFin02aList.forEach(e->{
			if(e.getFin07Status() == null) { 
			CimsHistoryFin02aDistinct CimsTransactionUnique = new CimsHistoryFin02aDistinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getClinicId(),e.getMonth(),e.getYear(),e.getFin07Status());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueCimsHistoryFin02a.add(e);
			}
			}
		});
		
//		
//		List<CimsHistoryFin02a> fin07List = cimsHistoryFin02aRepository.findByDistrictIdAndClinicTypeIdAndFin07StatusAndMonthAndYearAndFin03aStatusIsNull(districtId, clinicTypeId, String.valueOf(month), String.valueOf(year));
//		Comparator<CimsHistoryFin02a> compareByClinicId = (CimsHistoryFin02a o1, CimsHistoryFin02a o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
//		Collections.sort(fin07List, compareByClinicId);
		return uniqueCimsHistoryFin02a;
		
	}
	
	public List<Fin07> getFin03aCreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin07Distinct> uniqueSet = new HashSet<>();
		List<Fin07> uniqueFin07List = new ArrayList<Fin07>();
		List<Fin07> fin07List = new ArrayList<Fin07>();
		if(stateId == 0 && districtId == 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));	
		} else if (stateId != 0 && districtId != 0) {
			fin07List = (List<Fin07>) fin07Repository.findByStatusAndFin03aStatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
				
		fin07List.forEach(e->{
			Fin07Distinct CimsTransactionUnique = new Fin07Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin07List.add(e);
			}			
		});

		return uniqueFin07List;
	}
	
	public List<Fin07> getFin07ForFin03Create(Integer districtId, Integer clinicTypeId, String month , String year){
		List<Fin07> fin07List = fin07Repository.findByDistrictIdAndClinicTypeIdAndStatusAndMonthAndYearAndFin03aStatusIsNull(districtId, clinicTypeId, "APPROVED BY MOH", String.valueOf(month), String.valueOf(year));
		Comparator<Fin07> compareByClinicId = (Fin07 o1, Fin07 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		Collections.sort(fin07List, compareByClinicId);
		return fin07List;
	}
	
	public Fin03a addFin03a(List<Fin07> fin07,int userId,int backLog) {
		Fin07 fin07Data = fin07.get(0);
		String prvZero = "0";
		//List<Fin03a> fin03aCheck = fin03aRepository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(fin07Data.getDistrictId(), fin07Data.getClinicTypeId(), fin07Data.getMonth(), fin07Data.getYear());
			Fin03a fin03a = new Fin03a();
			fin03a.setClinicTypeId(fin07Data.getClinicTypeId());
			CostCenterCode costCenterCode = costCenterCodeRepository.findByDistrictIdAndClinicTypeId(fin07Data.getDistrictId(), fin07Data.getClinicTypeId());
			if(fin07Data.getMonth().length() == 1) {
				prvZero = "00";
			}
			if(backLog == 0) {
				fin03a.setCode("FIN03A" + fin07Data.getStateCode() + costCenterCode.getCostCenterName() + "-" + prvZero + fin07Data.getMonth()+ "-" +fin07Data.getYear());
			} else {
				fin03a.setCode("FIN03A" + fin07Data.getStateCode() + costCenterCode.getCostCenterName() + "-" + prvZero +  fin07Data.getMonth()+ "-" +fin07Data.getYear()+ "-" + backLog);
			}
			fin03a.setStateId(fin07Data.getStateId());
			fin03a.setDistrictId(fin07Data.getDistrictId());
			fin03a.setClinicTypeId(fin07Data.getClinicTypeId());
			fin03a.setMonth(fin07Data.getMonth());
			fin03a.setYear(fin07Data.getYear());
			fin03a.setStatus("IN INTERNAL APPROVAL");
			fin03a.setTotalEquipmentCount(0);
			fin03a.setTotalRentalCharge(0.0);
			fin03aRepository.save(fin03a);
			fin07.forEach(fin07Sub -> {
				fin03a.setTotalEquipmentCount(fin03a.getTotalEquipmentCount() + fin07Sub.getTotalEquipmentCount());
				fin03a.setTotalRentalCharge(fin03a.getTotalRentalCharge() + fin07Sub.getTotalRentalCharge());
				fin07Sub.setFin03aRefNo(fin03a.getCode());
				fin07Sub.setFin03aStatus("IN INTERNAL APPROVAL");
				fin07Repository.save(fin07Sub);
				List <CimsHistoryFin02a> cimsHistory = cimsHistoryFin02aRepository.findByFin07RefNo(fin07Sub.getCode());
				cimsHistory.forEach(cimsData -> {
					cimsData.setFin03aRefNo(fin03a.getCode());
					cimsData.setFin03aStatus("IN INTERNAL APPROVAL");
					cimsHistoryFin02aRepository.save(cimsData);
				});
			});
			fin03a.setTotalRentalCharge(Double.valueOf(df.format(fin03a.getTotalRentalCharge())));
			fin03a.setSubmittedUserId(userId);
			fin03a.setSubmittedDate(createdDate);
			fin03aRepository.save(fin03a);
			return fin03a;
	}
	
	public Fin03a getFin03a(Integer id) {
		Fin03a fin03a = fin03aRepository.findByFin03a(id);
		Comparator<Fin07> compareByClinicId = (Fin07 o1, Fin07 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		Collections.sort(fin03a.getFin07(), compareByClinicId);
		return fin03a;
	}
	
	public List<Fin03a> fin03aInProgressList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aInProgressList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03aInProgressList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aInProgressList;
	}
	
	public List<Fin03a> fin03aInProgressListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aInProgressList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin03aInProgressList = fin03aRepository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03aInProgressList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aInProgressList;
	}
	
	public Fin03a updateFin03aStatus(Fin03a Fin03a) {
		if(Fin03a.getStatus().equals("FOR APPROVAL TO MOH")) {
			Fin03a.setApproval1Date(createdDate);			
		}else if(Fin03a.getStatus().equals("APPROVED BY MOH")){
			Fin03a.setApproval2Date(createdDate);
		}
		fin03aRepository.save(Fin03a);
		List<Fin07> Fin07 = fin07Repository.findByFin03aRefNo(Fin03a.getCode());
		Fin07.forEach(fin07 -> {
			fin07.setFin03aStatus(Fin03a.getStatus());
			fin07Repository.save(fin07);
		});
		List<CimsHistoryFin02a> cimsHistory =  cimsHistoryFin02aRepository.findByFin03aRefNo(Fin03a.getCode());
		
		cimsHistory.forEach(cims -> {
			cims.setFin03aStatus(Fin03a.getStatus());
			cimsHistoryFin02aRepository.save(cims);
		});
		return Fin03a;
	}
	
	public List<Fin03a> fin03aApprovedList(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aApprovedList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03aApprovedList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aApprovedList;
	}
	
	public List<Fin03a> fin03aApprovedListOlder(String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aApprovedList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0 ) {
			fin03aApprovedList = fin03aRepository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03aApprovedList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aApprovedList;
	}
	
	public List<Fin03a> getFin02aCreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		fin03aList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aList;
	}
	
	public List<Fin03a> getFin02aCreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<Fin03a> fin03aList = new ArrayList<Fin03a>();
		if(stateId == 0 && districtId == 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));	
		} else if(stateId != 0 && districtId == 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if (stateId != 0 && districtId != 0) {
			fin03aList = fin03aRepository.findByFin02aInvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		
		fin03aList.forEach(fin03a -> {
			fin03a.setFin07(null);
		});
		return fin03aList;
	}
	
	public Invoice updateFin02aInvoiceStatus(Invoice Invoice) {
		if(Invoice.getStatus().equals("FOR APPROVAL TO MOH")) {
			Invoice.setApproval1Date(createdDate);			
		}else if(Invoice.getStatus().equals("APPROVED BY MOH")){
			Invoice.setApproval2Date(createdDate);
		}
		invoiceRepository.save(Invoice);
		List<Fin03a> Fin03a = fin03aRepository.findByFin02aInvNo(Invoice.getInvoiceNo());
		Fin03a.forEach( fin03a -> {
			fin03a.setFin02aInvStatus(Invoice.getStatus());
			fin03aRepository.save(fin03a);
		});
		List<Fin07> fin07 = fin07Repository.findByFin02aInvNo(Invoice.getCode());
		fin07.forEach(fin07Sub -> {
			fin07Sub.setFin02aInvStatus(Invoice.getStatus());
			fin07Repository.save(fin07Sub);
		});
		 Fin03aMapping fin03aMapping = fin03aMappingRepository.findByInvoiceRefNo(Invoice.getInvoiceNo());
		 List<CimsHistoryFin02a> cimsHistory =  cimsHistoryFin02aRepository.findByFin03aRefNo(fin03aMapping.getFin03aRefNo());
		 cimsHistory.forEach(cims -> {
			 cims.setFin02aInvStatus(Invoice.getStatus());
			 cimsHistoryFin02aRepository.save(cims);
		 });
		
		return Invoice;
	}
	
	public Fin03a fin03aForInvoiceApproval(String invoiceNo){
		Fin03aMapping fin03aMapping = fin03aMappingRepository.findByInvoiceRefNo(invoiceNo);
		Fin03a Fin03a = fin03aRepository.findByCode(fin03aMapping.getFin03aRefNo());
		System.out.println(Fin03a);
		return  Fin03a;
	}

	public List<InvoiceGeneration> getInvoiceFin07Initiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndIsFin07Created( month, year,stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
		
	   public List<InvoiceGeneration> getInvoiceFin07Inprogress(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndIsFin07CreatedAndFin07StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
	   
	   public List<InvoiceGeneration> getInvoiceFin07Approved(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
		   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndIsFin07CreatedAndFin07Status(month, year,"APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);		
		}
	   
		public List<InvoiceGeneration> getInvoiceFin03aInitiate(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin07Status( month, year, "APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
		
	   public List<InvoiceGeneration> getInvoiceFin03aInprogress(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin07StatusAndFin03aStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
	   
	   public List<InvoiceGeneration> getInvoiceFin03aApproved(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
		   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin07StatusAndFin03aStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
	   
	   
		public List<InvoiceGeneration> getInvoiceFin02InvInitiate(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin03aStatus( month, year, "APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
		
	  public List<InvoiceGeneration> getInvoiceFin02InvInprogress(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
			List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin03aStatusAndFin02aStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
	  
	  public List<InvoiceGeneration> getInvoiceFin02InvApproved(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
		   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
			cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByMonthAndYearAndFin03aStatusAndFin02aStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);		
			return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
			
		}
	  
	  
	  
	  
	  
	  public List<InvoiceGeneration> getInvoiceFin07InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
			if(stateId==0) {
				stateId = null;
			}
			if(districtId==0) {
				districtId = null;
			}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndIsFin07Created( month,year,stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
			
		   public List<InvoiceGeneration> getInvoiceFin07InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndIsFin07CreatedAndFin07StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
		   
		   public List<InvoiceGeneration> getInvoiceFin07ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
			   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndIsFin07CreatedAndFin07Status(month, year,"APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);		
			}
		   
			public List<InvoiceGeneration> getInvoiceFin03aInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin07Status( month, year, "APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
			
		   public List<InvoiceGeneration> getInvoiceFin03aInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin07StatusAndFin03aStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
		   
		   public List<InvoiceGeneration> getInvoiceFin03aApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
			   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin07StatusAndFin03aStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
		   
		   
			public List<InvoiceGeneration> getInvoiceFin02InvInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin03aStatus( month, year, "APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
			
		  public List<InvoiceGeneration> getInvoiceFin02InvInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
				List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin03aStatusAndFin02aStatusNot(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
		  
		  public List<InvoiceGeneration> getInvoiceFin02InvApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
				if(stateId==0) {
					stateId = null;
				}
				if(districtId==0) {
					districtId = null;
				}
			   List<CimsHistoryFin02a> cimsHistoryFin02aList = new ArrayList<CimsHistoryFin02a>();
				cimsHistoryFin02aList = cimsHistoryFin02aRepository.findByNotMonthAndYearAndFin03aStatusAndFin02aStatus(month, year,"APPROVED BY MOH","APPROVED BY MOH",stateId,districtId);		
				return getUniqueInvoiceGeneration(cimsHistoryFin02aList);
				
			}
		public List<InvoiceGeneration> getUniqueInvoiceGeneration(List<CimsHistoryFin02a> cimsHistoryFin02aList){
			List<InvoiceGeneration> invoiceGenerationList = new ArrayList<InvoiceGeneration>();
			Map<Integer, Double> amountList =cimsHistoryFin02aList.stream().collect(Collectors.groupingBy(CimsHistoryFin02a::getClinicId,Collectors. summingDouble(CimsHistoryFin02a::getRentalCharges)));
			
			Map<Integer, Long> countList =cimsHistoryFin02aList.stream().collect(Collectors.groupingBy(CimsHistoryFin02a::getClinicId,Collectors.counting()));
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
