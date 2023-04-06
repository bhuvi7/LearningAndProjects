package com.example.demo.EntityModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

@Entity
//@Table(name="cammsbo")
public class CammsBO {

	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Integer id;
	
	private String BENumber;
	private String BECategory;
	private String ClinicCategory;
	private String Zone;
	private String ClinicCode;
	private String District;
	private String Circle;
	private Date InstallationDate;
	private Date TCDate;
	private Date AcceptanceDate;
	private Date RentalStartDate;
	private Date RentalEndDate;
	private Date WarrantyExpiryDate;
	private Date WarrantyStartDate;
	private Date PurchaseDate;
	private String ClinicType;
	private String State;
	private float MonthlyRev;
	private float RentalPerMonth;
	private String BatchNumber;
	private String Ownership;
	private float PurchaseCost;
	private String ModelNumber;
	private String SerialNumber;
	private String CurrentInstallmentNo;
	private String BEConditionalStatus;
	private Date AuditStartDate;
	private String finCategory;
	private String isMigrated;
	private String actionType;
	
	public CammsBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CammsBO(Integer id, String bENumber, String bECategory, String clinicCategory, String zone,
			String clinicCode, String district, String circle, Date installationDate, Date tCDate, Date acceptanceDate,
			Date rentalStartDate, Date rentalEndDate, Date warrantyExpiryDate, Date warrantyStartDate,
			Date purchaseDate, String clinicType, String state, float monthlyRev, float rentalPerMonth,
			String batchNumber, String ownership, float purchaseCost, String modelNumber, String serialNumber,
			String currentInstallmentNo, String bEConditionalStatus, Date auditStartDate, String finCategory, String actionType) {
		super();
		this.id = id;
		this.BENumber = bENumber;
		this.BECategory = bECategory;
		this.ClinicCategory = clinicCategory;
		this.Zone = zone;
		this.ClinicCode = clinicCode;
		this.District = district;
		this.Circle = circle;
		this.InstallationDate = installationDate;
		this.TCDate = tCDate;
		this.AcceptanceDate = acceptanceDate;
		this.RentalStartDate = rentalStartDate;
		this.RentalEndDate = rentalEndDate;
		this.WarrantyExpiryDate = warrantyExpiryDate;
		this.WarrantyStartDate = warrantyStartDate;
		this.PurchaseDate = purchaseDate;
		this.ClinicType = clinicType;
		this.State = state;
		this.MonthlyRev = monthlyRev;
		this.RentalPerMonth = rentalPerMonth;
		this.BatchNumber = batchNumber;
		this.Ownership = ownership;
		this.PurchaseCost = purchaseCost;
		this.ModelNumber = modelNumber;
		this.SerialNumber = serialNumber;
		this.CurrentInstallmentNo = currentInstallmentNo;
		this.BEConditionalStatus = bEConditionalStatus;
		this.AuditStartDate = auditStartDate;
		this.finCategory = finCategory;
	}



	public String getBENumber() {
		return BENumber;
	}
	public void setBENumber(String bENumber) {
		BENumber = bENumber;
	}
	public String getBECategory() {
		return BECategory;
	}
	public void setBECategory(String bECategory) {
		BECategory = bECategory;
	}
	public String getClinicCategory() {
		return ClinicCategory;
	}
	public void setClinicCategory(String clinicCategory) {
		ClinicCategory = clinicCategory;
	}
	public String getZone() {
		return Zone;
	}
	public void setZone(String zone) {
		Zone = zone;
	}
	public String getClinicCode() {
		return ClinicCode;
	}
	public void setClinicCode(String clinicCode) {
		ClinicCode = clinicCode;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getCircle() {
		return Circle;
	}
	public void setCircle(String circle) {
		Circle = circle;
	}
	public Date getInstallationDate() {
		return InstallationDate;
	}
	public void setInstallationDate(Date installationDate) {
		InstallationDate = installationDate;
	}
	public Date getTCDate() {
		return TCDate;
	}
	public void setTCDate(Date tCDate) {
		TCDate = tCDate;
	}
	public Date getAcceptanceDate() {
		return AcceptanceDate;
	}
	public void setAcceptanceDate(Date acceptanceDate) {
		AcceptanceDate = acceptanceDate;
	}
	public Date getRentalStartDate() {
		return RentalStartDate;
	}
	public void setRentalStartDate(Date rentalStartDate) {
		RentalStartDate = rentalStartDate;
	}
	public Date getRentalEndDate() {
		return RentalEndDate;
	}
	public void setRentalEndDate(Date rentalEndDate) {
		RentalEndDate = rentalEndDate;
	}
	public Date getWarrantyExpiryDate() {
		return WarrantyExpiryDate;
	}
	public void setWarrantyExpiryDate(Date warrantyExpiryDate) {
		WarrantyExpiryDate = warrantyExpiryDate;
	}
	public Date getWarrantyStartDate() {
		return WarrantyStartDate;
	}
	public void setWarrantyStartDate(Date warrantyStartDate) {
		WarrantyStartDate = warrantyStartDate;
	}
	public Date getPurchaseDate() {
		return PurchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	public String getClinicType() {
		return ClinicType;
	}
	public void setClinicType(String clinicType) {
		ClinicType = clinicType;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public float getMonthlyRev() {
		return MonthlyRev;
	}
	public void setMonthlyRev(float monthlyRev) {
		MonthlyRev = monthlyRev;
	}
	public float getRentalPerMonth() {
		return RentalPerMonth;
	}
	public void setRentalPerMonth(float rentalPerMonth) {
		RentalPerMonth = rentalPerMonth;
	}
	public String getBatchNumber() {
		return BatchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		BatchNumber = batchNumber;
	}
	public String getOwnership() {
		return Ownership;
	}
	public void setOwnership(String ownership) {
		Ownership = ownership;
	}
	public float getPurchaseCost() {
		return PurchaseCost;
	}
	public void setPurchaseCost(float purchaseCost) {
		PurchaseCost = purchaseCost;
	}
	public String getModelNumber() {
		return ModelNumber;
	}
	public void setModelNumber(String modelNumber) {
		ModelNumber = modelNumber;
	}
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getCurrentInstallmentNo() {
		return CurrentInstallmentNo;
	}
	public void setCurrentInstallmentNo(String currentInstallmentNo) {
		CurrentInstallmentNo = currentInstallmentNo;
	}
	public String getBEConditionalStatus() {
		return BEConditionalStatus;
	}
	public void setBEConditionalStatus(String bEConditionalStatus) {
		BEConditionalStatus = bEConditionalStatus;
	}
	public Date getAuditStartDate() {
		return AuditStartDate;
	}
	public void setAuditStartDate(Date auditStartDate) {
		AuditStartDate = auditStartDate;
	}
	public String getFinCategory() {
		return finCategory;
	}
	public void setFinCategory(String finCategory) {
		this.finCategory = finCategory;
	}

	public String getIsMigrated() {
		return isMigrated;
	}

	public void setIsMigrated(String isMigrated) {
		this.isMigrated = isMigrated;
	}



	public String getActionType() {
		return actionType;
	}



	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	
	
}