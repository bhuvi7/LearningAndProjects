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
@Table(name="cims_history_fin08c")
public class CimsHistoryFin08c implements Serializable {
	
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
	
	@Formula("(select clinic.clinic_code from m_clinic clinic where clinic.id=m_clinic_id )")
	private String clinicCode;
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate date;
	private String assetName;
	private String beNumber;
	private Double valueOfEquipment;
	private Double maintenanceCharges;
	private String status;
	@Column(name="fin08c_status")
	private String fin08cStatus;
	@Column(name="t_fin08c_ref_no")
	private String fin08cRefNo;
	@Column(name="t_fin08_ref_no")
	private String fin08RefNo;
	@Column(name="fin08_status")
	private String fin08Status;
	@Column(name="t_fin03_ref_no")
	private String fin03RefNo;
	@Column(name="fin03_status")
	private String fin03Status;
	@Column(name="t_fin02_inv_no")
	private String fin02InvNo;
	@Column(name="fin02_inv_status")
	private String fin02InvStatus;
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
	
	public CimsHistoryFin08c() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CimsHistoryFin08c(Integer id, Integer stateId, Integer invoiceTypeId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer circleId, Integer clinicTypeId,
			String clinicTypeCode, String clinicCode, Integer clinicId, String month, String year, LocalDate date,
			String assetName, String beNumber, Double valueOfEquipment, Double maintenanceCharges, String status,
			String fin08cStatus, String fin08cRefNo, String fin08RefNo, String fin08Status, String fin03RefNo,
			String fin03Status, String fin02InvNo, String fin02InvStatus, Clinic clinic, String remarks,
			String createdBy, LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
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
		this.clinicCode = clinicCode;
		this.clinicId = clinicId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.assetName = assetName;
		this.beNumber = beNumber;
		this.valueOfEquipment = valueOfEquipment;
		this.maintenanceCharges = maintenanceCharges;
		this.status = status;
		this.fin08cStatus = fin08cStatus;
		this.fin08cRefNo = fin08cRefNo;
		this.fin08RefNo = fin08RefNo;
		this.fin08Status = fin08Status;
		this.fin03RefNo = fin03RefNo;
		this.fin03Status = fin03Status;
		this.fin02InvNo = fin02InvNo;
		this.fin02InvStatus = fin02InvStatus;
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

	public String getClinicCode() {
		return clinicCode;
	}

	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
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

	public Double getValueOfEquipment() {
		return valueOfEquipment;
	}

	public void setValueOfEquipment(Double valueOfEquipment) {
		this.valueOfEquipment = valueOfEquipment;
	}

	public Double getMaintenanceCharges() {
		return maintenanceCharges;
	}

	public void setMaintenanceCharges(Double maintenanceCharges) {
		this.maintenanceCharges = maintenanceCharges;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFin08cStatus() {
		return fin08cStatus;
	}

	public void setFin08cStatus(String fin08cStatus) {
		this.fin08cStatus = fin08cStatus;
	}

	public String getFin08cRefNo() {
		return fin08cRefNo;
	}

	public void setFin08cRefNo(String fin08cRefNo) {
		this.fin08cRefNo = fin08cRefNo;
	}

	public String getFin08RefNo() {
		return fin08RefNo;
	}

	public void setFin08RefNo(String fin08RefNo) {
		this.fin08RefNo = fin08RefNo;
	}

	public String getFin08Status() {
		return fin08Status;
	}

	public void setFin08Status(String fin08Status) {
		this.fin08Status = fin08Status;
	}

	public String getFin03RefNo() {
		return fin03RefNo;
	}

	public void setFin03RefNo(String fin03RefNo) {
		this.fin03RefNo = fin03RefNo;
	}

	public String getFin03Status() {
		return fin03Status;
	}

	public void setFin03Status(String fin03Status) {
		this.fin03Status = fin03Status;
	}

	public String getFin02InvNo() {
		return fin02InvNo;
	}

	public void setFin02InvNo(String fin02InvNo) {
		this.fin02InvNo = fin02InvNo;
	}

	public String getFin02InvStatus() {
		return fin02InvStatus;
	}

	public void setFin02InvStatus(String fin02InvStatus) {
		this.fin02InvStatus = fin02InvStatus;
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
		return "CimsHistoryFin08c [id=" + id + ", stateId=" + stateId + ", invoiceTypeId=" + invoiceTypeId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", circleId=" + circleId
				+ ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode=" + clinicTypeCode + ", clinicCode=" + clinicCode
				+ ", clinicId=" + clinicId + ", month=" + month + ", year=" + year + ", date=" + date + ", assetName="
				+ assetName + ", beNumber=" + beNumber + ", valueOfEquipment=" + valueOfEquipment
				+ ", maintenanceCharges=" + maintenanceCharges + ", status=" + status + ", fin08cStatus=" + fin08cStatus
				+ ", fin08cRefNo=" + fin08cRefNo + ", fin08RefNo=" + fin08RefNo + ", fin08Status=" + fin08Status
				+ ", fin03RefNo=" + fin03RefNo + ", fin03Status=" + fin03Status + ", fin02InvNo=" + fin02InvNo
				+ ", fin02InvStatus=" + fin02InvStatus + ", clinic=" + clinic + ", remarks=" + remarks + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}

		
		
	

}
