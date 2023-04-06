package com.example.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.InvoiceCounter;

@Service
public class InvoiceCounterService {

	@PersistenceContext
	private EntityManager entityManager;

	public InvoiceCounter callStoredProcedure(String finType) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("spInvoiceCounter");
		
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(7, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(8, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(9, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(10, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(11, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(12, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(13, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(14, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(15, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(16, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(17, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(18, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(19, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(20, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(21, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(22, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(23, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(24, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(25, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(26, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(27, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(28, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(29, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(30, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(31, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(32, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(33, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(34, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(35, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(36, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(37, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(38, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(39, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(40, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(41, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(42, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(43, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(44, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(45, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(46, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(47, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(48, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(49, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(50, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(51, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(52, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(53, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(54, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(55, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(56, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(57, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(58, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(59, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(60, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(61, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(62, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(63, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(64, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(65, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(66, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(67, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(68, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(69, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(70, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(71, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(72, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(73, Integer.class, ParameterMode.OUT);
		
		query.setParameter(1, finType);
		
		query.execute();


		InvoiceCounter invoiceCounter = new InvoiceCounter();
		invoiceCounter.setCurrMonTotal((Integer) query.getOutputParameterValue(2));
		invoiceCounter.setCurrMonEquipCount((Integer) query.getOutputParameterValue(3));
		invoiceCounter.setCurrMonSd1NotCreated((Integer) query.getOutputParameterValue(4));
		invoiceCounter.setCurrMonSd1InProgress((Integer) query.getOutputParameterValue(5));
		invoiceCounter.setCurrMonSd1Approved((Integer) query.getOutputParameterValue(6));
		invoiceCounter.setCurrMonSd2NotCreated((Integer) query.getOutputParameterValue(7));
		invoiceCounter.setCurrMonSd2InProgress((Integer) query.getOutputParameterValue(8));
		invoiceCounter.setCurrMonSd2Approved((Integer) query.getOutputParameterValue(9));
		invoiceCounter.setCurrMonSd3NotCreated((Integer) query.getOutputParameterValue(10));
		invoiceCounter.setCurrMonSd3InProgress((Integer) query.getOutputParameterValue(11));
		invoiceCounter.setCurrMonSd3Approved((Integer) query.getOutputParameterValue(12));
		invoiceCounter.setCurrMonSd4NotCreated((Integer) query.getOutputParameterValue(13));
		invoiceCounter.setCurrMonSd4InProgress((Integer) query.getOutputParameterValue(14));
		invoiceCounter.setCurrMonSd4Approved((Integer) query.getOutputParameterValue(15));
		invoiceCounter.setCurrMonInvNotCreated((Integer) query.getOutputParameterValue(16));
		invoiceCounter.setCurrMonInvInProgress((Integer) query.getOutputParameterValue(17));
		invoiceCounter.setCurrMonInvApproved((Integer) query.getOutputParameterValue(18));
		invoiceCounter.setOldMonTotal((Integer) query.getOutputParameterValue(19));
		invoiceCounter.setOldMonEquipCount((Integer) query.getOutputParameterValue(20));
		invoiceCounter.setOldMonSd1NotCreated((Integer) query.getOutputParameterValue(21));
		invoiceCounter.setOldMonSd1InProgress((Integer) query.getOutputParameterValue(22));
		invoiceCounter.setOldMonSd1Approved((Integer) query.getOutputParameterValue(23));
		invoiceCounter.setOldMonSd2NotCreated((Integer) query.getOutputParameterValue(24));
		invoiceCounter.setOldMonSd2InProgress((Integer) query.getOutputParameterValue(25));
		invoiceCounter.setOldMonSd2Approved((Integer) query.getOutputParameterValue(26));
		invoiceCounter.setOldMonSd3NotCreated((Integer) query.getOutputParameterValue(27));
		invoiceCounter.setOldMonSd3InProgress((Integer) query.getOutputParameterValue(28));
		invoiceCounter.setOldMonSd3Approved((Integer) query.getOutputParameterValue(29));
		invoiceCounter.setOldMonSd4NotCreated((Integer) query.getOutputParameterValue(30));
		invoiceCounter.setOldMonSd4InProgress((Integer) query.getOutputParameterValue(31));
		invoiceCounter.setOldMonSd4Approved((Integer) query.getOutputParameterValue(32));
		invoiceCounter.setOldMonInvNotCreated((Integer) query.getOutputParameterValue(33));
		invoiceCounter.setOldMonInvInProgress((Integer) query.getOutputParameterValue(34));
		invoiceCounter.setOldMonInvApproved((Integer) query.getOutputParameterValue(35));
		invoiceCounter.setCurrMonSd1NotCrEquipCount((Integer) query.getOutputParameterValue(36));
		invoiceCounter.setCurrMonSd1ProgEquipCount((Integer) query.getOutputParameterValue(37));
		invoiceCounter.setCurrMonSd1ApprEquipCount((Integer) query.getOutputParameterValue(38));
		invoiceCounter.setCurrMonSd2NotCrEquipCount((Integer) query.getOutputParameterValue(39));
		invoiceCounter.setCurrMonSd2ProgEquipCount((Integer) query.getOutputParameterValue(40));
		invoiceCounter.setCurrMonSd2ApprEquipCount((Integer) query.getOutputParameterValue(41));
		invoiceCounter.setCurrMonSd3NotCrEquipCount((Integer) query.getOutputParameterValue(42));
		invoiceCounter.setCurrMonSd3ProgEquipCount((Integer) query.getOutputParameterValue(43));
		invoiceCounter.setCurrMonSd3ApprEquipCount((Integer) query.getOutputParameterValue(44));
		invoiceCounter.setCurrMonSd4NotCrEquipCount((Integer) query.getOutputParameterValue(45));
		invoiceCounter.setCurrMonSd4ProgEquipCount((Integer) query.getOutputParameterValue(46));
		invoiceCounter.setCurrMonSd4ApprEquipCount((Integer) query.getOutputParameterValue(47));
		invoiceCounter.setCurrMonInvNotCrEquipCount((Integer) query.getOutputParameterValue(48));
		invoiceCounter.setCurrMonInvProgEquipCount((Integer) query.getOutputParameterValue(49));
		invoiceCounter.setCurrMonInvApprEquipCount((Integer) query.getOutputParameterValue(50));
		invoiceCounter.setOldMonSd1NotCrEquipCount((Integer) query.getOutputParameterValue(51));
		invoiceCounter.setOldMonSd1ProgEquipCount((Integer) query.getOutputParameterValue(52));
		invoiceCounter.setOldMonSd1ApprEquipCount((Integer) query.getOutputParameterValue(53));
		invoiceCounter.setOldMonSd2NotCrEquipCount((Integer) query.getOutputParameterValue(54));
		invoiceCounter.setOldMonSd2ProgEquipCount((Integer) query.getOutputParameterValue(55));
		invoiceCounter.setOldMonSd2ApprEquipCount((Integer) query.getOutputParameterValue(56));
		invoiceCounter.setOldMonSd3NotCrEquipCount((Integer) query.getOutputParameterValue(57));
		invoiceCounter.setOldMonSd3ProgEquipCount((Integer) query.getOutputParameterValue(58));
		invoiceCounter.setOldMonSd3ApprEquipCount((Integer) query.getOutputParameterValue(59));
		invoiceCounter.setOldMonSd4NotCrEquipCount((Integer) query.getOutputParameterValue(60));
		invoiceCounter.setOldMonSd4ProgEquipCount((Integer) query.getOutputParameterValue(61));
		invoiceCounter.setOldMonSd4ApprEquipCount((Integer) query.getOutputParameterValue(62));
		invoiceCounter.setOldMonInvNotCrEquipCount((Integer) query.getOutputParameterValue(63));
		invoiceCounter.setOldMonInvProgEquipCount((Integer) query.getOutputParameterValue(64));
		invoiceCounter.setOldMonInvApprEquipCount((Integer) query.getOutputParameterValue(65));
		invoiceCounter.setCurrMonTotal1((Integer) query.getOutputParameterValue(66));
		invoiceCounter.setCurrMonEquipCount1((Integer) query.getOutputParameterValue(67));
		invoiceCounter.setCurrMonTotal2((Integer) query.getOutputParameterValue(68));
		invoiceCounter.setCurrMonEquipCount2((Integer) query.getOutputParameterValue(69));
		invoiceCounter.setOldMonTotal1((Integer) query.getOutputParameterValue(70));
		invoiceCounter.setOldMonEquipCount1((Integer) query.getOutputParameterValue(71));
		invoiceCounter.setOldMonTotal2((Integer) query.getOutputParameterValue(72));
		invoiceCounter.setOldMonEquipCount2((Integer) query.getOutputParameterValue(73));
		return invoiceCounter;
	}

	public InvoiceCounter getFin01InvoiceCounter() {
		return callStoredProcedure("FIN01");
	}

	public InvoiceCounter getFin02AInvoiceCounter() {
		return callStoredProcedure("FIN02A");
	}
	
	public InvoiceCounter getFin02InvoiceCounter() {
		return callStoredProcedure("FIN02");
	}
	
	public InvoiceCounter getFin02BInvoiceCounter() {
		return callStoredProcedure("FIN02B");
	}

}
