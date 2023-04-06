package com.example.demo.EntityModel;

public class InvoiceGeneration {
	
	public Integer stateId;
	public Integer circleId;
	public Integer districtId;
	public String stateName;
	public String circleName;
	public String districtName;
	public Integer clinicCount;
	public Integer equipmentCount;
	public Double amount;
	
	public InvoiceGeneration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceGeneration(Integer stateId, Integer circleId, Integer districtId, String stateName, String circleName,
			String districtName, Integer clinicCount, Integer equipmentCount, Double amount) {
		super();
		this.stateId = stateId;
		this.circleId = circleId;
		this.districtId = districtId;
		this.stateName = stateName;
		this.circleName = circleName;
		this.districtName = districtName;
		this.clinicCount = clinicCount;
		this.equipmentCount = equipmentCount;
		this.amount = amount;
	}

	
	
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getClinicCount() {
		return clinicCount;
	}

	public void setClinicCount(Integer clinicCount) {
		this.clinicCount = clinicCount;
	}

	public Integer getEquipmentCount() {
		return equipmentCount;
	}

	public void setEquipmentCount(Integer equipmentCount) {
		this.equipmentCount = equipmentCount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "InvoiceGeneration [stateId=" + stateId + ", circleId=" + circleId + ", districtId=" + districtId
				+ ", stateName=" + stateName + ", circleName=" + circleName + ", districtName=" + districtName
				+ ", clinicCount=" + clinicCount + ", equipmentCount=" + equipmentCount + ", amount=" + amount + "]";
	}

	
	
	
	
	
}
