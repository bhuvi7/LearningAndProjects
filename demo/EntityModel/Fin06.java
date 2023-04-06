package com.example.demo.EntityModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_fin06")
public class Fin06 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String docRefNo;
	@Column(name="m_state_id")
	private Integer stateId;
	
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
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin06_ref_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<CimsHistoryFin01> cimsHistoryFin01;
	
	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate date;
	private Integer totalEquipmentCount;
	private Double totalAmount;
	private String status;
	@Column(name="t_fin01_ref_no")
	private String fin01RefNo;
	@Column(name="fin01_status")
	private String fin01Status;
	@Column(name="t_fin01_inv_no")
	private String fin01InvNo;
	@Column(name="fin01_inv_status")
	private String fin01InvStatus;
	private String invoiceException;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="m_clinic_id", insertable = false, updatable = false)
	private Clinic clinic;
	private String remarks;
	
	private Integer submittedUserId;
	private Integer approval1UserId;
	private Integer approval2UserId;
	
	@Formula("(select u.name from user u where u.id=submitted_user_id )")
	private String submittedUserName;
	
	@Formula("(select u.name from user u where u.id=approval1user_id )")
	private String approval1UserName;
	
	@Formula("(select u.name from user u where u.id=approval2user_id )")
	private String approval2UserName;
	
	@Formula("(select u.designation from user u where u.id=submitted_user_id )")
	private String submittedUserDesignation;
	
	@Formula("(select u.designation from user u where u.id=approval1user_id )")
	private String approval1UserDesignation;
	
	@Formula("(select u.designation from user u where u.id=approval2user_id )")
	private String approval2UserDesignation;

	private LocalDate submittedDate;

	private LocalDate approval1Date;

	private LocalDate approval2Date;
	
	private String createdBy;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private String updatedBy;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public Fin06() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fin06(Integer id, String code, String docRefNo, Integer stateId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode,
			Integer clinicId, List<CimsHistoryFin01> cimsHistoryFin01, String month, String year, LocalDate date,
			Integer totalEquipmentCount, Double totalAmount, String status, String fin01RefNo, String fin01Status,
			String fin01InvNo, String fin01InvStatus, String invoiceException, Clinic clinic, String remarks,
			Integer submittedUserId, Integer approval1UserId, Integer approval2UserId, String submittedUserName,
			String approval1UserName, String approval2UserName, String submittedUserDesignation,
			String approval1UserDesignation, String approval2UserDesignation, LocalDate submittedDate,
			LocalDate approval1Date, LocalDate approval2Date, String createdBy, LocalDateTime createdDate,
			String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.code = code;
		this.docRefNo = docRefNo;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.clinicId = clinicId;
		this.cimsHistoryFin01 = cimsHistoryFin01;
		this.month = month;
		this.year = year;
		this.date = date;
		this.totalEquipmentCount = totalEquipmentCount;
		this.totalAmount = totalAmount;
		this.status = status;
		this.fin01RefNo = fin01RefNo;
		this.fin01Status = fin01Status;
		this.fin01InvNo = fin01InvNo;
		this.fin01InvStatus = fin01InvStatus;
		this.invoiceException = invoiceException;
		this.clinic = clinic;
		this.remarks = remarks;
		this.submittedUserId = submittedUserId;
		this.approval1UserId = approval1UserId;
		this.approval2UserId = approval2UserId;
		this.submittedUserName = submittedUserName;
		this.approval1UserName = approval1UserName;
		this.approval2UserName = approval2UserName;
		this.submittedUserDesignation = submittedUserDesignation;
		this.approval1UserDesignation = approval1UserDesignation;
		this.approval2UserDesignation = approval2UserDesignation;
		this.submittedDate = submittedDate;
		this.approval1Date = approval1Date;
		this.approval2Date = approval2Date;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDocRefNo() {
		return docRefNo;
	}

	public void setDocRefNo(String docRefNo) {
		this.docRefNo = docRefNo;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

	public List<CimsHistoryFin01> getCimsHistoryFin01() {
		return cimsHistoryFin01;
	}

	public void setCimsHistoryFin01(List<CimsHistoryFin01> cimsHistoryFin01) {
		this.cimsHistoryFin01 = cimsHistoryFin01;
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

	public Integer getTotalEquipmentCount() {
		return totalEquipmentCount;
	}

	public void setTotalEquipmentCount(Integer totalEquipmentCount) {
		this.totalEquipmentCount = totalEquipmentCount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFin01RefNo() {
		return fin01RefNo;
	}

	public void setFin01RefNo(String fin01RefNo) {
		this.fin01RefNo = fin01RefNo;
	}

	public String getFin01Status() {
		return fin01Status;
	}

	public void setFin01Status(String fin01Status) {
		this.fin01Status = fin01Status;
	}

	public String getFin01InvNo() {
		return fin01InvNo;
	}

	public void setFin01InvNo(String fin01InvNo) {
		this.fin01InvNo = fin01InvNo;
	}

	public String getFin01InvStatus() {
		return fin01InvStatus;
	}

	public void setFin01InvStatus(String fin01InvStatus) {
		this.fin01InvStatus = fin01InvStatus;
	}

	public String getInvoiceException() {
		return invoiceException;
	}

	public void setInvoiceException(String invoiceException) {
		this.invoiceException = invoiceException;
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

	public Integer getSubmittedUserId() {
		return submittedUserId;
	}

	public void setSubmittedUserId(Integer submittedUserId) {
		this.submittedUserId = submittedUserId;
	}

	public Integer getApproval1UserId() {
		return approval1UserId;
	}

	public void setApproval1UserId(Integer approval1UserId) {
		this.approval1UserId = approval1UserId;
	}

	public Integer getApproval2UserId() {
		return approval2UserId;
	}

	public void setApproval2UserId(Integer approval2UserId) {
		this.approval2UserId = approval2UserId;
	}

	public String getSubmittedUserName() {
		return submittedUserName;
	}

	public void setSubmittedUserName(String submittedUserName) {
		this.submittedUserName = submittedUserName;
	}

	public String getApproval1UserName() {
		return approval1UserName;
	}

	public void setApproval1UserName(String approval1UserName) {
		this.approval1UserName = approval1UserName;
	}

	public String getApproval2UserName() {
		return approval2UserName;
	}

	public void setApproval2UserName(String approval2UserName) {
		this.approval2UserName = approval2UserName;
	}

	public String getSubmittedUserDesignation() {
		return submittedUserDesignation;
	}

	public void setSubmittedUserDesignation(String submittedUserDesignation) {
		this.submittedUserDesignation = submittedUserDesignation;
	}

	public String getApproval1UserDesignation() {
		return approval1UserDesignation;
	}

	public void setApproval1UserDesignation(String approval1UserDesignation) {
		this.approval1UserDesignation = approval1UserDesignation;
	}

	public String getApproval2UserDesignation() {
		return approval2UserDesignation;
	}

	public void setApproval2UserDesignation(String approval2UserDesignation) {
		this.approval2UserDesignation = approval2UserDesignation;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public LocalDate getApproval1Date() {
		return approval1Date;
	}

	public void setApproval1Date(LocalDate approval1Date) {
		this.approval1Date = approval1Date;
	}

	public LocalDate getApproval2Date() {
		return approval2Date;
	}

	public void setApproval2Date(LocalDate approval2Date) {
		this.approval2Date = approval2Date;
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
		return "Fin06 [id=" + id + ", code=" + code + ", docRefNo=" + docRefNo + ", stateId=" + stateId + ", stateName="
				+ stateName + ", stateCode=" + stateCode + ", districtId=" + districtId + ", districtName="
				+ districtName + ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId
				+ ", clinicTypeCode=" + clinicTypeCode + ", clinicId=" + clinicId + ", cimsHistoryFin01="
				+ cimsHistoryFin01 + ", month=" + month + ", year=" + year + ", date=" + date + ", totalEquipmentCount="
				+ totalEquipmentCount + ", totalAmount=" + totalAmount + ", status=" + status + ", fin01RefNo="
				+ fin01RefNo + ", fin01Status=" + fin01Status + ", fin01InvNo=" + fin01InvNo + ", fin01InvStatus="
				+ fin01InvStatus + ", invoiceException=" + invoiceException + ", clinic=" + clinic + ", remarks="
				+ remarks + ", submittedUserId=" + submittedUserId + ", approval1UserId=" + approval1UserId
				+ ", approval2UserId=" + approval2UserId + ", submittedUserName=" + submittedUserName
				+ ", approval1UserName=" + approval1UserName + ", approval2UserName=" + approval2UserName
				+ ", submittedUserDesignation=" + submittedUserDesignation + ", approval1UserDesignation="
				+ approval1UserDesignation + ", approval2UserDesignation=" + approval2UserDesignation
				+ ", submittedDate=" + submittedDate + ", approval1Date=" + approval1Date + ", approval2Date="
				+ approval2Date + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
	

}