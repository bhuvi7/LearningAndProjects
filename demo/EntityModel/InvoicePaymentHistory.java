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
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_invoice_payment_history")
public class InvoicePaymentHistory implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_inv_ref_no")
	private String invoiceNo;
	private String paymentMode;
	private Double paymentReceived;
	private String paymentRefNo;
	private String transactionRefNo;
	private String createdBy;
	private LocalDate paymentDate;
	
	
	
	@Column(name="fin09_invoice_no")
	private String fin09InvoiceNo;
	
	public String getFin09InvoiceNo() {
		return fin09InvoiceNo;
	}

	public void setFin09InvoiceNo(String fin09InvoiceNo) {
		this.fin09InvoiceNo = fin09InvoiceNo;
	}

	@Formula("(SELECT DISTINCT invoice.sst FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Double sst;	
	@Formula("(SELECT DISTINCT invoice.total_invoice_value FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Double totalInvoiceValue;	
	@Formula("(SELECT DISTINCT invoice.invoice_base_value FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Double invoiceBaseValue;
	@Formula("(SELECT DISTINCT invoice.retention_amount FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Double retentionAmount;
	@Formula("(SELECT DISTINCT invoice.outstanding_amount FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Double outstandingAmount;	
	@Formula("(select DISTINCT invoice.m_state_id from t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Integer stateId;	
	@Formula("(select DISTINCT state.state_name FROM m_state state WHERE state.id=(select DISTINCT invoice.m_state_id from t_invoice invoice where invoice.invoice_no=t_inv_ref_no ))")
	private String stateName;
	@Formula("(select DISTINCT invoice.m_district_id FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Integer districtId;	
	@Formula("(select DISTINCT district.district_name FROM m_district district WHERE district.id=(select DISTINCT invoice.m_district_id from t_invoice invoice where invoice.invoice_no=t_inv_ref_no ))")
	private String districtName;
	@Formula("(select DISTINCT invoice.m_clinic_type_id FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no )")
	private Integer clinicTypeId;
	@Formula("(SELECT DISTINCT invoice.m_invoice_type_id FROM t_invoice invoice WHERE invoice.invoice_no=t_inv_ref_no)")
	private Integer invoiceTypeId; 	
	@Formula("(select DISTINCT clinictype.clinic_type_code from m_clinic_type clinictype where clinictype.id=(select DISTINCT invoice.m_clinic_type_id from t_invoice invoice where invoice.invoice_no=t_inv_ref_no ) )")
	private String clinicTypeCode;
	@Formula("(select DISTINCT invoicetype.invoice_type_name from m_invoice_type invoicetype  where invoicetype.id=(select DISTINCT invoice.m_invoice_type_id from t_invoice invoice where invoice.invoice_no=t_inv_ref_no ) )")
	private String invoiceTypeName;
	//mine
	@Formula("(select DISTINCT x.name FROM user x WHERE x.id= updated_by )")
	private String updatedName;
	
	

	@Column(name="updated_by")
	private String updatedBy;
//	@Formula("(SELECT DISTINCT invoice.name FROM user invoice WHERE invoice.id=updatedBy)")
//	private String updatedBy;
		
	@CreationTimestamp
	private LocalDateTime createdDate;
//	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public InvoicePaymentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoicePaymentHistory(Integer id, String invoiceNo, String paymentMode, Double paymentReceived,
			String paymentRefNo, LocalDate paymentDate, String transactionRefNo, String createdBy,
			LocalDateTime createdDate, String updatedBy,String updatedName, LocalDateTime updatedDate, String stateName, String districtName,
			Integer clinicTypeId, Integer invoiceTypeId, Integer stateId, Integer districtId, Double sst, Double invoiceBaseValue,
			Double retentionAmount, Double outstandingAmount, Double totalInvoiceValue, String invoiceTypeName, String clinicTypeCode) {
		super();
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.paymentMode = paymentMode;
		this.paymentReceived = paymentReceived;
		this.paymentRefNo = paymentRefNo;
		this.paymentDate = paymentDate;
		this.transactionRefNo = transactionRefNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedName =updatedName;
		this.updatedDate = updatedDate;		
		this.stateName = stateName;
		this.districtName = districtName;
		this.clinicTypeId = clinicTypeId;
		this.invoiceTypeId = invoiceTypeId;		
		this.stateId = stateId;
		this.districtId = districtId; 
		this.invoiceBaseValue = invoiceBaseValue;
		this.sst = sst;
		this.retentionAmount = retentionAmount;
		this.outstandingAmount = outstandingAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.clinicTypeCode = clinicTypeCode;
		this.invoiceTypeName = invoiceTypeName;
		
	}
	
	public InvoicePaymentHistory(Integer id, String invoiceNo, Integer stateId,  String stateName, Integer districtId, String districtName,
			Integer clinicTypeId, LocalDate paymentDate, String paymentMode,String transactionRefNo, String paymentRefNo,  Integer invoiceTypeId, Double sst,
			Double retentionAmount, Double invoiceBaseReport, Double outstandingAmount, Double totalInvoiceValue, String invoiceTypeName, String clinicTypeCode
			 ) {
		super();
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.stateId = stateId;
		this.paymentMode = paymentMode;		
		this.districtId = districtId;
		this.paymentDate = paymentDate;
		this.paymentRefNo = paymentRefNo;	
		this.transactionRefNo = transactionRefNo;		
		this.stateName = stateName;
		this.districtName = districtName;
		this.clinicTypeId = clinicTypeId;
		this.invoiceTypeId = invoiceTypeId;
		this.sst = sst;
		this.retentionAmount = retentionAmount;
		this.outstandingAmount = outstandingAmount;
		this.invoiceBaseValue = invoiceBaseValue;
		this.totalInvoiceValue = totalInvoiceValue;
		this.clinicTypeCode = clinicTypeCode;
		this.invoiceTypeName = invoiceTypeName;
		
	
	}
	
	
	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(Double paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public String getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTransactionRefNo() {
		return transactionRefNo;
	}

	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
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

	public Integer getClinicTypeId() {
		return clinicTypeId;
	}

	public void setClinicTypeId(Integer clinicTypeId) {
		this.clinicTypeId = clinicTypeId;
	}

	public Integer getInvoiceTypeId() {
		return invoiceTypeId;
	}

	public void setInvoiceTypeId(Integer invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}
	
	public Double getSst() {
		return sst;
	}

	public void setSst(Double sst) {
		this.sst = sst;
	}

	public Double getInvoiceBaseValue() {
		return invoiceBaseValue;
	}

	public void setInvoiceBaseValue(Double invoiceBaseValue) {
		this.invoiceBaseValue = invoiceBaseValue;
	}

	public Double getRetentionAmount() {
		return retentionAmount;
	}

	public void setRetentionAmount(Double retentionAmount) {
		this.retentionAmount = retentionAmount;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	
	public Double getTotalInvoiceValue() {
		return totalInvoiceValue;
	}

	public void setTotalInvoiceValue(Double totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}
	
	public String getClinicTypeCode() {
		return clinicTypeCode;
	}

	public void setClinicTypeCode(String clinicTypeCode) {
		this.clinicTypeCode = clinicTypeCode;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	@Override
	public String toString() {
		return "InvoicePaymentHistory [id=" + id + ", invoiceNo=" + invoiceNo + ", paymentMode=" + paymentMode
				+ ", paymentReceived=" + paymentReceived + ", paymentRefNo=" + paymentRefNo + ", paymentDate="
				+ paymentDate + ", transactionRefNo=" + transactionRefNo + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ""
						+ "clinicTypeId = "+ clinicTypeId +", stateName = "+ stateName +","
						+ "districtName = "+ districtName+",invoiceTypeId = "+ invoiceTypeId +""  
								+ "sst = "+ sst +",outstandingAmount = "+ outstandingAmount +",retentionAmount = "+ retentionAmount+""
										+ "invoiceBaseValue = "+ invoiceBaseValue +" totalInvoiceValue = "+ totalInvoiceValue + ""
												+ "invoiceTypeName = "+ invoiceTypeName+" +clinicTypeCode = "+ clinicTypeCode+"]";
								
	}

	

	
	
	

}
