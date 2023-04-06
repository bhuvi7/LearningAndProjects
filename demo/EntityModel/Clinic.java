package com.example.demo.EntityModel;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="m_clinic")
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String clinicCode;
	private String clinicName;
	private String address;
	@Column(name="phoneNumber",columnDefinition = "bigint")
	private BigInteger phoneNumber;
	@Column(name="email_id")
	private String clinicEmail;
	
	@Formula("(select clinictype.clinic_type_code from m_clinic_type clinictype where clinictype.id=m_clinic_type_id )")
	private String clinicTypeCode;
	
	@Column(name="clinic_contact_number1")
	private String clinicContaactNumber1;
	@Column(name="clinic_contact_number2")
	private String clinicContaactNumber2;
	@Column(name="clinic_fax_number")
	private String clinicFaxNumber;
	
	
	@Column(name="m_state_id")
	private Integer stateId;
	
	@Formula("(select state.state_name from m_state state where state.id=m_state_id )")
	private String stateName;
	
	@Formula("(select state.state_code from m_state state where state.id=m_state_id )")
	private String stateCode;
	
	@Column(name="m_district_id")
	private Integer districtId;
	
	@Column(name="m_clinic_type_id")
	private Integer clinicTypeId;
	
	@Formula("(select district.district_name from m_district district where district.id=m_district_id )")
	private String districtName;
	
	@Formula("(select district.district_code from m_district district where district.id=m_district_id )")
	private String districtCode;
	

	@Column(name="m_circle_id")
	private Integer circleId;
	
	
	@Formula("(select circle.circle_name from m_circle circle where circle.id=m_circle_id )")
	private String circleName;
	@Column(name="m_zone_id")
	private Integer zoneId;
	
	@Column(name="m_region_id")
	private Integer regionId;
	
	@Formula("(select clinictype.clinic_type_name from m_clinic_type clinictype where clinictype.id=m_clinic_type_id )")
	private String clinicTypeName;
	
	
	@Column(name="is_active")
	private String isActive;
	
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public Clinic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clinic(Integer id, String clinicCode, String clinicName, String address, BigInteger phoneNumber, String clinicEmail,
			String clinicContaactNumber1, String clinicContaactNumber2, String clinicFaxNumber, Integer stateId,
			String stateName, String stateCode, Integer districtId, Integer clinicTypeId, String districtName,
			String districtCode, Integer circleId, String circleName, Integer zoneId, Integer regionId,
			String clinicTypeName, String clinicTypeCode, String isActive, String createdBy, LocalDateTime createdDate,
			String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.clinicCode = clinicCode;
		this.clinicName = clinicName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.clinicEmail = clinicEmail;
		this.clinicContaactNumber1 = clinicContaactNumber1;
		this.clinicContaactNumber2 = clinicContaactNumber2;
		this.clinicFaxNumber = clinicFaxNumber;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.clinicTypeId = clinicTypeId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.circleId = circleId;
		this.circleName = circleName;
		this.zoneId = zoneId;
		this.regionId = regionId;
		this.clinicTypeName = clinicTypeName;
		this.clinicTypeCode = clinicTypeCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getclinicEmail() {
		return clinicEmail;
	}

	public void setclinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}

	public String getClinicContaactNumber1() {
		return clinicContaactNumber1;
	}

	public void setClinicContaactNumber1(String clinicContaactNumber1) {
		this.clinicContaactNumber1 = clinicContaactNumber1;
	}

	public String getClinicContaactNumber2() {
		return clinicContaactNumber2;
	}

	public void setClinicContaactNumber2(String clinicContaactNumber2) {
		this.clinicContaactNumber2 = clinicContaactNumber2;
	}

	public String getClinicFaxNumber() {
		return clinicFaxNumber;
	}

	public void setClinicFaxNumber(String clinicFaxNumber) {
		this.clinicFaxNumber = clinicFaxNumber;
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

	public Integer getClinicTypeId() {
		return clinicTypeId;
	}

	public void setClinicTypeId(Integer clinicTypeId) {
		this.clinicTypeId = clinicTypeId;
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

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getClinicTypeName() {
		return clinicTypeName;
	}

	public void setClinicTypeName(String clinicTypeName) {
		this.clinicTypeName = clinicTypeName;
	}

	public String getClinicTypeCode() {
		return clinicTypeCode;
	}

	public void setClinicTypeCode(String clinicTypeCode) {
		this.clinicTypeCode = clinicTypeCode;
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
		return "Clinic [id=" + id + ", clinicCode=" + clinicCode + ", clinicName=" + clinicName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", clinicEmail=" + clinicEmail + ", clinicContaactNumber1="
				+ clinicContaactNumber1 + ", clinicContaactNumber2=" + clinicContaactNumber2 + ", clinicFaxNumber="
				+ clinicFaxNumber + ", stateId=" + stateId + ", stateName=" + stateName + ", stateCode=" + stateCode
				+ ", districtId=" + districtId + ", clinicTypeId=" + clinicTypeId + ", districtName=" + districtName
				+ ", districtCode=" + districtCode + ", circleId=" + circleId + ", circleName=" + circleName
				+ ", zoneId=" + zoneId + ", regionId=" + regionId + ", clinicTypeName=" + clinicTypeName
				+ ", clinicTypeCode=" + clinicTypeCode + ", isActive=" + isActive + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
