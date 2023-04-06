package com.example.demo.EntityModel;

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
@Table(name="m_district")
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String districtCode;
	private String districtName;
	@Column(name="m_state_id")
	private Integer stateId;
	@Formula("(select state.state_name from m_state state where state.id=m_state_id )")
	private String stateName;
	@Column(name="m_circle_id")
	private Integer circleId;
	@Column(name="office_pkd_name")
	private String officePkdName;
	@Column(name="office_ppd_name")
	private String officePpdName;
	@Column(name="office_pkd_address")
	private String officePkdAddress;
	@Column(name="office_ppd_address")
	private String officePpdAddress;	
	@Formula("(select circle.circle_name from m_circle circle where circle.id=m_circle_id )")
	private String circleName;
	private String isActive;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	public District(Integer id, String districtCode, String districtName, Integer stateId, String stateName,
			Integer circleId, String circleName, String isActive, String createdBy, LocalDateTime createdDate,
			String updatedBy, LocalDateTime updatedDate, String officePkdName, String officePpdName, 
			String officePkdAddress, String officePpdAddress) {
		super();
		this.id = id;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.circleId = circleId;
		this.circleName = circleName;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.officePkdAddress = officePkdAddress;
		this.officePpdAddress = officePpdAddress;
		this.officePkdName = officePkdName;
		this.officePpdName = officePpdName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	
	public String getOfficePkdName() {
		return officePkdName;
	}

	public void setOfficePkdName(String officePkdName) {
		this.officePkdName = officePkdName;
	}

	public String getOfficePpdName() {
		return officePpdName;
	}

	public void setOfficePpdName(String officePpdName) {
		this.officePpdName = officePpdName;
	}

	public String getOfficePkdAddress() {
		return officePkdAddress;
	}

	public void setOfficePkdAddress(String officePkdAddress) {
		this.officePkdAddress = officePkdAddress;
	}

	public String getOfficePpdAddress() {
		return officePpdAddress;
	}

	public void setOfficePpdAddress(String officePpdAddress) {
		this.officePpdAddress = officePpdAddress;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", districtCode=" + districtCode + ", districtName=" + districtName + ", stateId="
				+ stateId + ", stateName=" + stateName + ", circleId=" + circleId + ", circleName=" + circleName
				+ ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", officePkdName = " + officePkdName+ ""
				+ ", officePpdName = " + officePpdName + ", officePkdAddress = " + officePkdAddress + ", officePpdAddress = " + officePpdAddress +"]";
	}


	

	
	
	

}
