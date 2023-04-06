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
@Table(name="t_fin08b")
public class Fin08b implements Serializable {

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
	
	@Formula("(select clinic.clinic_code from m_clinic clinic where clinic.id=m_clinic_id )")
	private String clinicCode;
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin08b_ref_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<CimsHistoryFin08b> cimsHistoryFin08b;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin08b_ref_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<CimsHistoryFin02> cimsHistoryFin02;
	
	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate date;
	private Integer totalEquipmentCount;
	private Double totalMaintenanceCharges;
	private String status;
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
	
	public Fin08b() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fin08b(Integer id, String code, String docRefNo, Integer stateId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode,
			String clinicCode, Integer clinicId, List<CimsHistoryFin08b> cimsHistoryFin08b,
			List<CimsHistoryFin02> cimsHistoryFin02, String month, String year, LocalDate date,
			Integer totalEquipmentCount, Double totalMaintenanceCharges, String status, String fin08RefNo,
			String fin08Status, String fin03RefNo, String fin03Status, String fin02InvNo, String fin02InvStatus,
			Clinic clinic, String remarks, Integer submittedUserId, Integer approval1UserId, Integer approval2UserId,
			String submittedUserName, String approval1UserName, String approval2UserName,
			String submittedUserDesignation, String approval1UserDesignation, String approval2UserDesignation,
			LocalDate submittedDate, LocalDate approval1Date, LocalDate approval2Date, String createdBy,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
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
		this.clinicCode = clinicCode;
		this.clinicId = clinicId;
		this.cimsHistoryFin08b = cimsHistoryFin08b;
		this.cimsHistoryFin02 = cimsHistoryFin02;
		this.month = month;
		this.year = year;
		this.date = date;
		this.totalEquipmentCount = totalEquipmentCount;
		this.totalMaintenanceCharges = totalMaintenanceCharges;
		this.status = status;
		this.fin08RefNo = fin08RefNo;
		this.fin08Status = fin08Status;
		this.fin03RefNo = fin03RefNo;
		this.fin03Status = fin03Status;
		this.fin02InvNo = fin02InvNo;
		this.fin02InvStatus = fin02InvStatus;
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

	public List<CimsHistoryFin08b> getCimsHistoryFin08b() {
		return cimsHistoryFin08b;
	}

	public void setCimsHistoryFin08b(List<CimsHistoryFin08b> cimsHistoryFin08b) {
		this.cimsHistoryFin08b = cimsHistoryFin08b;
	}

	public List<CimsHistoryFin02> getCimsHistoryFin02() {
		return cimsHistoryFin02;
	}

	public void setCimsHistoryFin02(List<CimsHistoryFin02> cimsHistoryFin02) {
		this.cimsHistoryFin02 = cimsHistoryFin02;
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

	public Double getTotalMaintenanceCharges() {
		return totalMaintenanceCharges;
	}

	public void setTotalMaintenanceCharges(Double totalMaintenanceCharges) {
		this.totalMaintenanceCharges = totalMaintenanceCharges;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "Fin08b [id=" + id + ", code=" + code + ", docRefNo=" + docRefNo + ", stateId=" + stateId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId
				+ ", clinicTypeCode=" + clinicTypeCode + ", clinicCode=" + clinicCode + ", clinicId=" + clinicId
				+ ", cimsHistoryFin08b=" + cimsHistoryFin08b + ", cimsHistoryFin02=" + cimsHistoryFin02 + ", month="
				+ month + ", year=" + year + ", date=" + date + ", totalEquipmentCount=" + totalEquipmentCount
				+ ", totalMaintenanceCharges=" + totalMaintenanceCharges + ", status=" + status + ", fin08RefNo="
				+ fin08RefNo + ", fin08Status=" + fin08Status + ", fin03RefNo=" + fin03RefNo + ", fin03Status="
				+ fin03Status + ", fin02InvNo=" + fin02InvNo + ", fin02InvStatus=" + fin02InvStatus + ", clinic="
				+ clinic + ", remarks=" + remarks + ", submittedUserId=" + submittedUserId + ", approval1UserId="
				+ approval1UserId + ", approval2UserId=" + approval2UserId + ", submittedUserName=" + submittedUserName
				+ ", approval1UserName=" + approval1UserName + ", approval2UserName=" + approval2UserName
				+ ", submittedUserDesignation=" + submittedUserDesignation + ", approval1UserDesignation="
				+ approval1UserDesignation + ", approval2UserDesignation=" + approval2UserDesignation
				+ ", submittedDate=" + submittedDate + ", approval1Date=" + approval1Date + ", approval2Date="
				+ approval2Date + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
	
}
