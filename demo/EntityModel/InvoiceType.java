package com.example.demo.EntityModel;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name="m_invoice_type")
public class InvoiceType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String invoiceTypeName;
	private String invoiceTypeCode;
	private String retentionAvailable;
	private Double retentionPercentage;
	private String sstIncluded;
	private Double sstPercentage;
	private String penaltyAdjustmentApplicable;
	private String description;
	private String isActive;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public InvoiceType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceType(Integer id, String invoiceTypeName, String invoiceTypeCode, String retentionAvailable,
			Double retentionPercentage, String sstIncluded, Double sstPercentage, String penaltyAdjustmentApplicable,
			String description, String isActive, String createdBy, LocalDateTime createdDate, String updatedBy,
			LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.invoiceTypeName = invoiceTypeName;
		this.invoiceTypeCode = invoiceTypeCode;
		this.retentionAvailable = retentionAvailable;
		this.retentionPercentage = retentionPercentage;
		this.sstIncluded = sstIncluded;
		this.sstPercentage = sstPercentage;
		this.penaltyAdjustmentApplicable = penaltyAdjustmentApplicable;
		this.description = description;
		this.isActive = isActive;
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

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getRetentionAvailable() {
		return retentionAvailable;
	}

	public void setRetentionAvailable(String retentionAvailable) {
		this.retentionAvailable = retentionAvailable;
	}

	public Double getRetentionPercentage() {
		return retentionPercentage;
	}

	public void setRetentionPercentage(Double retentionPercentage) {
		this.retentionPercentage = retentionPercentage;
	}

	public String getSstIncluded() {
		return sstIncluded;
	}

	public void setSstIncluded(String sstIncluded) {
		this.sstIncluded = sstIncluded;
	}

	public Double getSstPercentage() {
		return sstPercentage;
	}

	public void setSstPercentage(Double sstPercentage) {
		this.sstPercentage = sstPercentage;
	}

	public String getPenaltyAdjustmentApplicable() {
		return penaltyAdjustmentApplicable;
	}

	public void setPenaltyAdjustmentApplicable(String penaltyAdjustmentApplicable) {
		this.penaltyAdjustmentApplicable = penaltyAdjustmentApplicable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
		return "InvoiceType [id=" + id + ", invoiceTypeName=" + invoiceTypeName + ", invoiceTypeCode=" + invoiceTypeCode
				+ ", retentionAvailable=" + retentionAvailable + ", retentionPercentage=" + retentionPercentage
				+ ", sstIncluded=" + sstIncluded + ", sstPercentage=" + sstPercentage + ", penaltyAdjustmentApplicable="
				+ penaltyAdjustmentApplicable + ", description=" + description + ", isActive=" + isActive
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}

		

}
