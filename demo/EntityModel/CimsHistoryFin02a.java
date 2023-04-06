package com.example.demo.EntityModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="cims_history_fin02a")
public class CimsHistoryFin02a implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_state_id")
	private Integer stateId;
	
	@Column(name="m_invoice_type_id")
	private Integer invoiceTypeId;
	@Formula("(select state.state_name from m_state state where state.id=m_state_id )")
	private String stateName;
	
	@Formula("(select state.state_code from m_state state where state.id=m_state_id )")
	private String stateCode;
	
	@Column(name="m_district_id")
	private Integer districtId;
	
	@Formula("(select district.district_name from m_district district where district.id=m_district_id )")
	private String districtName;
	
	@Formula("(select district.district_code from m_district district where district.id=m_district_id )")
	private String districtCode;
	
	@Column(name="m_clinic_type_id")
	private Integer clinicTypeId;
	
	@Formula("(select clinictype.clinic_type_code from m_clinic_type clinictype where clinictype.id=m_clinic_type_id )")
	private String clinicTypeCode;
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate date;
	private String assetName;
	private String beNumber;
	private String batchNumber;
	private String installmentNumber;
	private Double valueOfEquipment;
	private Double rentalCharges;
	private String tcCertificate;
	private String status;
	@Column(name="fin07_status")
	private String fin07Status;
	@Column(name="t_fin07_ref_no")
	private String fin07RefNo;
	@Column(name="fin03a_status")
	private String fin03aStatus;
	@Column(name="t_fin03a_ref_no")
	private String fin03aRefNo;
	@Column(name="fin02a_inv_status")
	private String fin02aInvStatus;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="m_clinic_id", insertable = false, updatable = false)
	private Clinic clinic;
	private String remarks;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public CimsHistoryFin02a() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CimsHistoryFin02a(Integer id, Integer stateId, Integer invoiceTypeId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode,
			Integer clinicId, String month, String year, LocalDate date, String assetName, String beNumber,
			String batchNumber, String installmentNumber, Double valueOfEquipment, Double rentalCharges,
			String tcCertificate, String status, String fin07Status, String fin07RefNo, String fin03aStatus,
			String fin03aRefNo, String fin02aInvStatus, Clinic clinic, String remarks, String createdBy,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.invoiceTypeId = invoiceTypeId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.clinicId = clinicId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.assetName = assetName;
		this.beNumber = beNumber;
		this.batchNumber = batchNumber;
		this.installmentNumber = installmentNumber;
		this.valueOfEquipment = valueOfEquipment;
		this.rentalCharges = rentalCharges;
		this.tcCertificate = tcCertificate;
		this.status = status;
		this.fin07Status = fin07Status;
		this.fin07RefNo = fin07RefNo;
		this.fin03aStatus = fin03aStatus;
		this.fin03aRefNo = fin03aRefNo;
		this.fin02aInvStatus = fin02aInvStatus;
		this.clinic = clinic;
		this.remarks = remarks;
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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getInvoiceTypeId() {
		return invoiceTypeId;
	}

	public void setInvoiceTypeId(Integer invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getClinicTypeId() {
		return clinicTypeId;
	}

	public void setClinicTypeId(Integer clinicTypeId) {
		this.clinicTypeId = clinicTypeId;
	}

	public String getClinicTypeCode() {
		return clinicTypeCode;
	}

	public void setClinicTypeCode(String clinicTypeCode) {
		this.clinicTypeCode = clinicTypeCode;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(String installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public Double getValueOfEquipment() {
		return valueOfEquipment;
	}

	public void setValueOfEquipment(Double valueOfEquipment) {
		this.valueOfEquipment = valueOfEquipment;
	}

	public Double getRentalCharges() {
		return rentalCharges;
	}

	public void setRentalCharges(Double rentalCharges) {
		this.rentalCharges = rentalCharges;
	}

	public String getTcCertificate() {
		return tcCertificate;
	}

	public void setTcCertificate(String tcCertificate) {
		this.tcCertificate = tcCertificate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFin07Status() {
		return fin07Status;
	}

	public void setFin07Status(String fin07Status) {
		this.fin07Status = fin07Status;
	}

	public String getFin07RefNo() {
		return fin07RefNo;
	}

	public void setFin07RefNo(String fin07RefNo) {
		this.fin07RefNo = fin07RefNo;
	}

	public String getFin03aStatus() {
		return fin03aStatus;
	}

	public void setFin03aStatus(String fin03aStatus) {
		this.fin03aStatus = fin03aStatus;
	}

	public String getFin03aRefNo() {
		return fin03aRefNo;
	}

	public void setFin03aRefNo(String fin03aRefNo) {
		this.fin03aRefNo = fin03aRefNo;
	}

	public String getFin02aInvStatus() {
		return fin02aInvStatus;
	}

	public void setFin02aInvStatus(String fin02aInvStatus) {
		this.fin02aInvStatus = fin02aInvStatus;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "CimsHistoryFin02A [id=" + id + ", stateId=" + stateId + ", invoiceTypeId=" + invoiceTypeId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId
				+ ", clinicTypeCode=" + clinicTypeCode + ", clinicId=" + clinicId + ", month=" + month + ", year="
				+ year + ", date=" + date + ", assetName=" + assetName + ", beNumber=" + beNumber + ", batchNumber="
				+ batchNumber + ", installmentNumber=" + installmentNumber + ", valueOfEquipment=" + valueOfEquipment
				+ ", rentalCharges=" + rentalCharges + ", tcCertificate=" + tcCertificate + ", status=" + status
				+ ", fin07Status=" + fin07Status + ", fin07RefNo=" + fin07RefNo + ", fin03aStatus=" + fin03aStatus
				+ ", fin03aRefNo=" + fin03aRefNo + ", fin02aInvStatus=" + fin02aInvStatus + ", clinic=" + clinic
				+ ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
}
