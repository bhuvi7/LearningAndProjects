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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_fin09")
public class Fin09 implements Serializable {

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
	
	@Formula("(select mc.circle_code from m_circle mc where mc.id = (SELECT md.m_circle_id from m_district md where md.id  = m_district_id))")
	private String circleCode;
	
	@Column(name="m_clinic_type_id")
	private Integer clinicTypeId;
	
	@Formula("(select clinictype.clinic_type_code from m_clinic_type clinictype where clinictype.id=m_clinic_type_id )")
	private String clinicTypeCode;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fin09_invoice_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<InvoicePaymentHistory> InvoicePaymentHistory;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin09_ref_no", referencedColumnName = "code",insertable = false, updatable = false)
	private List<Fin09Clinic> Fin09Clinic;
	
	private String month;
	private String year;
	@Column(name = "approval_date")
	private LocalDate approvalDate;
	@CreationTimestamp
	private LocalDate date;
	private Double totalResponseTimePenalty;
	private Double totalRepairTimePenalty;
	private Double totalUptimePenalty;
	private Double totalScheduledMaintenancePenalty;
	private Double totalLateDeliveryPenalty;
	private Double totalPenalty;
	private String status;
	@Column(name="t_fin03_ref_no")
	private String fin03RefNo;
	@Column(name="fin03_status")
	private String fin03Status;
	@Column(name="t_fin02_ref_no")
	private String fin02RefNo;
	@Column(name="fin02_status")
	private String fin02Status;
	
	@Column(name="quater")
	private String quater;
	
	@Column(name="approval_quater")
	private String approvalQuater;
	
	@Column(name="approval_Year")
	private String approvalYear;
	
	@Column(name="approval_Month")
	private String approvalMonth;
	
	
	
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
	
	public Fin09() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Fin09(Integer stateId, String stateName, Double totalPenalty) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.totalPenalty = totalPenalty;
	}


	public Fin09( String circleCode, Double totalPenalty) {
		super();
	
		this.circleCode = circleCode;
		this.totalPenalty = totalPenalty;
	}

	

	public Fin09(String districtName,  String quater, Double totalPenalty) {
		super();
		this.districtName = districtName;
		this.quater = quater;
		this.totalPenalty = totalPenalty;
	}


	public Fin09(Integer stateId,String circleCode,Integer districtId, String districtName,  Double totalPenalty) {
		super();
		this.stateId = stateId;
		this.districtId = districtId;
		this.districtName = districtName;
		this.circleCode = circleCode;
		this.totalPenalty = totalPenalty;
		
	}
	
	
	public Fin09( Double totalPenalty,String quater, String year ) {
		super();
		this.totalPenalty = totalPenalty;
		this.quater = quater;
		this.year = year;
	}
	

	public Fin09(Double totalPenalty,  String quater, String month, String year) {
		super();
		this.totalPenalty = totalPenalty;
		this.quater = quater;
		this.month = month;
		this.year = year;
	}
	
	public Fin09(Integer id, String code, Integer clinicTypeId,String stateName,  String districtName,String clinicTypeCode,
			Double totalPenalty,String status, String approvalMonth,String approvalQuater,String approvalYear, String remarks,Double totalResponseTimePenalty,Double totalRepairTimePenalty,
			Double totalUptimePenalty,Double  totalScheduledMaintenancePenalty, Double totalLateDeliveryPenalty) {
		super();

		this.id = id;
		this.code = code;
		this.clinicTypeId=clinicTypeId;
		this.stateName = stateName;
		this.districtName =districtName;
		this.clinicTypeCode =clinicTypeCode;
		this.totalPenalty =totalPenalty;
		this.status = status;
		this.approvalMonth =approvalMonth;
		this.approvalQuater =approvalQuater;
		this.approvalYear =approvalYear;
		this.remarks = remarks;
		this.totalResponseTimePenalty=totalResponseTimePenalty;
		this.totalRepairTimePenalty=totalRepairTimePenalty;
		this.totalUptimePenalty=totalUptimePenalty;
		this.totalScheduledMaintenancePenalty=totalScheduledMaintenancePenalty;
		this.totalLateDeliveryPenalty=totalLateDeliveryPenalty;
	}


	public Fin09(Integer id, String code, String docRefNo, Integer stateId, String stateName, String stateCode,
			Integer districtId, String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode,
			List<com.example.demo.EntityModel.Fin09Clinic> fin09Clinic, String month, String year, LocalDate date,
			Double totalResponseTimePenalty, Double totalRepairTimePenalty, Double totalUptimePenalty,
			Double totalScheduledMaintenancePenalty, Double totalLateDeliveryPenalty, Double totalPenalty,
			String status, String fin03RefNo, String fin03Status, String fin02RefNo, String fin02Status, String remarks,
			String createdBy, LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate, LocalDate approvalDate, String quater, 
			String approvalQuater, String approvalYear, String approvalMonth,	List<com.example.demo.EntityModel.InvoicePaymentHistory> invoicePaymentHistory) {
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
		Fin09Clinic = fin09Clinic;
		this.month = month;
		this.year = year;
		this.date = date;
		this.totalResponseTimePenalty = totalResponseTimePenalty;
		this.totalRepairTimePenalty = totalRepairTimePenalty;
		this.totalUptimePenalty = totalUptimePenalty;
		this.totalScheduledMaintenancePenalty = totalScheduledMaintenancePenalty;
		this.totalLateDeliveryPenalty = totalLateDeliveryPenalty;
		this.totalPenalty = totalPenalty;
		this.status = status;
		this.fin03RefNo = fin03RefNo;
		this.fin03Status = fin03Status;
		this.fin02RefNo = fin02RefNo;
		this.fin02Status = fin02Status;
		this.remarks = remarks;
		this.approvalDate = approvalDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.quater= quater;
		this.approvalQuater= approvalQuater;
		this.approvalYear= approvalYear;
		this.approvalMonth=approvalMonth;
		InvoicePaymentHistory = invoicePaymentHistory;
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

	public String getCircleCode() {
		return circleCode;
	}


	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
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

	public List<Fin09Clinic> getFin09Clinic() {
		return Fin09Clinic;
	}

	public void setFin09Clinic(List<Fin09Clinic> fin09Clinic) {
		Fin09Clinic = fin09Clinic;
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
	

	public List<InvoicePaymentHistory> getInvoicePaymentHistory() {
		return InvoicePaymentHistory;
	}


	public void setInvoicePaymentHistory(List<InvoicePaymentHistory> invoicePaymentHistory) {
		InvoicePaymentHistory = invoicePaymentHistory;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotalResponseTimePenalty() {
		return totalResponseTimePenalty;
	}

	public void setTotalResponseTimePenalty(Double totalResponseTimePenalty) {
		this.totalResponseTimePenalty = totalResponseTimePenalty;
	}

	public Double getTotalRepairTimePenalty() {
		return totalRepairTimePenalty;
	}

	public void setTotalRepairTimePenalty(Double totalRepairTimePenalty) {
		this.totalRepairTimePenalty = totalRepairTimePenalty;
	}

	public Double getTotalUptimePenalty() {
		return totalUptimePenalty;
	}

	public void setTotalUptimePenalty(Double totalUptimePenalty) {
		this.totalUptimePenalty = totalUptimePenalty;
	}

	public Double getTotalScheduledMaintenancePenalty() {
		return totalScheduledMaintenancePenalty;
	}

	public void setTotalScheduledMaintenancePenalty(Double totalScheduledMaintenancePenalty) {
		this.totalScheduledMaintenancePenalty = totalScheduledMaintenancePenalty;
	}

	public Double getTotalLateDeliveryPenalty() {
		return totalLateDeliveryPenalty;
	}

	public void setTotalLateDeliveryPenalty(Double totalLateDeliveryPenalty) {
		this.totalLateDeliveryPenalty = totalLateDeliveryPenalty;
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

	public String getFin02RefNo() {
		return fin02RefNo;
	}

	public void setFin02RefNo(String fin02RefNo) {
		this.fin02RefNo = fin02RefNo;
	}

	public String getFin02Status() {
		return fin02Status;
	}

	public void setFin02Status(String fin02Status) {
		this.fin02Status = fin02Status;
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
	

	public String getQuater() {
		return quater;
	}


	public void setQuater(String quater) {
		this.quater = quater;
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


	public void setApprovalYear(String approvalYear) {
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
		return "Fin09 [id=" + id + ", code=" + code + ", docRefNo=" + docRefNo + ", stateId=" + stateId + ", stateName="
				+ stateName + ", stateCode=" + stateCode + ", districtId=" + districtId + ", districtName="
				+ districtName + ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId
				+ ", clinicTypeCode=" + clinicTypeCode + ", Fin09Clinic=" + Fin09Clinic + ", month=" + month + ", year="
				+ year + ", date=" + date + ", totalResponseTimePenalty=" + totalResponseTimePenalty
				+ ", totalRepairTimePenalty=" + totalRepairTimePenalty + ", totalUptimePenalty=" + totalUptimePenalty
				+ ", totalScheduledMaintenancePenalty=" + totalScheduledMaintenancePenalty
				+ ", totalLateDeliveryPenalty=" + totalLateDeliveryPenalty + ", totalPenalty=" + totalPenalty
				+ ", status=" + status + ", fin03RefNo=" + fin03RefNo + ", fin03Status=" + fin03Status + ", fin02RefNo="
				+ fin02RefNo + ", fin02Status=" + fin02Status + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
	

			
	
	
	
}

