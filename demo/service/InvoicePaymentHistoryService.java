package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.Fin09;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoicePaymentHistory;
import com.example.demo.EntityModel.NumberingSequence;
import com.example.demo.repository.Fin09Repository;
import com.example.demo.repository.InvoicePaymentHistoryRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.NumberingSequenceRepository;
//
@Service
public class InvoicePaymentHistoryService {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	private InvoiceRepository invocieRepository;
	
	@Autowired
	private Fin09Repository fin09Repository;
	
	@Autowired
	private InvoicePaymentHistoryRepository invoicePaymentHistoryRepository;
	
	@Autowired
	private NumberingSequenceRepository numberingSequenceRepository;
	
	public String getPaymentModeCode(String paymentMode) {
		String paymentModeCode = "";
		if(paymentMode.equals("Bank Receipting")) { paymentModeCode = "BR"; }
		else if(paymentMode.equals("Sundry Advances")) { paymentModeCode = "SA"; }
		else if(paymentMode.equals("Credit Note")) { paymentModeCode = "CN"; }
		else if(paymentMode.equals("Debit Note")) { paymentModeCode = "DN"; }
		return paymentModeCode;
	}
	
	public InvoicePaymentHistory findByIdService(int id) {
		
		InvoicePaymentHistory invoicePaymentHistory = invoicePaymentHistoryRepository.findByPaymentId(id);
		
		return invoicePaymentHistory;
	}
	
	public InvoicePaymentHistory paymentForInvoice(InvoicePaymentHistory invoicePaymentHistory) {
		invoicePaymentHistoryRepository.save(invoicePaymentHistory);
		invoicePaymentHistory.setTransactionRefNo(getPaymentModeCode(invoicePaymentHistory.getPaymentMode()) + "-" + formatter.format(invoicePaymentHistory.getPaymentDate()) + "-" +invoicePaymentHistory.getId() );
		invoicePaymentHistoryRepository.save(invoicePaymentHistory);
		Invoice invoice = invocieRepository.findByInvoiceNo(invoicePaymentHistory.getInvoiceNo());
		invoice.setOutstandingAmount(Double.valueOf(df.format(invoice.getOutstandingAmount() - invoicePaymentHistory.getPaymentReceived())));		
		if(invoice.getOutstandingAmount() == 0.0) {
			invoice.setPaymentStatus("PAYMENT-RECEIVED");
		}
		if(invoice.getTotalInvoiceValueWoRetention() == null) {
			invoice.setTotalInvoiceValueWoRetention(0.0);
		}
		if(invoice.getPaymentReceived() == null) {
			invoice.setPaymentReceived(0.0);
		}
		invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format((invoice.getTotalInvoiceValueWoRetention() - invoicePaymentHistory.getPaymentReceived() ))));
		if(invoice.getTotalInvoiceValueWoRetention() < 0) {
			invoice.setTotalInvoiceValueWoRetention(0.0);
		}
		//setting netRetention amount dont want to add explicitly for fin02 & 2B
		if(invoice.getTotalInvoiceValueWoRetention()==0 && invoice.getOutstandingAmount()<invoice.getRetentionAmount()) {
			invoice.setNetRetentionAmount(invoice.getOutstandingAmount());
		}
		
		invoice.setPaymentReceived(Double.valueOf(df.format(invoice.getPaymentReceived() + invoicePaymentHistory.getPaymentReceived())));
		invocieRepository.save(invoice);
		return invoicePaymentHistory;
	}
		
//	debitservice
	public InvoicePaymentHistory addPaymentForDebit(InvoicePaymentHistory invoicePaymentHistory) {
		invoicePaymentHistoryRepository.save(invoicePaymentHistory);
		invoicePaymentHistory.setTransactionRefNo(getPaymentModeCode(invoicePaymentHistory.getPaymentMode()) + "-" + formatter.format(invoicePaymentHistory.getPaymentDate()) + "-" +invoicePaymentHistory.getId() );
		invoicePaymentHistoryRepository.save(invoicePaymentHistory);
		Invoice invoice = invocieRepository.findByInvoiceNo(invoicePaymentHistory.getInvoiceNo());
		invoice.setOutstandingAmount(Double.valueOf(df.format(invoice.getOutstandingAmount() + invoicePaymentHistory.getPaymentReceived())));	
		//invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValueWoRetention() + invoicePaymentHistory.getPaymentReceived())));	
		invoice.setDebitNoteEntry(Double.valueOf(df.format(invoice.getDebitNoteEntry() + invoicePaymentHistory.getPaymentReceived())));	
//		if(invoice.getOutstandingAmount() == 0.0) {
//			invoice.setPaymentStatus("PAYMENT-RECEIVED");
//		}
		if(invoice.getTotalInvoiceValueWoRetention() == null) {
			invoice.setTotalInvoiceValueWoRetention(0.0);
		}
		if(invoice.getPaymentReceived() == null) {
			invoice.setPaymentReceived(0.0);
		}
		invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format((invoice.getTotalInvoiceValueWoRetention() + invoicePaymentHistory.getPaymentReceived() ))));
		if(invoice.getTotalInvoiceValueWoRetention() < 0) {
			invoice.setTotalInvoiceValueWoRetention(0.0);
		}
		//invoice.setPaymentReceived(Double.valueOf(df.format(invoice.getPaymentReceived() + invoicePaymentHistory.getPaymentReceived())));
		invocieRepository.save(invoice);
		return invoicePaymentHistory;
	}
	
	public List<InvoicePaymentHistory> penaltyPaymentToInvoice(List<InvoicePaymentHistory> invoicePaymentHistory){
		LocalDate date = LocalDate.now();
		List<NumberingSequence> numbSeq = (List<NumberingSequence>) numberingSequenceRepository.findAll();
		numbSeq.get(0).setCreditNoteSequence(numbSeq.get(0).getCreditNoteSequence() + 1);
		numberingSequenceRepository.save(numbSeq.get(0));
		String transactionRefNo = "CN" + "-" +formatter.format(date) + "-" + numbSeq.get(0).getCreditNoteSequence();
		System.out.print(transactionRefNo);
		invoicePaymentHistory.forEach(invoicePaymentHistories -> {
			invoicePaymentHistories.setPaymentDate(date);
			Fin09 fin09 = fin09Repository.findByCodes(invoicePaymentHistories.getFin09InvoiceNo());
			invoicePaymentHistories.setPaymentRefNo(fin09.getRemarks());
			fin09.setTotalPenalty(Double.valueOf(df.format(fin09.getTotalPenalty() - invoicePaymentHistories.getPaymentReceived())));
			if(fin09.getTotalPenalty() == 0.0) {
			fin09.setStatus("PENALTY ADJUSTED");
			}
			fin09Repository.save(fin09);
			invoicePaymentHistories.setTransactionRefNo(transactionRefNo);
			invoicePaymentHistoryRepository.save(invoicePaymentHistories);
			
			
			Invoice invoice = invocieRepository.findByInvoiceNo(invoicePaymentHistories.getInvoiceNo());
			invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getNetRetentionAmount() - invoicePaymentHistories.getPaymentReceived())));
			invoice.setOutstandingAmount(Double.valueOf(df.format(invoice.getOutstandingAmount() - invoicePaymentHistories.getPaymentReceived())));
			if(invoice.getOutstandingAmount() == 0.0) {
				invoice.setPaymentStatus("PAYMENT-RECEIVED");
			}
			if(invoice.getTotalInvoiceValueWoRetention() == null) {
				invoice.setTotalInvoiceValueWoRetention(0.0);
			}
			if(invoice.getPaymentReceived() == null) {
				invoice.setPaymentReceived(0.0);
			}
			//invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValueWoRetention() - invoicePaymentHistories.getPaymentReceived())));
//			if(invoice.getTotalInvoiceValueWoRetention() < 0) {
//				invoice.setTotalInvoiceValueWoRetention(0.0);
//			}
			invoice.setPaymentReceived(Double.valueOf(df.format(invoice.getPaymentReceived() + invoicePaymentHistories.getPaymentReceived())));
			invocieRepository.save(invoice);
		});
		return invoicePaymentHistory;
	}
	
	public List<InvoicePaymentHistory> penaltyPaymentToInvoiceSpecial(List<InvoicePaymentHistory> invoicePaymentHistory){
		LocalDate date = LocalDate.now();
		List<NumberingSequence> numbSeq = (List<NumberingSequence>) numberingSequenceRepository.findAll();
		numbSeq.get(0).setCreditNoteSequence(numbSeq.get(0).getCreditNoteSequence() + 1);
		numberingSequenceRepository.save(numbSeq.get(0));
		String transactionRefNo = "CN" + "-" +formatter.format(date) + "-" + numbSeq.get(0).getCreditNoteSequence();
		System.out.print(transactionRefNo);
		invoicePaymentHistory.forEach(invoicePaymentHistories -> {
			invoicePaymentHistories.setPaymentDate(date);
			Fin09 fin09 = fin09Repository.findByCodes(invoicePaymentHistories.getFin09InvoiceNo());
			invoicePaymentHistories.setPaymentRefNo(fin09.getRemarks());
			fin09.setTotalPenalty(Double.valueOf(df.format(fin09.getTotalPenalty() - invoicePaymentHistories.getPaymentReceived())));
			if(fin09.getTotalPenalty() == 0.0) {
			fin09.setStatus("PENALTY ADJUSTED");
			}
			fin09Repository.save(fin09);
			invoicePaymentHistories.setTransactionRefNo(transactionRefNo);
			invoicePaymentHistoryRepository.save(invoicePaymentHistories);
			
			
			Invoice invoice = invocieRepository.findByInvoiceNo(invoicePaymentHistories.getInvoiceNo());
				invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getNetRetentionAmount() - invoicePaymentHistories.getPaymentReceived())));
				invoice.setOutstandingAmount(Double.valueOf(df.format(invoice.getOutstandingAmount() - invoicePaymentHistories.getPaymentReceived())));
			
	      //  System.out.println("Net 1-"+invoice.getNetRetentionAmount());
		    if(invoice.getNetRetentionAmount() < 0) {
		    	//System.out.println("Net 2-"+invoice.getNetRetentionAmount());
		    	invoice.setNetRetentionAmount(0.0);
		    }
			if(invoice.getOutstandingAmount() == 0.0) {
				invoice.setPaymentStatus("PAYMENT-RECEIVED");
			}
			if(invoice.getTotalInvoiceValueWoRetention() == null) {
				invoice.setTotalInvoiceValueWoRetention(0.0);
			}
			if(invoice.getPaymentReceived() == null) {
				invoice.setPaymentReceived(0.0);
			}
			
			Double remainingAmount = invoicePaymentHistories.getPaymentReceived();
            remainingAmount = remainingAmount - invoice.getNetRetentionAmount();
            System.out.println("RemAmnt-"+remainingAmount);
           
            if(remainingAmount > 0) {
                System.out.println("NetRemAmnt-"+ invoice.getTotalInvoiceValueWoRetention());
                invoice.setTotalInvoiceValueWoRetention(invoice.getTotalInvoiceValueWoRetention() - remainingAmount);
                System.out.println("NetRemAmnt1-"+ invoice.getTotalInvoiceValueWoRetention());
            
            
			invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValueWoRetention() - invoicePaymentHistories.getPaymentReceived())));
			if(invoice.getTotalInvoiceValueWoRetention() < 0) {
				invoice.setTotalInvoiceValueWoRetention(0.0);
			}
			
            }
            
			invoice.setPaymentReceived(Double.valueOf(df.format(invoice.getPaymentReceived() + invoicePaymentHistories.getPaymentReceived())));
			invocieRepository.save(invoice);
		});
		return invoicePaymentHistory;
	}
	
	
	public List<InvoicePaymentHistory> paymentForMultipleInvoice(List<InvoicePaymentHistory> invoicePaymentHistories){
		LocalDate date = LocalDate.now();
		List<NumberingSequence> numbSeq = (List<NumberingSequence>) numberingSequenceRepository.findAll();
		numbSeq.get(0).setBankReceiptingSequence(numbSeq.get(0).getBankReceiptingSequence() + 1);
		numberingSequenceRepository.save(numbSeq.get(0));
		String transactionRefNo = "BR" + "-" +formatter.format(invoicePaymentHistories.get(0).getPaymentDate()) + "-" + numbSeq.get(0).getBankReceiptingSequence();
		invoicePaymentHistories.forEach(invoicePaymentHistory -> {
			
			
			invoicePaymentHistory.setTransactionRefNo(transactionRefNo);
			invoicePaymentHistory.setUpdatedBy(invoicePaymentHistories.get(0).getUpdatedBy());
			invoicePaymentHistoryRepository.save(invoicePaymentHistory);
			Invoice invoice = invocieRepository.findByInvoiceNo(invoicePaymentHistory.getInvoiceNo());
			invoice.setUpdatedBy(invoicePaymentHistories.get(0).getUpdatedBy());
			invoice.setOutstandingAmount(Double.valueOf(df.format(invoice.getOutstandingAmount() - invoicePaymentHistory.getPaymentReceived())));
			if(invoice.getOutstandingAmount() == 0.0) {
				invoice.setPaymentStatus("PAYMENT-RECEIVED");
			}
			if(invoice.getTotalInvoiceValueWoRetention() == null) {
				invoice.setTotalInvoiceValueWoRetention(0.0);
			}
			if(invoice.getPaymentReceived() == null) {
				invoice.setPaymentReceived(0.0);
			}
			invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValueWoRetention() - invoicePaymentHistory.getPaymentReceived())));
			if(invoice.getTotalInvoiceValueWoRetention() < 0) {
				invoice.setTotalInvoiceValueWoRetention(0.0);
			}
			//setting netRetention amount dont want to add explicitly for fin02 & 2B
			if(invoice.getTotalInvoiceValueWoRetention()==0 && invoice.getOutstandingAmount()<invoice.getRetentionAmount()) {
				invoice.setNetRetentionAmount(invoice.getOutstandingAmount());
			}
			
			invoice.setPaymentReceived(Double.valueOf(df.format(invoice.getPaymentReceived() + invoicePaymentHistory.getPaymentReceived())));
			invocieRepository.save(invoice);
		});
		return invoicePaymentHistories;
	}

	public List<InvoicePaymentHistory> getCollectionReport(String stateName,String districtName,List<String> invoiceTypeName,String clinicTypeCode,String transactionRefNo,String paymentRefNo,String paymentMode,String dateFrom,String dateTo 
			){	
		System.out.print(stateName);
		// List<InvoicePaymentHistory> invoicePaymentHistory =  invoicePaymentHistoryRepository.findByStateId(stateId);
	List<InvoicePaymentHistory> invoicePaymentHistory =  invoicePaymentHistoryRepository.findByCollectionReport(stateName, dateFrom, dateTo,districtName,invoiceTypeName,clinicTypeCode,transactionRefNo,paymentRefNo,paymentMode);  	            	
		return invoicePaymentHistory;
		
		//districtName,invoiceTypeId,clinicTypeId,transactionRefNo,paymentRefNo,paymentMode,dateFrom,dateTo,
	}
	
	
	
	
	

}
