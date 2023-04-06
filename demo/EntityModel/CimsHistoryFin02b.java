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
@Table(name="cims_history_fin02b")
public class CimsHistoryFin02b implements Serializable {
	
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
	
	@Formula("(select circle.circle_name from m_circle circle where circle.id=(select district.m_circle_id from m_district district where district.id=m_district_id ) )")
	private String circleName;
	
	@Column(name="m_clinic_type_id")
	private Integer clinicTypeId;
	
	@Formula("(select clinictype.clinic_type_code from m_clinic_type clinictype where clinictype.id=m_clinic_type_id )")
	private String clinicTypeCode;

	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate date;
	private Integer numberofExistingBe;
	private Double ebeValue;
	private Integer NumberOfSa7;
	private Double sa7Value;
	private Integer totalEbe;
	private Double totalEbeValue;
	@Column(name="t_fin02b_inv_no")
	private String fin02bInvNo;
	@Column(name="fin02b_inv_status")
	private String fin02bInvStatus;
	private String remarks;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public CimsHistoryFin02b() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CimsHistoryFin02b(Integer id, Integer stateId, Integer invoiceTypeId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer circleId, String circleName,
			Integer clinicTypeId, String clinicTypeCode, String month, String year, LocalDate date,
			Integer numberofExistingBe, Double ebeValue, Integer numberOfSa7, Double sa7Value, Integer totalEbe,
			Double totalEbeValue, String fin02bInvNo, String fin02bInvStatus, String remarks, String createdBy,
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
		this.circleId = circleId;
		this.circleName = circleName;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.month = month;
		this.year = year;
		this.date = date;
		this.numberofExistingBe = numberofExistingBe;
		this.ebeValue = ebeValue;
		NumberOfSa7 = numberOfSa7;
		this.sa7Value = sa7Value;
		this.totalEbe = totalEbe;
		this.totalEbeValue = totalEbeValue;
		this.fin02bInvNo = fin02bInvNo;
		this.fin02bInvStatus = fin02bInvStatus;
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

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
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

	public Integer getNumberofExistingBe() {
		return numberofExistingBe;
	}

	public void setNumberofExistingBe(Integer numberofExistingBe) {
		this.numberofExistingBe = numberofExistingBe;
	}

	public Double getEbeValue() {
		return ebeValue;
	}

	public void setEbeValue(Double ebeValue) {
		this.ebeValue = ebeValue;
	}

	public Integer getNumberOfSa7() {
		return NumberOfSa7;
	}

	public void setNumberOfSa7(Integer numberOfSa7) {
		NumberOfSa7 = numberOfSa7;
	}

	public Double getSa7Value() {
		return sa7Value;
	}

	public void setSa7Value(Double sa7Value) {
		this.sa7Value = sa7Value;
	}

	public Integer getTotalEbe() {
		return totalEbe;
	}

	public void setTotalEbe(Integer totalEbe) {
		this.totalEbe = totalEbe;
	}

	public Double getTotalEbeValue() {
		return totalEbeValue;
	}

	public void setTotalEbeValue(Double totalEbeValue) {
		this.totalEbeValue = totalEbeValue;
	}

	public String getFin02bInvNo() {
		return fin02bInvNo;
	}

	public void setFin02bInvNo(String fin02bInvNo) {
		this.fin02bInvNo = fin02bInvNo;
	}

	public String getFin02bInvStatus() {
		return fin02bInvStatus;
	}

	public void setFin02bInvStatus(String fin02bInvStatus) {
		this.fin02bInvStatus = fin02bInvStatus;
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
		return "CimsHistoryFin02b [id=" + id + ", stateId=" + stateId + ", invoiceTypeId=" + invoiceTypeId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", circleId=" + circleId
				+ ", circleName=" + circleName + ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode=" + clinicTypeCode
				+ ", month=" + month + ", year=" + year + ", date=" + date + ", numberofExistingBe="
				+ numberofExistingBe + ", ebeValue=" + ebeValue + ", NumberOfSa7=" + NumberOfSa7 + ", sa7Value="
				+ sa7Value + ", totalEbe=" + totalEbe + ", totalEbeValue=" + totalEbeValue + ", fin02bInvNo="
				+ fin02bInvNo + ", fin02bInvStatus=" + fin02bInvStatus + ", remarks=" + remarks + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}

	
	
	
	
}
