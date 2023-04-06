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
@Table(name="t_invoice")
public class Invoice implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String code;
	private String invoiceNo;
	@Column(name="m_invoice_type_id")
	private Integer invoiceTypeId;
	@Formula("(select invoicetype.invoice_type_name from m_invoice_type invoicetype  where invoicetype.id=m_invoice_type_id )")
	private String invoiceTypeName;
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
	
	@Formula("(select mc.circle_code from m_circle mc where mc.id = (SELECT md.m_circle_id from m_district md where md.id  = m_district_id))")
	private String circleCode;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin01_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin06> Fin06;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin02a_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin03a> Fin03a;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin02_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin03> Fin03;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin02b_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<CimsHistoryFin02b> CimsHistoryFin02b;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin04_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin10b> Fin10b;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin05a_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin05aSiteConformity> Fin05aSiteConformity;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_fin11_inv_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<Fin11ConcessionElements> Fin11ConcessionElements;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="t_inv_ref_no", referencedColumnName = "invoiceNo",insertable = false, updatable = false)
	private List<InvoicePaymentHistory> InvoicePaymentHistory;
	
	private String month;
	private String year;
	@CreationTimestamp
	private LocalDate invoiceDate;
	private Double invoiceBaseValue;
	private Double sst;
	private Double netAfterSst; 
	private Double retentionAmount;
	private Double totalInvoiceValue;
	private Double totalInvoiceValueWoRetention;
	private Double outstandingAmount;
	private String status;
	private LocalDate paymentDate;
	private String paymentStatus;
	private String entryType;
	private String quater;
	private String paymentRefNo;
	private Double paymentReceived;
	
	private Double netRetentionAmount;
	private Integer submittedUserId;
	private Integer approval1UserId;
	private Integer approval2UserId;
	
	@Column(name="debit_note_entry")
	private Double debitNoteEntry;
	
	


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
	
	private String remarks;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	// don't remove the code from line 76 to 130


	public Invoice(Integer stateId, String stateName, Double outstandingAmount) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.outstandingAmount = outstandingAmount;
	}
	
	public Invoice(Integer stateId, String stateName, Double outstandingAmount,Double totalInvoiceValueWoRetention) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}
	
	public Invoice( Integer stateId, String stateName,String circleCode, Double outstandingAmount) {
		super();
	
		this.circleCode = circleCode;
		this.outstandingAmount = outstandingAmount;
	}

	public Invoice( Integer stateId, String stateName,String circleCode, Double outstandingAmount,
			Double totalInvoiceValueWoRetention) {
		super();
	
		this.circleCode = circleCode;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}




	public Invoice(String circleCode, Integer districtId, String districtName, Double outstandingAmount) {
		super();
		this.circleCode = circleCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.outstandingAmount = outstandingAmount;
	}

	public Invoice(String circleCode, Integer districtId, String districtName, Double outstandingAmount,Double totalInvoiceValueWoRetention) {
		super();
		this.circleCode = circleCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}




	public Invoice(String districtName, String quater, Double outstandingAmount) {
		super();
		this.districtName = districtName;
		this.quater = quater;
		this.outstandingAmount = outstandingAmount;
	}
	
	public Invoice(String districtName, String quater, Double outstandingAmount,Double totalInvoiceValueWoRetention) {
		super();
		this.districtName = districtName;
		this.quater = quater;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}
	
	//Fin02 
	public Invoice(String invoiceNo, String stateName, String districtName, LocalDate invoiceDate,
			Double invoiceBaseValue, Double sst, Double retentionAmount, Double totalInvoiceValue,
			Double outstandingAmount, Double paymentReceived) {
		super();
		this.invoiceNo = invoiceNo;
		this.stateName = stateName;
		this.districtName = districtName;
		this.invoiceDate = invoiceDate;
		this.invoiceBaseValue = invoiceBaseValue;
		this.sst = sst;
		this.retentionAmount = retentionAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.outstandingAmount = outstandingAmount;
		this.paymentReceived = paymentReceived;
	}



	public Invoice( String invoiceTypeName, Double outstandingAmount) {
		super();
		this.invoiceTypeName = invoiceTypeName;
		this.outstandingAmount = outstandingAmount;
	}
	



	public Invoice( String invoiceTypeName, Double outstandingAmount ,Double totalInvoiceValueWoRetention) {
		super();
		this.invoiceTypeName = invoiceTypeName;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}



	public Invoice( Double outstandingAmount,String quater, String year) {
		super();
		this.outstandingAmount = outstandingAmount;
		this.quater = quater;
		this.year = year;
		
	}
	
	public Invoice( Double outstandingAmount,Double totalInvoiceValueWoRetention,String quater, String year) {
		super();
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
		this.quater = quater;
		this.year = year;
		
	}

	public Invoice( Double outstandingAmount,String quater, String month,String year) {
		super();
		this.quater = quater;
		this.year = year;
		this.outstandingAmount = outstandingAmount;
		this.month = month;
	}
	
	public Invoice( Double outstandingAmount,Double totalInvoiceValueWoRetention,String quater, String month,String year) {
		super();
		this.quater = quater;
		this.year = year;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
		this.month = month;
	}

	

	public Invoice(Integer id, String invoiceNo, Integer invoiceTypeId, String invoiceTypeName, Integer stateId,
			String stateName, Integer districtId, String districtName, Integer clinicTypeId, String clinicTypeCode,
			String month, String year, LocalDate invoiceDate, Double retentionAmount, Double totalInvoiceValue,
			Double outstandingAmount, String status, String paymentStatus, Double paymentReceived,String paymentRefNo,
			Double totalInvoiceValueWoRetention, String circleCode, Double sst, Double invoiceBaseValue, Double netRetentionAmount, Double debitNoteEntry) {
		super();

		this.id = id;
		this.invoiceNo = invoiceNo;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceTypeName = invoiceTypeName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.districtId = districtId;
		this.districtName = districtName;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.month = month;
		this.year = year;
		this.invoiceDate = invoiceDate;
		this.retentionAmount = retentionAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.outstandingAmount = outstandingAmount;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.paymentReceived = paymentReceived;
		this.paymentRefNo = paymentRefNo;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
		this.circleCode = circleCode;
		this.sst = sst;
		this.invoiceBaseValue = invoiceBaseValue;
		this.netRetentionAmount = netRetentionAmount;
		this.debitNoteEntry =debitNoteEntry;
		
	}
	
	
	public Invoice(Integer id, String invoiceNo, Integer invoiceTypeId, String invoiceTypeName, Integer stateId,
			String stateName, Integer districtId, String districtName, Integer clinicTypeId, String clinicTypeCode,
			String month, String year, LocalDate invoiceDate, Double netRetentionAmount, Double totalInvoiceValue,
			Double outstandingAmount, String status, String paymentStatus, Double paymentReceived,String paymentRefNo,
			Double totalInvoiceValueWoRetention, String circleCode, Double sst) {
		super();
		
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceTypeName = invoiceTypeName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.districtId = districtId;
		this.districtName = districtName;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.month = month;
		this.year = year;
		this.invoiceDate = invoiceDate;
		this.netRetentionAmount = netRetentionAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.outstandingAmount = outstandingAmount;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.paymentReceived = paymentReceived;
		this.paymentRefNo = paymentRefNo;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
		this.circleCode = circleCode;
		this.sst = sst;

	}
	public Invoice(Integer invoiceTypeId, String invoiceTypeName,Integer clinicTypeId, String clinicTypeCode, Integer stateId, String stateName, Integer districtId, String districtName,Double totalInvoiceValue) {	
		super();	
		
		this.invoiceTypeId = invoiceTypeId;	
		this.invoiceTypeName = invoiceTypeName;	
		this.clinicTypeId = clinicTypeId;	
		this.clinicTypeCode = clinicTypeCode;	
		this.stateId = stateId;	
		this.stateName = stateName;	
		this.districtId = districtId;	
		this.districtName = districtName;	
		this.totalInvoiceValue = totalInvoiceValue;	
	}	



	

	public Invoice(Integer id, String code, String invoiceNo, Integer invoiceTypeId, String invoiceTypeName,
			Integer stateId, String stateName, String stateCode, Integer districtId, String districtName,
			String districtCode, Integer clinicTypeId, String clinicTypeCode, String circleCode, Double debitNoteEntry,
			List<com.example.demo.EntityModel.Fin06> fin06, List<com.example.demo.EntityModel.Fin03a> fin03a,
			List<com.example.demo.EntityModel.Fin03> fin03,
			List<com.example.demo.EntityModel.CimsHistoryFin02b> cimsHistoryFin02b,
			List<com.example.demo.EntityModel.Fin10b> fin10b,
			List<com.example.demo.EntityModel.Fin05aSiteConformity> fin05aSiteConformity,
			List<com.example.demo.EntityModel.Fin11ConcessionElements> fin11ConcessionElements,
			List<com.example.demo.EntityModel.InvoicePaymentHistory> invoicePaymentHistory, String month, String year,
			LocalDate invoiceDate, Double invoiceBaseValue, Double sst, Double netAfterSst, Double retentionAmount,
			Double totalInvoiceValue, Double totalInvoiceValueWoRetention, Double outstandingAmount, String status,
			LocalDate paymentDate, String paymentStatus, String entryType, String quater, String paymentRefNo,
			Double paymentReceived, Double netRetentionAmount, Integer submittedUserId, Integer approval1UserId,
			Integer approval2UserId, String submittedUserName, String approval1UserName, String approval2UserName,
			String submittedUserDesignation, String approval1UserDesignation, String approval2UserDesignation,
			LocalDate submittedDate, LocalDate approval1Date, LocalDate approval2Date, String remarks, String createdBy,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.code = code;
		this.invoiceNo = invoiceNo;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceTypeName = invoiceTypeName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.circleCode = circleCode;
		this.debitNoteEntry =debitNoteEntry;
	
		Fin06 = fin06;
		Fin03a = fin03a;
		Fin03 = fin03;
		CimsHistoryFin02b = cimsHistoryFin02b;
		Fin10b = fin10b;
		Fin05aSiteConformity = fin05aSiteConformity;
		Fin11ConcessionElements = fin11ConcessionElements;
		InvoicePaymentHistory = invoicePaymentHistory;
		this.month = month;
		this.year = year;
		this.invoiceDate = invoiceDate;
		this.invoiceBaseValue = invoiceBaseValue;
		this.sst = sst;
		this.netAfterSst = netAfterSst;
		this.retentionAmount = retentionAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
		this.outstandingAmount = outstandingAmount;
		this.status = status;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.entryType = entryType;
		this.quater = quater;
		this.paymentRefNo = paymentRefNo;
		this.paymentReceived = paymentReceived;
		this.netRetentionAmount = netRetentionAmount;
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public Integer getInvoiceTypeId() {
		return invoiceTypeId;
	}


	public void setInvoiceTypeId(Integer invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}


	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}


	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
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


	public String getCircleCode() {
		return circleCode;
	}


	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}


	public List<Fin06> getFin06() {
		return Fin06;
	}


	public void setFin06(List<Fin06> fin06) {
		Fin06 = fin06;
	}


	public List<Fin03a> getFin03a() {
		return Fin03a;
	}


	public void setFin03a(List<Fin03a> fin03a) {
		Fin03a = fin03a;
	}


	public List<Fin03> getFin03() {
		return Fin03;
	}


	public void setFin03(List<Fin03> fin03) {
		Fin03 = fin03;
	}


	public List<CimsHistoryFin02b> getCimsHistoryFin02b() {
		return CimsHistoryFin02b;
	}


	public void setCimsHistoryFin02b(List<CimsHistoryFin02b> cimsHistoryFin02b) {
		CimsHistoryFin02b = cimsHistoryFin02b;
	}


	public List<Fin10b> getFin10b() {
		return Fin10b;
	}


	public void setFin10b(List<Fin10b> fin10b) {
		Fin10b = fin10b;
	}


	public List<Fin05aSiteConformity> getFin05aSiteConformity() {
		return Fin05aSiteConformity;
	}


	public void setFin05aSiteConformity(List<Fin05aSiteConformity> fin05aSiteConformity) {
		Fin05aSiteConformity = fin05aSiteConformity;
	}


	public List<Fin11ConcessionElements> getFin11ConcessionElements() {
		return Fin11ConcessionElements;
	}


	public void setFin11ConcessionElements(List<Fin11ConcessionElements> fin11ConcessionElements) {
		Fin11ConcessionElements = fin11ConcessionElements;
	}


	public List<InvoicePaymentHistory> getInvoicePaymentHistory() {
		return InvoicePaymentHistory;
	}


	public void setInvoicePaymentHistory(List<InvoicePaymentHistory> invoicePaymentHistory) {
		InvoicePaymentHistory = invoicePaymentHistory;
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


	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public Double getInvoiceBaseValue() {
		return invoiceBaseValue;
	}


	public void setInvoiceBaseValue(Double invoiceBaseValue) {
		this.invoiceBaseValue = invoiceBaseValue;
	}


	public Double getSst() {
		return sst;
	}


	public void setSst(Double sst) {
		this.sst = sst;
	}


	public Double getNetAfterSst() {
		return netAfterSst;
	}


	public void setNetAfterSst(Double netAfterSst) {
		this.netAfterSst = netAfterSst;
	}


	public Double getRetentionAmount() {
		return retentionAmount;
	}


	public void setRetentionAmount(Double retentionAmount) {
		this.retentionAmount = retentionAmount;
	}


	public Double getTotalInvoiceValue() {
		return totalInvoiceValue;
	}


	public void setTotalInvoiceValue(Double totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}


	public Double getTotalInvoiceValueWoRetention() {
		return totalInvoiceValueWoRetention;
	}


	public void setTotalInvoiceValueWoRetention(Double totalInvoiceValueWoRetention) {
		this.totalInvoiceValueWoRetention = totalInvoiceValueWoRetention;
	}


	public Double getOutstandingAmount() {
		return outstandingAmount;
	}


	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getEntryType() {
		return entryType;
	}


	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}


	public String getQuater() {
		return quater;
	}


	public void setQuater(String quater) {
		this.quater = quater;
	}


	public String getPaymentRefNo() {
		return paymentRefNo;
	}


	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}


	public Double getPaymentReceived() {
		return paymentReceived;
	}


	public void setPaymentReceived(Double paymentReceived) {
		this.paymentReceived = paymentReceived;
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


	public Double getNetRetentionAmount() {
		if(netRetentionAmount==null) netRetentionAmount=0.0;
		return netRetentionAmount;
	}


	public void setNetRetentionAmount(Double netRetentionAmount) {
		this.netRetentionAmount = netRetentionAmount;
	}
	
	

	public Double getDebitNoteEntry() {
		return debitNoteEntry;
	}


	public void setDebitNoteEntry(Double debitNoteEntry) {
		this.debitNoteEntry = debitNoteEntry;
	}


	@Override
	public String toString() {
		return "Invoice [id=" + id + ", code=" + code + ", invoiceNo=" + invoiceNo + ", invoiceTypeId=" + invoiceTypeId
				+ ", invoiceTypeName=" + invoiceTypeName + ", stateId=" + stateId + ", stateName=" + stateName
				+ ", stateCode=" + stateCode + ", districtId=" + districtId + ", districtName=" + districtName
				+ ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode="
				+ clinicTypeCode + ", circleCode=" + circleCode + ", Fin06=" + Fin06 + ", Fin03a=" + Fin03a + ", Fin03="
				+ Fin03 + ", CimsHistoryFin02b=" + CimsHistoryFin02b + ", Fin10b=" + Fin10b + ", Fin05aSiteConformity="
				+ Fin05aSiteConformity + ", Fin11ConcessionElements=" + Fin11ConcessionElements
				+ ", InvoicePaymentHistory=" + InvoicePaymentHistory + ", month=" + month + ", year=" + year
				+ ", invoiceDate=" + invoiceDate + ", invoiceBaseValue=" + invoiceBaseValue + ", sst=" + sst
				+ ", netAfterSst=" + netAfterSst + ", retentionAmount=" + retentionAmount + ", totalInvoiceValue="
				+ totalInvoiceValue + ", totalInvoiceValueWoRetention=" + totalInvoiceValueWoRetention
				+ ", outstandingAmount=" + outstandingAmount + ", status=" + status + ", paymentDate=" + paymentDate
				+ ", paymentStatus=" + paymentStatus + ", entryType=" + entryType + ", quater=" + quater
				+ ", paymentRefNo=" + paymentRefNo + ", paymentReceived=" + paymentReceived + ", netRetentionAmount="
				+ netRetentionAmount + ", submittedUserId=" + submittedUserId + ", approval1UserId=" + approval1UserId
				+ ", approval2UserId=" + approval2UserId + ", submittedUserName=" + submittedUserName
				+ ", approval1UserName=" + approval1UserName + ", approval2UserName=" + approval2UserName
				+ ", submittedUserDesignation=" + submittedUserDesignation + ", approval1UserDesignation="
				+ approval1UserDesignation + ", approval2UserDesignation=" + approval2UserDesignation
				+ ", submittedDate=" + submittedDate + ", approval1Date=" + approval1Date + ", approval2Date="
				+ approval2Date + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}





	

	
	
	
	
	
	
	
	

}

