package com.example.demo.service;

import java.util.ArrayList;
import org.springframework.data.util.Pair;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin10b;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoicePaymentHistory;
import com.example.demo.EntityModel.Retention;
import com.example.demo.EntityModel.RevenueAnalysisLastQuater;
import com.example.demo.repository.Fin10bRepository;
import com.example.demo.repository.InvoicePaymentHistoryRepository;
import com.example.demo.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	Fin10bRepository fin10bRepository;
	
	@Autowired
	private InvoicePaymentHistoryRepository invoicePaymentHistoryRepository;
	
	public List<Invoice> getInvoiceInProgressList(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceInProgressList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndMonth(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdAndMonth(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdAndMonth(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceInProgressList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceInProgressList;
	}
	
	public List<Invoice> getInvoiceInProgressListForFin04(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceInProgressList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotFin04(invoiceTypeId, status);
		} else if(stateId != 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdFin04(invoiceTypeId, status, stateId);
		} else if(stateId != 0 && districtId != 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdFin04(invoiceTypeId, status, stateId, districtId);
		}
		
		invoiceInProgressList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceInProgressList;
	}
	
	
//	public List<Invoice> findByInvoicePaymentStatusPenalty(String stateName,String districtName,String invoiceTypeName,Integer clinicTypeId,String approvalQuater,String approvalYear){
//		LocalDate date = LocalDate.now();
//		List<Invoice> invoiceInProgressList =  new ArrayList<Invoice>();
//		String saving = approvalQuater;
//		String Q4 ="Q4";
//		String Q3 ="Q3";
//		String Q2 ="Q2";
//		String Q1 ="Q1";
//		if(saving.equals(Q4) ) {
//			String month ="12";
//			System.out.println(month);
//			invoiceInProgressList = invoiceRepository.findByQuaterAndYearAndStatus(stateName,districtName,invoiceTypeName,clinicTypeId,month,approvalYear);
//		}
//		else if(saving.equals(Q3)) {
//			String month ="9";
//			System.out.println(month);
//			invoiceInProgressList = invoiceRepository.findByQuaterAndYearAndStatus(stateName,districtName,invoiceTypeName,clinicTypeId,month,approvalYear);
//		}
//		else if(saving.equals(Q2)){
//			String month ="6";
//			System.out.println(month);
//			invoiceInProgressList = invoiceRepository.findByQuaterAndYearAndStatus(stateName,districtName,invoiceTypeName,clinicTypeId,month,approvalYear);
//		}
//		else if(saving.equals(Q1)) {
//			String month ="3";
//			System.out.println(month);
//			invoiceInProgressList = invoiceRepository.findByQuaterAndYearAndStatus(stateName,districtName,invoiceTypeName,clinicTypeId,month,approvalYear);
//		}
//		System.out.println(approvalQuater);
//		return invoiceInProgressList;
//	}
	
	public Invoice findByInvoiceByPaymentHistoryId(Integer id){
//		Optional<InvoicePaymentHistory> invoiceSearchedById ;
//		List<InvoicePaymentHistory> invoicePaymentHistory
		InvoicePaymentHistory paymentSearchById =invoicePaymentHistoryRepository.findByIds(id);
		
		Invoice invoiceSearchById = invoiceRepository.findByInvoiceNo(paymentSearchById.getInvoiceNo());
		
		
		return invoiceSearchById;
	}
	public Invoice findByInvoiceNoCndnId(Integer id) {
		
		InvoicePaymentHistory paymentSearchById =invoicePaymentHistoryRepository.findByIds(id);
		Invoice invoiceSearchById = invoiceRepository.findByInvoiceNo( paymentSearchById.getInvoiceNo());
		return invoiceSearchById;
	}
	
    public List<Fin10b> findByInvoiceNofetchdate(int id) {
		
    	Invoice fetchInvoice = invoiceRepository.findByIdForDate(id);
    	List<Fin10b> fin10bInvoice = fin10bRepository.findByFin04InvNo(fetchInvoice.getInvoiceNo());
     	return fin10bInvoice;
	}
	
	
	public List<Invoice> getInvoiceInProgressListOlder(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceInProgressList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndMonthNot(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdAndMonthNot(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceInProgressList = invoiceRepository.findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdAndMonthNot(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceInProgressList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceInProgressList;
	}
	
	public List<Invoice> getInvoiceApprovedList(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceApprovedList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndMonth(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndMonth(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonth(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceApprovedList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceApprovedList;
	}
	public List<Invoice> getInvoiceApprovedListForFin04(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceApprovedList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndMonthForFin04(invoiceTypeId, status);
		} else if(stateId != 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndMonthForFin04(invoiceTypeId, status, stateId);
		} else if(stateId != 0 && districtId != 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonthForFin04(invoiceTypeId, status, stateId, districtId);
		}
		
		invoiceApprovedList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceApprovedList;
	}
	public List<Invoice> getInvoiceApprovedListOlder(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceApprovedList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndMonthNot(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndMonthNot(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonthNot(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceApprovedList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceApprovedList;
	}
	
	public List<Invoice> getAllReceivable(String retention){
		List<Invoice> invoices =(List<Invoice>) invoiceRepository.findAll();
		 List<Invoice> invoicesList = new ArrayList<Invoice>();
			if(retention.equals("WITHOUT-RETENTION")) {
				invoicesList = invoices.stream()
		           .collect(Collectors.groupingBy(foo -> foo.getStateId()))
		           .entrySet().stream()
		           .map(e -> e.getValue().stream()
//		                   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getStateName(),(f1.getOutstandingAmount()-f1.R()) + (f2.getOutstandingAmount()-f2.getRetentionAmount()) )))
		        		   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getStateName(), f1.getOutstandingAmount()+f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention() )))
		                   .map(f -> f.get())
		                   .collect(Collectors.toList());	
			}else if(retention.equals("WITH-RETENTION")) {
//				System.out.println(retention);
				invoicesList = invoices.stream()
				           .collect(Collectors.groupingBy(foo -> foo.getStateId()))
				           .entrySet().stream()
				           .map(e -> e.getValue().stream()
				                   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getStateName(),f1.getOutstandingAmount() + f2.getOutstandingAmount())))
				                   .map(f -> f.get())
				                   .collect(Collectors.toList());	
			}
				 
		 return invoicesList;		
	}
	public List<Invoice> getInvoiceApprovedListFin01(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceApprovedList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndMonth(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndMonth(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonth(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceApprovedList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceApprovedList;
	}
	
	public List<Invoice> getInvoiceApprovedListFin01Older(Integer invoiceTypeId,String status,Integer stateId,Integer districtId){
		LocalDate date = LocalDate.now();
		List<Invoice> invoiceApprovedList =  new ArrayList<Invoice>();
		
		if(stateId == 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndMonthNot(invoiceTypeId, status, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndMonthNot(invoiceTypeId, status, stateId, String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			invoiceApprovedList = invoiceRepository.findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonthNot(invoiceTypeId, status, stateId, districtId, String.valueOf(date.getMonthValue()-1));
		}
		
		invoiceApprovedList.forEach(invoice -> {
			invoice.setFin06(null);
			invoice.setFin03a(null);
			invoice.setFin03(null);
			invoice.setFin10b(null);
			invoice.setFin05aSiteConformity(null);
			invoice.setFin11ConcessionElements(null);
		});
		
		return invoiceApprovedList;
	}
	
public List<Invoice> getAllReceivableByStateName(String retention,String stateName) {
		
		List<Invoice> invoices = invoiceRepository.findByStateName(stateName);
		List<Invoice> invoicesList = new ArrayList<Invoice>();
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getCircleCode()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
//			                   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getDistrictId(),f1.getDistrictName(),(f1.getOutstandingAmount()-f1.getRetentionAmount()) + (f2.getOutstandingAmount()-f2.getRetentionAmount()) )))
			        		   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getStateName(),f1.getCircleCode(), f1.getOutstandingAmount()+f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention() )))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
//			System.out.println(retention);
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getCircleCode()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getStateName(),f1.getCircleCode(), f1.getOutstandingAmount() + f2.getOutstandingAmount() )))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
			 
		 return invoicesList;		
	}
	
public List<Invoice> getAllReceivableByCircleCode(String retention,String circleCode) {
		
		List<Invoice> invoices = invoiceRepository.findByCircleCode(circleCode);  
		List<Invoice> invoicesList = new ArrayList<Invoice>();
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getDistrictId()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
//			                   .reduce((f1,f2) -> new Invoice(f1.getStateId(),f1.getDistrictId(),f1.getDistrictName(),(f1.getOutstandingAmount()-f1.getRetentionAmount()) + (f2.getOutstandingAmount()-f2.getRetentionAmount()) )))
			        		   .reduce((f1,f2) -> new Invoice(f1.getCircleCode(),f1.getDistrictId(), f1.getDistrictName(),f1.getOutstandingAmount()+f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention() )))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
//			System.out.println(retention);
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getDistrictId()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Invoice(f1.getCircleCode(),f1.getDistrictId(), f1.getDistrictName(), f1.getOutstandingAmount() + f2.getOutstandingAmount() )))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
			 
		 return invoicesList;		
	}
	 
	public List<Invoice> getAllReceivableByDistrictName(String retention,String districtName) {
		List<Invoice> invoices = invoiceRepository.findByDistrictName(districtName);
		List<Invoice> invoicesList = new ArrayList<Invoice>();
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getQuater()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
//                   .reduce((f1,f2) -> new Invoice(f1.getDistrictName(),f1.getQuater(),(f1.getOutstandingAmount()-f1.getRetentionAmount()) + (f2.getOutstandingAmount()-f2.getRetentionAmount()) )))
        		   .reduce((f1,f2) -> new Invoice(f1.getDistrictName(),f1.getQuater(),f1.getOutstandingAmount() + f2.getOutstandingAmount(), f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention() )))
                   .map(f -> f.get())
                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
//			System.out.println("getAllReceivableByDistrictName");
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getQuater()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Invoice(f1.getDistrictName(),f1.getQuater(),f1.getOutstandingAmount() + f2.getOutstandingAmount())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
			
		 return invoicesList;
	}
	
	public List<Invoice> getAllReceivableByInvoiceType(String retention,String key,String value){
		List<Invoice> invoices = new ArrayList<Invoice>();
//		System.out.println(retention);
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findAll();
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateName(value);;	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCode(value);	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictName(value);	
		}
		List<Invoice> invoicesList = new ArrayList<Invoice>();
	
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getInvoiceTypeId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Invoice(f1.getInvoiceTypeName(),f1.getOutstandingAmount() + f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention())))
//        		   .reduce((f1,f2) -> new Invoice(f1.getInvoiceTypeName(), (f1.getOutstandingAmount()-f1.getRetentionAmount()<0.0?0.0:f1.getOutstandingAmount()-f1.getRetentionAmount())  + (f2.getOutstandingAmount()-f2.getRetentionAmount()<0.0?0.0:f2.getOutstandingAmount()-f2.getRetentionAmount())  ) ))
                   .map(f -> f.get())
                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo -> foo.getInvoiceTypeId()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
//			                   .reduce((f1,f2) -> new Invoice(f1.getInvoiceTypeName(),( (f1.getOutstandingAmount()-f1.getRetentionAmount())<0.0?0.0:f1.getOutstandingAmount()-f1.getRetentionAmount() ) + ( (f2.getOutstandingAmount()-f2.getRetentionAmount())<0.0?0.0: (f2.getOutstandingAmount()-f2.getRetentionAmount()))       )))
			        		   .reduce((f1,f2) -> new Invoice(f1.getInvoiceTypeName(),f1.getOutstandingAmount() + f2.getOutstandingAmount())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
		 return invoicesList;		
	}
	
	
	public List<Invoice> getAllReceivableQuater(String retention,String key,String value){

		List<Invoice> invoicesList = new ArrayList<Invoice>();
		List<Invoice> invoices = new ArrayList<Invoice>();
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findAll();
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateName(value);	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCode(value);	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictName(value);
		}
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
       //    .collect(Collectors.groupingBy(foo -> foo.getQuater()))
		   .collect(Collectors.groupingBy(foo -> Pair.of(foo.getQuater(), foo.getYear())))		
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Invoice(f1.getOutstandingAmount()+f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention(),f1.getQuater() ,f1.getYear())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
			invoicesList = invoices.stream()
			       //    .collect(Collectors.groupingBy(foo ->foo.getQuater()))
					   .collect(Collectors.groupingBy(foo -> Pair.of(foo.getQuater(), foo.getYear())))	
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
//			                   .reduce((f1,f2) -> new Invoice(( (f1.getOutstandingAmount()-f1.getRetentionAmount())<0.0?0.0:f1.getOutstandingAmount()-f1.getRetentionAmount() )  + ( (f2.getOutstandingAmount()-f2.getRetentionAmount())<0.0?0.0: (f2.getOutstandingAmount()-f2.getRetentionAmount())) ,f1.getQuater() ,f1.getYear())))
			        		   .reduce((f1,f2) -> new Invoice(f1.getOutstandingAmount() + f2.getOutstandingAmount(),f1.getQuater() ,f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
		
		invoicesList.sort((i1, i2) -> i2.getQuater().compareTo(i1.getQuater()));
		invoicesList.sort((i1, i2) -> i2.getYear().compareTo(i1.getYear()));
		 return invoicesList;		
	}
	
	
	public List<Invoice> getAllReceivableMonth(String retention,String key,String value,String quater,String year){

		List<Invoice> invoicesList = new ArrayList<Invoice>();
		List<Invoice> invoices = new ArrayList<Invoice>();
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findByQuaterAndYear(quater,year);
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateNameAndQuaterAndYear(value,quater,year);	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCodeAndQuaterAndYear(value,quater,year);	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictNameAndQuaterAndYear(value,quater,year);
		}
		if(retention.equals("WITHOUT-RETENTION")) {
			invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getMonth()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Invoice(  f1.getOutstandingAmount() + f2.getOutstandingAmount(),f1.getTotalInvoiceValueWoRetention() + f2.getTotalInvoiceValueWoRetention() ,f1.getQuater() ,f1.getMonth(),f1.getYear())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());	
		}else if(retention.equals("WITH-RETENTION")) {
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getMonth()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			        		   .reduce((f1,f2) -> new Invoice(f1.getOutstandingAmount() + f2.getOutstandingAmount(),f1.getQuater() ,f1.getMonth(),f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
		}
	
		 return invoicesList;		
	}
	public List<Invoice> getAllRetention(){
		List<Invoice> invoices =(List<Invoice>) invoiceRepository.findAll();
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getStateId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Retention(f1.getStateId(),f1.getStateName(),f1.getNetRetentionAmount() + f2.getNetRetentionAmount())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	public List<Invoice> getAllRetentionByStateName(String stateName) {
		List<Invoice> invoices = invoiceRepository.findByStateName(stateName);	
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getCircleCode()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Retention(f1.getStateId(),f1.getStateName(),f1.getCircleCode(),f1.getNetRetentionAmount() + f2.getNetRetentionAmount())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	
	public List<Invoice> getAllRetentionByCircleCode(String circleCode) {
		List<Invoice> invoices = invoiceRepository.findByCircleCode(circleCode); 
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getDistrictId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Retention(f1.getCircleCode(),f1.getDistrictId(), f1.getDistrictName(),f1.getNetRetentionAmount() + f2.getNetRetentionAmount())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	
	public List<Invoice> getAllRetentionByDistrictName(String districtName) {
		List<Invoice> invoices = invoiceRepository.findByDistrictName(districtName);;
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getQuater()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Retention(f1.getDistrictName(),f1.getQuater(),f1.getNetRetentionAmount() + f2.getNetRetentionAmount())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;
	}
	
	public List<Invoice> getAllRetentionByInvoiceType(String key,String value){
		List<Invoice> invoices = new ArrayList<Invoice>();
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findAll();
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateName(value);	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCode(value);	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictName(value);;	
		}
	
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getInvoiceTypeId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new Retention(f1.getInvoiceTypeName(),f1.getNetRetentionAmount() + f2.getNetRetentionAmount())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	
	
	public List<Invoice> getAllRetentionQuater(String key,String value){

			List<Invoice> invoicesList = new ArrayList<Invoice>();
	
			List<Invoice> invoices = new ArrayList<Invoice>();
			if(key.equals("ALL")) {
				invoices = (List<Invoice>) invoiceRepository.findAll();
			}
			if(key.equals("STATE")) {
				invoices = invoiceRepository.findByStateName(value);	
			}
			if(key.equals("CIRCLE")) {
				invoices = invoiceRepository.findByCircleCode(value);	
			}
			if(key.equals("DISTRICT")) {
				invoices = invoiceRepository.findByDistrictName(value);
			}
		
				invoicesList = invoices.stream()
				          // .collect(Collectors.groupingBy(foo ->foo.getQuater()))
						  .collect(Collectors.groupingBy(foo -> Pair.of(foo.getQuater(), foo.getYear()))) 
				           .entrySet().stream()
				           .map(e -> e.getValue().stream()
				                   .reduce((f1,f2) -> new Retention(f1.getNetRetentionAmount() + f2.getNetRetentionAmount(),f1.getQuater() ,f1.getYear())))
				                   .map(f -> f.get())
				                   .collect(Collectors.toList());	  
				invoicesList.sort((i1,i2)->i2.getQuater().compareTo(i1.getQuater()));
				invoicesList.sort((i1, i2) -> i2.getYear().compareTo(i1.getYear()));
			 return invoicesList;
	}
	
	public List<Invoice> getAllRetentionMonth(String key,String value,String quater,String year){

		List<Invoice> invoicesList = new ArrayList<Invoice>();

		List<Invoice> invoices = new ArrayList<Invoice>();
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findByQuaterAndYear(quater,year);
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateNameAndQuaterAndYear(value,quater,year);	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCodeAndQuaterAndYear(value,quater,year);	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictNameAndQuaterAndYear(value,quater,year);
		}
	
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getMonth()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new Retention(f1.getNetRetentionAmount() + f2.getNetRetentionAmount(),f1.getQuater() ,f1.getMonth(),f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	  
		 return invoicesList;
}

	
	public List<Invoice> getAllRevenueAnalysisLastQuater(){
		List<Invoice> invoices =(List<Invoice>) invoiceRepository.findByYear("2021");
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getStateId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getStateId(),f1.getStateName(),f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());
		 System.out.println(invoicesList);
		 return invoicesList;		
	}
	public List<Invoice> getAllRevenueAnalysisLastQuaterByStateName(String stateName) {
		List<Invoice> invoices = invoiceRepository.findByStateNameAndYear(stateName ,"2021");	
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getCircleCode()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getStateId(),f1.getStateName(),f1.getCircleCode(),f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	public List<Invoice> getAllRevenueAnalysisLastQuaterByCircleCode(String circleCode) {
		List<Invoice> invoices = invoiceRepository.findByCircleCodeAndYear(circleCode,"2021"); 
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getDistrictId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getCircleCode(),f1.getDistrictId(), f1.getDistrictName(),f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	
	public List<Invoice> getAllRevenueAnalysisLastQuaterByDistrictName(String districtName) {
		List<Invoice> invoices = invoiceRepository.findByDistrictNameAndYear(districtName,"2021");
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getQuater()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getDistrictName(),f1.getQuater(),f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;
	}
	
	public List<Invoice> getAllRevenueAnalysisLastQuaterByInvoiceType(String key,String value){
		List<Invoice> invoices = new ArrayList<Invoice>();
		if(key.equals("ALL")) {
			invoices = (List<Invoice>) invoiceRepository.findByYear("2021");
		}
		if(key.equals("STATE")) {
			invoices = invoiceRepository.findByStateNameAndYear(value,"2021");	
		}
		if(key.equals("CIRCLE")) {
			invoices = invoiceRepository.findByCircleCodeAndYear(value,"2021");	
		}
		if(key.equals("DISTRICT")) {
			invoices = invoiceRepository.findByDistrictNameAndYear(value,"2021");	
		}
	
		 List<Invoice> invoicesList = invoices.stream()
           .collect(Collectors.groupingBy(foo -> foo.getInvoiceTypeId()))
           .entrySet().stream()
           .map(e -> e.getValue().stream()
                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getInvoiceTypeName(),f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue())))
                   .map(f -> f.get())
                   .collect(Collectors.toList());		 
		 return invoicesList;		
	}
	
	
	public List<Invoice> getAllRevenueAnalysisLastQuaterQuater(String key,String value){
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<Invoice> invoicesList = new ArrayList<Invoice>();
			if(key.equals("ALL")) {
				invoices = (List<Invoice>) invoiceRepository.findByYear("2021");
			}
			if(key.equals("STATE")) {
				invoices = invoiceRepository.findByStateNameAndYear(value,"2021");	
			}
			if(key.equals("CIRCLE")) {
				invoices = invoiceRepository.findByCircleCodeAndYear(value,"2021");	
			}
			if(key.equals("DISTRICT")) {
				invoices = invoiceRepository.findByDistrictNameAndYear(value,"2021");	
			}
			
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getQuater()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue(),f1.getQuater() ,f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
			invoicesList.sort((i1,i2)->i2.getQuater().compareTo(i1.getQuater()));
			 return invoicesList;		
	}

	
	
	public List<Invoice> getAllRevenueAnalysisLastQuaterMonth(String key,String value,String quater,String year){
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<Invoice> invoicesList = new ArrayList<Invoice>();
			if(key.equals("ALL")) {
				invoices = (List<Invoice>) invoiceRepository.findByQuaterAndYear(quater,year);
			}
			if(key.equals("STATE")) {
				invoices = invoiceRepository.findByStateNameAndQuaterAndYear(value,quater,year);	
			}
			if(key.equals("CIRCLE")) {
				invoices = invoiceRepository.findByCircleCodeAndQuaterAndYear(value,quater,year);	
			}
			if(key.equals("DISTRICT")) {
				invoices = invoiceRepository.findByDistrictNameAndQuaterAndYear(value,quater,year);	
			}
			
			invoicesList = invoices.stream()
			           .collect(Collectors.groupingBy(foo ->foo.getMonth()))
			           .entrySet().stream()
			           .map(e -> e.getValue().stream()
			                   .reduce((f1,f2) -> new RevenueAnalysisLastQuater(f1.getTotalInvoiceValue() + f2.getTotalInvoiceValue(),f1.getQuater() ,f1.getMonth() ,f1.getYear())))
			                   .map(f -> f.get())
			                   .collect(Collectors.toList());	
			 return invoicesList;		
	}
	public List<Invoice> getConsolidatedReport(String stateName,List<String>invoiceTypeName, List<String> month,List<String> year){	
		if(month.equals("null")) {
			month = null;
		}
		if(year.equals("null")) {
			year = null;
		}
		System.out.println(stateName);
		List<Invoice> invoices =  invoiceRepository.findByConsolidatedReport(stateName,invoiceTypeName, month,year);	            	
		return invoices;			
	}
}
