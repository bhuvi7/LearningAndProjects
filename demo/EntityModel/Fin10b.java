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
@Table(name = "t_fin10b")
public class Fin10b implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String docRefNo;
	private String certOfAcceptanceRef;
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
	
	@Formula("(select clinic.clinic_code from m_clinic clinic where clinic.id=m_clinic_id )")
	private String clinicCode;
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	private String month;
	private String year;
	
	private LocalDate date;
	private String assetName;
	private String assetType;
	private String beNumber;
	private Double totalAmount;
	private String status;
	@Column(name="t_fin04_inv_no")
	private String fin04InvNo;
	@Column(name="fin04_inv_status")
	private String fin04InvStatus;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="m_clinic_id", insertable = false, updatable = false)
	private Clinic clinic;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin10b_ref_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<Fin10bConstructionWorks> fin10bConstructionWorks;
	
	
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
	
	public Fin10b() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fin10b(Integer id, String code, String docRefNo, String certOfAcceptanceRef, Integer stateId,
			String stateName, String stateCode, Integer districtId, String districtName, String districtCode,
			Integer clinicTypeId, String clinicTypeCode, String clinicCode, Integer clinicId, String month, String year,
			LocalDate date, String assetName, String assetType, String beNumber, Double totalAmount, String status,
			String fin04InvNo, String fin04InvStatus, Clinic clinic,
			List<Fin10bConstructionWorks> fin10bConstructionWorks, Integer submittedUserId, Integer approval1UserId,
			Integer approval2UserId, String submittedUserName, String approval1UserName, String approval2UserName,
			String submittedUserDesignation, String approval1UserDesignation, String approval2UserDesignation,
			LocalDate submittedDate, LocalDate approval1Date, LocalDate approval2Date, String createdBy,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.code = code;
		this.docRefNo = docRefNo;
		this.certOfAcceptanceRef = certOfAcceptanceRef;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.clinicCode = clinicCode;
		this.clinicId = clinicId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.assetName = assetName;
		this.assetType = assetType;
		this.beNumber = beNumber;
		this.totalAmount = totalAmount;
		this.status = status;
		this.fin04InvNo = fin04InvNo;
		this.fin04InvStatus = fin04InvStatus;
		this.clinic = clinic;
		this.fin10bConstructionWorks = fin10bConstructionWorks;
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

	public String getCertOfAcceptanceRef() {
		return certOfAcceptanceRef;
	}

	public void setCertOfAcceptanceRef(String certOfAcceptanceRef) {
		this.certOfAcceptanceRef = certOfAcceptanceRef;
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

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getBeNumber() {
		return beNumber;
	}

	public void setBeNumber(String beNumber) {
		this.beNumber = beNumber;
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

	public String getFin04InvNo() {
		return fin04InvNo;
	}

	public void setFin04InvNo(String fin04InvNo) {
		this.fin04InvNo = fin04InvNo;
	}

	public String getFin04InvStatus() {
		return fin04InvStatus;
	}

	public void setFin04InvStatus(String fin04InvStatus) {
		this.fin04InvStatus = fin04InvStatus;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public List<Fin10bConstructionWorks> getFin10bConstructionWorks() {
		return fin10bConstructionWorks;
	}

	public void setFin10bConstructionWorks(List<Fin10bConstructionWorks> fin10bConstructionWorks) {
		this.fin10bConstructionWorks = fin10bConstructionWorks;
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
		return "Fin10b [id=" + id + ", code=" + code + ", docRefNo=" + docRefNo + ", certOfAcceptanceRef="
				+ certOfAcceptanceRef + ", stateId=" + stateId + ", stateName=" + stateName + ", stateCode=" + stateCode
				+ ", districtId=" + districtId + ", districtName=" + districtName + ", districtCode=" + districtCode
				+ ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode=" + clinicTypeCode + ", clinicCode=" + clinicCode
				+ ", clinicId=" + clinicId + ", month=" + month + ", year=" + year + ", date=" + date + ", assetName="
				+ assetName + ", assetType=" + assetType + ", beNumber=" + beNumber + ", totalAmount=" + totalAmount
				+ ", status=" + status + ", fin04InvNo=" + fin04InvNo + ", fin04InvStatus=" + fin04InvStatus
				+ ", clinic=" + clinic + ", fin10bConstructionWorks=" + fin10bConstructionWorks + ", submittedUserId="
				+ submittedUserId + ", approval1UserId=" + approval1UserId + ", approval2UserId=" + approval2UserId
				+ ", submittedUserName=" + submittedUserName + ", approval1UserName=" + approval1UserName
				+ ", approval2UserName=" + approval2UserName + ", submittedUserDesignation=" + submittedUserDesignation
				+ ", approval1UserDesignation=" + approval1UserDesignation + ", approval2UserDesignation="
				+ approval2UserDesignation + ", submittedDate=" + submittedDate + ", approval1Date=" + approval1Date
				+ ", approval2Date=" + approval2Date + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
		
	
}