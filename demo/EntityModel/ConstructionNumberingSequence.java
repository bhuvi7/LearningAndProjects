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
@Table(name="construction_numbering_sequence")
public class ConstructionNumberingSequence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer stateId;
	private String stateCode;
	private String stateName;
	private Integer clinicTypeId;
	private String clinicTypeCode;
	private Integer constructionWorkingSequence;
	private String isActive;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	public ConstructionNumberingSequence() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConstructionNumberingSequence(Integer id, Integer stateId, String stateCode, String stateName,
			Integer clinicTypeId, String clinicTypeCode, Integer constructionWorkingSequence, String isActive,
			LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.constructionWorkingSequence = constructionWorkingSequence;
		this.isActive = isActive;
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
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	public Integer getConstructionWorkingSequence() {
		return constructionWorkingSequence;
	}
	public void setConstructionWorkingSequence(Integer constructionWorkingSequence) {
		this.constructionWorkingSequence = constructionWorkingSequence;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	
	

}
