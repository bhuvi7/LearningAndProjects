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
@Table(name="t_fin09_clinic")
public class Fin09Clinic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
	
	@Formula("(select clinic.clinic_name from m_clinic clinic where clinic.id=m_clinic_id )")
	private String clinicName;
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	
	private String month;
	private String year;
	@Column(name="approval_date")
	private LocalDate approvalDate;
	
	@Column(name="approval_quater")
	private String approvalQuater;
	
	@Column(name="approval_year")
	private String approvalYear;
	
	@Column(name="approval_Month")
	private String approvalMonth;
	
	@CreationTimestamp
	private LocalDate date;
	private Double responseTimePenalty;
	private Double repairTimePenalty;
	private Double uptimePenalty;
	private Double scheduledMaintenancePenalty;
	private Double lateDeliveryPenalty;
	private Double totalPenalty;
	private String status;
	@Column(name="t_fin09_ref_no")
	private String fin09RefNo;
	@Column(name="fin09_status")
	private String fin09Status;
	@Column(name="t_fin03_ref_no")
	private String fin03RefNo;
	@Column(name="fin03_status")
	private String fin03Status;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="m_clinic_id", insertable = false, updatable = false)
//	private Clinic clinic;
	private String remarks;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public Fin09Clinic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fin09Clinic(Integer id, Integer stateId, String stateName, String stateCode, Integer districtId,
			String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode, String clinicCode,
			String clinicName, Integer clinicId, String month, String year, LocalDate date, Double responseTimePenalty,
			Double repairTimePenalty, Double uptimePenalty, Double scheduledMaintenancePenalty,
			Double lateDeliveryPenalty, Double totalPenalty, String status, String fin09RefNo, String fin09Status,
			String fin03RefNo, String fin03Status, String remarks, String createdBy, LocalDateTime createdDate,
			String updatedBy, LocalDateTime updatedDate, LocalDate approvalDate,String approvalQuater,String approvalYear,String approvalMonth) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.clinicCode = clinicCode;
		this.clinicName = clinicName;
		this.clinicId = clinicId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.approvalDate = approvalDate;
		this.responseTimePenalty = responseTimePenalty;
		this.repairTimePenalty = repairTimePenalty;
		this.uptimePenalty = uptimePenalty;
		this.scheduledMaintenancePenalty = scheduledMaintenancePenalty;
		this.lateDeliveryPenalty = lateDeliveryPenalty;
		this.totalPenalty = totalPenalty;
		this.status = status;
		this.fin09RefNo = fin09RefNo;
		this.fin09Status = fin09Status;
		this.fin03RefNo = fin03RefNo;
		this.fin03Status = fin03Status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.approvalQuater= approvalQuater;
		this.approvalYear= approvalYear;
		this.approvalMonth=approvalMonth;
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

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
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

	public Double getResponseTimePenalty() {
		return responseTimePenalty;
	}

	public void setResponseTimePenalty(Double responseTimePenalty) {
		this.responseTimePenalty = responseTimePenalty;
	}

	public Double getRepairTimePenalty() {
		return repairTimePenalty;
	}

	public void setRepairTimePenalty(Double repairTimePenalty) {
		this.repairTimePenalty = repairTimePenalty;
	}

	public Double getUptimePenalty() {
		return uptimePenalty;
	}

	public void setUptimePenalty(Double uptimePenalty) {
		this.uptimePenalty = uptimePenalty;
	}

	public Double getScheduledMaintenancePenalty() {
		return scheduledMaintenancePenalty;
	}

	public void setScheduledMaintenancePenalty(Double scheduledMaintenancePenalty) {
		this.scheduledMaintenancePenalty = scheduledMaintenancePenalty;
	}

	public Double getLateDeliveryPenalty() {
		return lateDeliveryPenalty;
	}

	public void setLateDeliveryPenalty(Double lateDeliveryPenalty) {
		this.lateDeliveryPenalty = lateDeliveryPenalty;
	}

	public Double getTotalPenalty() {
		return totalPenalty;
	}

	public void setTotalPenalty(Double totalPenalty) {
		this.totalPenalty = totalPenalty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFin09RefNo() {
		return fin09RefNo;
	}

	public void setFin09RefNo(String fin09RefNo) {
		this.fin09RefNo = fin09RefNo;
	}

	public String getFin09Status() {
		return fin09Status;
	}

	public void setFin09Status(String fin09Status) {
		this.fin09Status = fin09Status;
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

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	public String getApprovalQuater() {
		return approvalQuater;
	}


	public void setApprovalQuater(String approvalQuater) {
		this.approvalQuater = approvalQuater;
	}
	
	public String getApprovalYear() {
		return approvalYear;
	}


	public void getApprovalYear (String approvalYear) {
		this.approvalYear = approvalYear;
	}
	
	public String getApprovalMonth() {
		return approvalMonth;
	}


	public void setApprovalMonth(String approvalMonth) {
		this.approvalMonth = approvalMonth;
	}

	@Override
	public String toString() {
		return "Fin09Clinic [id=" + id + ", stateId=" + stateId + ", stateName=" + stateName + ", stateCode="
				+ stateCode + ", districtId=" + districtId + ", districtName=" + districtName + ", districtCode="
				+ districtCode + ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode=" + clinicTypeCode
				+ ", clinicCode=" + clinicCode + ", clinicName=" + clinicName + ", clinicId=" + clinicId + ", month="
				+ month + ", year=" + year + ", date=" + date + ", responseTimePenalty=" + responseTimePenalty
				+ ", repairTimePenalty=" + repairTimePenalty + ", uptimePenalty=" + uptimePenalty
				+ ", scheduledMaintenancePenalty=" + scheduledMaintenancePenalty + ", lateDeliveryPenalty="
				+ lateDeliveryPenalty + ", totalPenalty=" + totalPenalty + ", status=" + status + ", fin09RefNo="
				+ fin09RefNo + ", fin09Status=" + fin09Status + ", fin03RefNo=" + fin03RefNo + ", fin03Status="
				+ fin03Status + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

		
	
	
	
	
}
