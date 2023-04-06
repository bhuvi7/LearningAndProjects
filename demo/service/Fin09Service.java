package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.CimsHistoryFin02b;
import com.example.demo.EntityModel.ClinicType;
import com.example.demo.EntityModel.District;
import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin07Distinct;
import com.example.demo.EntityModel.Fin09;
import com.example.demo.EntityModel.Fin09Clinic;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.Retention;
import com.example.demo.EntityModel.RevenueAnalysisLastQuater;
import com.example.demo.EntityModel.State;
import com.example.demo.repository.Fin09Repository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.ClinicTypeRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.Fin09ClinicRepository;

@Service
public class Fin09Service {
	
	//private LocalDate createDate = LocalDate.now();
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private Fin09Repository fin09Repository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ClinicTypeRepository clinicTypeRepository;
	
	@Autowired
	private Fin09ClinicRepository fin09ClinicRepository;
	
	@Autowired
	private StateRepository stateRepository;

	
	public String clinicTypeCodeForICliice(String clinicTypeCode) {
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
	
	public String findByStateId(Integer stateId) {
		State state = stateRepository.findByStateIdOne(stateId);
		String stateCode = state.getStateCode();
		return stateCode;
	}
	
	
	
	
	
	/*
	public Fin09 createFin09(List<Fin09Clinic> Fin09Clinic) {
		Fin09 Fin09 = new Fin09();
		BeanUtils.copyProperties(Fin09Clinic.get(0), Fin09 );
		fin09Repository.save(Fin09);
		Fin09.setMonth(String.valueOf(Fin09.getCreatedDate().getMonthValue()));
		Fin09.setYear(String.valueOf(Fin09.getCreatedDate().getYear()));
		Fin09.setCode("FIN09" + Fin09.getDistrictCode() + Fin09.getClinicTypeCode() + Fin09.getMonth() +"-"+ Fin09.getYear());
		Fin09.setStatus("IN INTERNAL APPROVAL");
		Fin09.setTotalLateDeliveryPenalty(0.0);
		Fin09.setTotalRepairTimePenalty(0.0);
		Fin09.setTotalResponseTimePenalty(0.0);
		Fin09.setTotalScheduledMaintenancePenalty(0.0);
		Fin09.setTotalUptimePenalty(0.0);
		Fin09.setTotalPenalty(0.0);
		Fin09Clinic.forEach(fin09Clinic -> {
			fin09Clinic.setTotalPenalty(0.0);
			fin09Clinic.setTotalPenalty(fin09Clinic.getLateDeliveryPenalty() + fin09Clinic.getRepairTimePenalty() + 
					fin09Clinic.getResponseTimePenalty() + fin09Clinic.getScheduledMaintenancePenalty() + 
					fin09Clinic.getUptimePenalty());
			Fin09.setTotalLateDeliveryPenalty(Fin09.getTotalLateDeliveryPenalty() + fin09Clinic.getLateDeliveryPenalty());
			Fin09.setTotalRepairTimePenalty(Fin09.getTotalRepairTimePenalty() + fin09Clinic.getRepairTimePenalty());
			Fin09.setTotalResponseTimePenalty(Fin09.getTotalResponseTimePenalty() + fin09Clinic.getResponseTimePenalty());
			Fin09.setTotalScheduledMaintenancePenalty(Fin09.getTotalScheduledMaintenancePenalty() + fin09Clinic.getScheduledMaintenancePenalty());
			Fin09.setTotalUptimePenalty(Fin09.getTotalUptimePenalty() + fin09Clinic.getUptimePenalty());
			fin09Clinic.setFin09RefNo(Fin09.getCode());
			fin09Clinic.setFin09Status("IN INTERNAL APPROVAL");
			fin09ClinicRepository.save(fin09Clinic);
		});
		Fin09.setTotalPenalty(Fin09.getTotalLateDeliveryPenalty() + Fin09.getTotalRepairTimePenalty() + 
				Fin09.getTotalResponseTimePenalty() + Fin09.getTotalScheduledMaintenancePenalty() + 
				Fin09.getTotalUptimePenalty());
		Fin09.setTotalLateDeliveryPenalty(Double.valueOf(df.format(Fin09.getTotalLateDeliveryPenalty())));
		Fin09.setTotalRepairTimePenalty(Double.valueOf(df.format(Fin09.getTotalRepairTimePenalty())));
		Fin09.setTotalResponseTimePenalty(Double.valueOf(df.format(Fin09.getTotalResponseTimePenalty())));
		Fin09.setTotalScheduledMaintenancePenalty(Double.valueOf(df.format(Fin09.getTotalScheduledMaintenancePenalty())));
		Fin09.setTotalUptimePenalty(Double.valueOf(df.format(Fin09.getTotalUptimePenalty())));
		Fin09.setTotalPenalty(Double.valueOf(df.format(Fin09.getTotalPenalty())));
		fin09Repository.save(Fin09);
		return Fin09;
	}
	*/
	
	public Fin09 createFin09(List<Fin09Clinic> Fin09Clinic) {
		Fin09 Fin09 = new Fin09();
		BeanUtils.copyProperties(Fin09Clinic.get(0), Fin09 );
		List<Fin09Clinic> fin09List  =fin09ClinicRepository.findByFin09RefNo("FIN09" +"-"+findByStateId(Fin09.getStateId()) +"-"+findDistrictCode(Fin09.getDistrictId()) +"-"+findClinicTypeCode(Fin09.getClinicTypeId())+"-"+Fin09.getApprovalMonth()+"-"+ Fin09.getApprovalYear());
		if(fin09List.size()==0) {
		fin09Repository.save(Fin09);
		//Fin09.setMonth(String.valueOf(Fin09.getCreatedDate().getMonthValue()));
		//mine
		Fin09.setMonth(Fin09Clinic.get(0).getApprovalMonth());
		Fin09.setQuater(invoiceQuater(Fin09Clinic.get(0).getApprovalMonth()));
		Fin09.setApprovalQuater(Fin09.getQuater());
		//Fin09.setQuater(Fin09.getApprovalQuater());
		Fin09.setYear(Fin09Clinic.get(0).getApprovalYear());
		Fin09.setCode("FIN09" +"-"+findByStateId(Fin09.getStateId()) +"-"+findDistrictCode(Fin09.getDistrictId()) +"-"+findClinicTypeCode(Fin09.getClinicTypeId())+"-"+Fin09.getApprovalMonth()+"-"+ Fin09.getApprovalYear());
		if(Fin09Clinic.get(0).getTotalPenalty()==0) {
			Fin09.setStatus("PENALTY ADJUSTED");
		}else
		{
			
			Fin09.setStatus("FIN09 NOT ADJUSTED");
		}
		Fin09.setTotalLateDeliveryPenalty(0.0);
		Fin09.setTotalRepairTimePenalty(0.0);
		Fin09.setTotalResponseTimePenalty(0.0);
		Fin09.setTotalScheduledMaintenancePenalty(0.0);
		Fin09.setTotalUptimePenalty(0.0);
		Fin09.setTotalPenalty(0.0);
		Fin09Clinic.forEach(fin09Clinic -> {
			fin09Clinic.setTotalPenalty(0.0);
			fin09Clinic.setTotalPenalty(fin09Clinic.getLateDeliveryPenalty() + fin09Clinic.getRepairTimePenalty() + 
					fin09Clinic.getResponseTimePenalty() + fin09Clinic.getScheduledMaintenancePenalty() + 
					fin09Clinic.getUptimePenalty());
			Fin09.setTotalLateDeliveryPenalty(Fin09.getTotalLateDeliveryPenalty() + fin09Clinic.getLateDeliveryPenalty());
			Fin09.setTotalRepairTimePenalty(Fin09.getTotalRepairTimePenalty() + fin09Clinic.getRepairTimePenalty());
			Fin09.setTotalResponseTimePenalty(Fin09.getTotalResponseTimePenalty() + fin09Clinic.getResponseTimePenalty());
			Fin09.setTotalScheduledMaintenancePenalty(Fin09.getTotalScheduledMaintenancePenalty() + fin09Clinic.getScheduledMaintenancePenalty());
			Fin09.setTotalUptimePenalty(Fin09.getTotalUptimePenalty() + fin09Clinic.getUptimePenalty());
			fin09Clinic.setFin09RefNo(Fin09.getCode());
			if(Fin09Clinic.get(0).getTotalPenalty()==0) {
				fin09Clinic.setFin09Status("PENALTY ADJUSTED");
			}
			
			else {
				fin09Clinic.setFin09Status("FIN09 NOT ADJUSTED");
				
			}
			fin09Clinic.setApprovalQuater(Fin09.getQuater());
			fin09Clinic.setMonth(Fin09.getMonth());
			
			fin09ClinicRepository.save(fin09Clinic);
		});
		Fin09.setTotalPenalty(Fin09.getTotalLateDeliveryPenalty() + Fin09.getTotalRepairTimePenalty() + 
				Fin09.getTotalResponseTimePenalty() + Fin09.getTotalScheduledMaintenancePenalty() + 
				Fin09.getTotalUptimePenalty());
		Fin09.setTotalLateDeliveryPenalty(Double.valueOf(df.format(Fin09.getTotalLateDeliveryPenalty())));
		Fin09.setTotalRepairTimePenalty(Double.valueOf(df.format(Fin09.getTotalRepairTimePenalty())));
		Fin09.setTotalResponseTimePenalty(Double.valueOf(df.format(Fin09.getTotalResponseTimePenalty())));
		Fin09.setTotalScheduledMaintenancePenalty(Double.valueOf(df.format(Fin09.getTotalScheduledMaintenancePenalty())));
		Fin09.setTotalUptimePenalty(Double.valueOf(df.format(Fin09.getTotalUptimePenalty())));
		Fin09.setTotalPenalty(Double.valueOf(df.format(Fin09.getTotalPenalty())));
		fin09Repository.save(Fin09);
		}
		return Fin09;
	}
	
	private String findByStateCode(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Fin09 updateFin09Status(Fin09 fin09) {
		fin09Repository.save(fin09);
		List<Fin09Clinic> fin09Clinics = fin09ClinicRepository.findByFin09RefNo(fin09.getCode());
		fin09Clinics.forEach(fin09Clinic -> {
			fin09Clinic.setFin09Status(fin09.getStatus());
			fin09ClinicRepository.save(fin09Clinic);
		});
		return fin09;
	}
	
	public List<Fin09> getAllUnadjustedPenalities(String status){
		
		List<Fin09> fin09Invoices =(List<Fin09>) fin09Repository.findByStatusNot(status);
		 List<Fin09> fin09InvoiceList = fin09Invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getStateId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Fin09(f1.getStateId(),f1.getStateName(),f1.getTotalPenalty() + f2.getTotalPenalty())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return fin09InvoiceList;		
	}
	
	
	public List<Fin09> getAllUnadjustedPenalityByStateName(String stateName,String status) {
		List<Fin09> fin09Invoices = fin09Repository.findByStateNameAndStatusNot(stateName,status);	
		 List<Fin09> fin09InvoiceList = fin09Invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getCircleCode()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Fin09(f1.getCircleCode(),f1.getTotalPenalty() + f2.getTotalPenalty())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return fin09InvoiceList;		
	}
	
	public List<Fin09> getAllUnadjustedPenalityByCircleCode(String circleCode,String status) {
		List<Fin09> fin09Invoices = fin09Repository.findByStateNameAndCircleCodeAndStatusNot(circleCode,status); 
		 List<Fin09> fin09InvoiceList = fin09Invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getDistrictId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Fin09(f1.getStateId(),f1.getCircleCode(),f1.getDistrictId(), f1.getDistrictName(),f1.getTotalPenalty() + f2.getTotalPenalty())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return fin09InvoiceList;		
	}
	
	public List<Fin09> getAllUnadjustedPenalityByDistrictName(String districtName, String status) {
		List<Fin09> fin09Invoices = fin09Repository.findByDistrictNameAndStatusNot(districtName, status);;
		 List<Fin09> fin09InvoiceList = fin09Invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getQuater()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Fin09(f1.getDistrictName(),f1.getQuater(),f1.getTotalPenalty() + f2.getTotalPenalty())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return fin09InvoiceList;
	}
	
	public List<Fin09> getAllUnadjustedPenalityByQuaterQuater(String key,String value,String status){
		List<Fin09> invoices = new ArrayList<Fin09>();
		List<Fin09> invoicesList = new ArrayList<Fin09>();
			if(key.equals("ALL")) {
				invoices = (List<Fin09>) fin09Repository.findByStatusNot(status);
			}
			if(key.equals("STATE")) {
				invoices = fin09Repository.findByStateNameAndStatusNot(value,status);	
			}
			if(key.equals("CIRCLE")) {
				invoices = fin09Repository.findByStateNameAndCircleCodeAndStatusNot(value,status);	
			}
			if(key.equals("DISTRICT")) {
				invoices = fin09Repository.findByDistrictNameAndStatusNot(value,status);	
			}
			
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getQuater()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Fin09(f1.getTotalPenalty() + f2.getTotalPenalty(),f1.getQuater() ,f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
			invoicesList.sort((i1,i2)->i2.getQuater().compareTo(i1.getQuater()));
			 return invoicesList;		
	}
	
	public List<Fin09> getAllUnadjustedPenalityByQuaterMonth(String key,String value,String quater,String year,String status){
		List<Fin09> invoices = new ArrayList<Fin09>();
		List<Fin09> invoicesList = new ArrayList<Fin09>();
			if(key.equals("ALL")) {
				invoices = (List<Fin09>) fin09Repository.findByQuaterAndYearAndStatusNot(quater,year,status);
			}
			if(key.equals("STATE")) {
				invoices = fin09Repository.findByStateNameAndQuaterAndYearAndStatusNot(value,quater,year,status);	
			}
			if(key.equals("CIRCLE")) {
				invoices = fin09Repository.findByCircleCodeAndQuaterAndYearAndStatusNot(value,quater,year,status);	
			}
			if(key.equals("DISTRICT")) {
				invoices = fin09Repository.findByDistrictNameAndQuaterAndYearAndStatusNot(value,quater,year,status);	
			}
			
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getMonth()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Fin09(f1.getTotalPenalty() + f2.getTotalPenalty(),f1.getQuater() ,f1.getMonth() ,f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
			 return invoicesList;		
	}
	

}

