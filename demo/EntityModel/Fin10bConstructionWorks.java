package com.example.demo.EntityModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_fin10b_construction_works")
public class Fin10bConstructionWorks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_clinic_id")
	private Integer clinicId;
	@CreationTimestamp
	private LocalDate date;
	private String assetName;
	private String beNumber;
	private String typeOfExpense;
	private String nameOfSupplier;
	private Double cwFinalBqCost;
	private String tenderRefNo;
	private String letterOfAwardOrContractRefNo;
	private String certOfPracticalCompletionRefNo;
	private String certOfTcNo;
	private String certOfAcceptanceRef;
	private Double agreedTotal;
	@Column(name="t_fin10b_ref_no")
	private String fin10bRefNo;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	public Fin10bConstructionWorks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fin10bConstructionWorks(Integer id, Integer clinicId, LocalDate date, String assetName, String beNumber,
			String typeOfExpense, String nameOfSupplier, Double cwFinalBqCost, String tenderRefNo,
			String letterOfAwardOrContractRefNo, String certOfPracticalCompletionRefNo, String certOfTcNo,
			String certOfAcceptanceRef, Double agreedTotal, String fin10bRefNo, String createdBy,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.clinicId = clinicId;
		this.date = date;
		this.assetName = assetName;
		this.beNumber = beNumber;
		this.typeOfExpense = typeOfExpense;
		this.nameOfSupplier = nameOfSupplier;
		this.cwFinalBqCost = cwFinalBqCost;
		this.tenderRefNo = tenderRefNo;
		this.letterOfAwardOrContractRefNo = letterOfAwardOrContractRefNo;
		this.certOfPracticalCompletionRefNo = certOfPracticalCompletionRefNo;
		this.certOfTcNo = certOfTcNo;
		this.certOfAcceptanceRef = certOfAcceptanceRef;
		this.agreedTotal = agreedTotal;
		this.fin10bRefNo = fin10bRefNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClinicId() {
		return clinicId;
	}
	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getBeNumber() {
		return beNumber;
	}
	public void setBeNumber(String beNumber) {
		this.beNumber = beNumber;
	}
	public String getTypeOfExpense() {
		return typeOfExpense;
	}
	public void setTypeOfExpense(String typeOfExpense) {
		this.typeOfExpense = typeOfExpense;
	}
	public String getNameOfSupplier() {
		return nameOfSupplier;
	}
	public void setNameOfSupplier(String nameOfSupplier) {
		this.nameOfSupplier = nameOfSupplier;
	}
	public Double getCwFinalBqCost() {
		return cwFinalBqCost;
	}
	public void setCwFinalBqCost(Double cwFinalBqCost) {
		this.cwFinalBqCost = cwFinalBqCost;
	}
	public String getTenderRefNo() {
		return tenderRefNo;
	}
	public void setTenderRefNo(String tenderRefNo) {
		this.tenderRefNo = tenderRefNo;
	}
	public String getLetterOfAwardOrContractRefNo() {
		return letterOfAwardOrContractRefNo;
	}
	public void setLetterOfAwardOrContractRefNo(String letterOfAwardOrContractRefNo) {
		this.letterOfAwardOrContractRefNo = letterOfAwardOrContractRefNo;
	}
	public String getCertOfPracticalCompletionRefNo() {
		return certOfPracticalCompletionRefNo;
	}
	public void setCertOfPracticalCompletionRefNo(String certOfPracticalCompletionRefNo) {
		this.certOfPracticalCompletionRefNo = certOfPracticalCompletionRefNo;
	}
	public String getCertOfTcNo() {
		return certOfTcNo;
	}
	public void setCertOfTcNo(String certOfTcNo) {
		this.certOfTcNo = certOfTcNo;
	}
	public String getCertOfAcceptanceRef() {
		return certOfAcceptanceRef;
	}
	public void setCertOfAcceptanceRef(String certOfAcceptanceRef) {
		this.certOfAcceptanceRef = certOfAcceptanceRef;
	}
	public Double getAgreedTotal() {
		return agreedTotal;
	}
	public void setAgreedTotal(Double agreedTotal) {
		this.agreedTotal = agreedTotal;
	}
	public String getFin10bRefNo() {
		return fin10bRefNo;
	}
	public void setFin10bRefNo(String fin10bRefNo) {
		this.fin10bRefNo = fin10bRefNo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Fin10bConstructionWorks [id=" + id + ", clinicId=" + clinicId + ", date=" + date + ", assetName="
				+ assetName + ", beNumber=" + beNumber + ", typeOfExpense=" + typeOfExpense + ", nameOfSupplier="
				+ nameOfSupplier + ", cwFinalBqCost=" + cwFinalBqCost + ", tenderRefNo=" + tenderRefNo
				+ ", letterOfAwardOrContractRefNo=" + letterOfAwardOrContractRefNo + ", certOfPracticalCompletionRefNo="
				+ certOfPracticalCompletionRefNo + ", certOfTcNo=" + certOfTcNo + ", certOfAcceptanceRef="
				+ certOfAcceptanceRef + ", agreedTotal=" + agreedTotal + ", fin10bRefNo=" + fin10bRefNo + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}
	
	

}
