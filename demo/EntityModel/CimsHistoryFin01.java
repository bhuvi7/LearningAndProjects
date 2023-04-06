package com.example.demo.EntityModel;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="cims_history_fin01")
public class CimsHistoryFin01 implements Serializable {
	
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
	
	@Formula("(select district.m_circle_id from m_district district where district.id=m_district_id )")
	private Integer circleId;
	
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
	private String serialNumber;
	private String modelNumber;
	private Double purchaseAmount;
	private Date installedDate;
	private Date purchasedDate;
	private Date acceptedDate;
	private String tcCertificate;
	private String status;
	@Column(name="is_fin06_created")
	private String isFin06Created;
	@Column(name="fin06_status")
	private String fin06Status;
	@Column(name="t_fin06_ref_no")
	private String fin06RefNo;
	@Column(name="fin01_status")
	private String fin01Status;
	@Column(name="t_fin01_ref_no")
	private String fin01RefNo;
	@Column(name="fin01_inv_status")
	private String fin01InvStatus;
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
	
	public CimsHistoryFin01() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CimsHistoryFin01(Integer id, Integer stateId, Integer invoiceTypeId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer circleId, Integer clinicTypeId,
			String clinicTypeCode, Integer clinicId, String month, String year, LocalDate date, String assetName,
			String beNumber, String serialNumber, String modelNumber, Double purchaseAmount, Date installedDate,
			Date purchasedDate, Date acceptedDate, String tcCertificate, String status, String isFin06Created,
			String fin06Status, String fin06RefNo, String fin01Status, String fin01RefNo, String fin01InvStatus,
			Clinic clinic, String remarks, String createdBy, LocalDateTime createdDate, String updatedBy,
			LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.invoiceTypeId = invoiceTypeId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.circleId = circleId;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.clinicId = clinicId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.assetName = assetName;
		this.beNumber = beNumber;
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.purchaseAmount = purchaseAmount;
		this.installedDate = installedDate;
		this.purchasedDate = purchasedDate;
		this.acceptedDate = acceptedDate;
		this.tcCertificate = tcCertificate;
		this.status = status;
		this.isFin06Created = isFin06Created;
		this.fin06Status = fin06Status;
		this.fin06RefNo = fin06RefNo;
		this.fin01Status = fin01Status;
		this.fin01RefNo = fin01RefNo;
		this.fin01InvStatus = fin01InvStatus;
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

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Date getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(Date installedDate) {
		this.installedDate = installedDate;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
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

	public String getIsFin06Created() {
		return isFin06Created;
	}

	public void setIsFin06Created(String isFin06Created) {
		this.isFin06Created = isFin06Created;
	}

	public String getFin06Status() {
		return fin06Status;
	}

	public void setFin06Status(String fin06Status) {
		this.fin06Status = fin06Status;
	}

	public String getFin06RefNo() {
		return fin06RefNo;
	}

	public void setFin06RefNo(String fin06RefNo) {
		this.fin06RefNo = fin06RefNo;
	}

	public String getFin01Status() {
		return fin01Status;
	}

	public void setFin01Status(String fin01Status) {
		this.fin01Status = fin01Status;
	}

	public String getFin01RefNo() {
		return fin01RefNo;
	}

	public void setFin01RefNo(String fin01RefNo) {
		this.fin01RefNo = fin01RefNo;
	}

	public String getFin01InvStatus() {
		return fin01InvStatus;
	}

	public void setFin01InvStatus(String fin01InvStatus) {
		this.fin01InvStatus = fin01InvStatus;
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
		return "CimsHistoryFin01 [id=" + id + ", stateId=" + stateId + ", invoiceTypeId=" + invoiceTypeId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", circleId=" + circleId
				+ ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode=" + clinicTypeCode + ", clinicId=" + clinicId
				+ ", month=" + month + ", year=" + year + ", date=" + date + ", assetName=" + assetName + ", beNumber="
				+ beNumber + ", serialNumber=" + serialNumber + ", modelNumber=" + modelNumber + ", purchaseAmount="
				+ purchaseAmount + ", installedDate=" + installedDate + ", purchasedDate=" + purchasedDate
				+ ", acceptedDate=" + acceptedDate + ", tcCertificate=" + tcCertificate + ", status=" + status
				+ ", isFin06Created=" + isFin06Created + ", fin06Status=" + fin06Status + ", fin06RefNo=" + fin06RefNo
				+ ", fin01Status=" + fin01Status + ", fin01RefNo=" + fin01RefNo + ", fin01InvStatus=" + fin01InvStatus
				+ ", clinic=" + clinic + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
	
	
	
		

}
