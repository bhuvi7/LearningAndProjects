package com.example.demo.EntityModel;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

public class RevenueAnalysisLastQuater extends Invoice {
	
	private Integer id;
	private String code;
	private String invoiceNo;

	private Integer invoiceTypeId;
	private String invoiceTypeName;
	
	private Integer stateId;
	
	private String stateName;
	
	private String stateCode;
	
	private Integer districtId;
	
	private String districtName;
	
	private String districtCode;
	
	private String circleCode;
	private String quater;
	
	private String month;
	private String year;
	private LocalDate invoiceDate;
	private Double retentionAmount;
	private Double outstandingAmount;
	private Double totalInvoiceValue;
	private String paymentStatus;
	
	public RevenueAnalysisLastQuater() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public RevenueAnalysisLastQuater(Integer stateId, String stateName, Double totalInvoiceValue) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.totalInvoiceValue = totalInvoiceValue;
	}
	
	

	public RevenueAnalysisLastQuater(Integer stateId, String stateName,String circleCode, Double totalInvoiceValue) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.circleCode = circleCode;
		this.totalInvoiceValue = totalInvoiceValue;
	}



	public RevenueAnalysisLastQuater(String circleCode, Integer districtId, String districtName, Double totalInvoiceValue) {
		super();
		this.circleCode = circleCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.totalInvoiceValue = totalInvoiceValue;
	}


	public RevenueAnalysisLastQuater(String districtName, String quater, Double totalInvoiceValue) {
		super();
		this.districtName = districtName;
		this.quater = quater;
		this.totalInvoiceValue = totalInvoiceValue;
	}
	
	




	public RevenueAnalysisLastQuater( String invoiceTypeName, Double totalInvoiceValue) {
		super();
		this.invoiceTypeName = invoiceTypeName;
		this.totalInvoiceValue = totalInvoiceValue;
	}
	




	public RevenueAnalysisLastQuater( Double totalInvoiceValue,String quater, String year) {
		super();
		this.quater = quater;
		this.year = year;
		this.totalInvoiceValue = totalInvoiceValue;
	}

	public RevenueAnalysisLastQuater( Double totalInvoiceValue,String quater, String month,String year) {
		super();
		this.quater = quater;
		this.year = year;
		this.totalInvoiceValue = totalInvoiceValue;
		this.month = month;
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




	public String getQuater() {
		return quater;
	}




	public void setQuater(String quater) {
		this.quater = quater;
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




	public String getPaymentStatus() {
		return paymentStatus;
	}




	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	



	public Double getTotalInvoiceValue() {
		if(totalInvoiceValue==null) {totalInvoiceValue=0.0;};
		return totalInvoiceValue;
	}




	public void setTotalInvoiceValue(Double totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}




	public String getCircleCode() {
		return circleCode;
	}




	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}




	@Override
	public String toString() {
		return "RevenueAnalysisLastQuater [id=" + id + ", code=" + code + ", invoiceNo=" + invoiceNo
				+ ", invoiceTypeId=" + invoiceTypeId + ", invoiceTypeName=" + invoiceTypeName + ", stateId=" + stateId
				+ ", stateName=" + stateName + ", stateCode=" + stateCode + ", districtId=" + districtId
				+ ", districtName=" + districtName + ", districtCode=" + districtCode + ", circleCode=" + circleCode
				+ ", quater=" + quater + ", month=" + month + ", year=" + year + ", invoiceDate=" + invoiceDate
				+ ", retentionAmount=" + retentionAmount + ", outstandingAmount=" + outstandingAmount
				+ ", totalInvoiceValue=" + totalInvoiceValue + ", paymentStatus=" + paymentStatus + "]";
	}




	
	
	
	
	
	
	

}
