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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_fin11_concession_elements")
public class Fin11ConcessionElements implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
	
	private String typeOfExpenses;
	private String reference;
	private Double concessionElements;
	@Column(name="t_fin11_inv_no")
	private String fin11InvNo;
	@Column(name="fin11_inv_status")
	private String fin11InvStatus;
	private String remarks;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public Fin11ConcessionElements() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fin11ConcessionElements(Integer id, Integer stateId, String stateName, String stateCode, Integer districtId,
			String districtName, String districtCode, Integer clinicTypeId, String clinicTypeCode,
			String typeOfExpenses, String reference, Double concessionElements, String fin11InvNo,
			String fin11InvStatus, String remarks, String createdBy, LocalDateTime createdDate, String updatedBy,
			LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.clinicTypeId = clinicTypeId;
		this.clinicTypeCode = clinicTypeCode;
		this.typeOfExpenses = typeOfExpenses;
		this.reference = reference;
		this.concessionElements = concessionElements;
		this.fin11InvNo = fin11InvNo;
		this.fin11InvStatus = fin11InvStatus;
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

	public String getTypeOfExpenses() {
		return typeOfExpenses;
	}

	public void setTypeOfExpenses(String typeOfExpenses) {
		this.typeOfExpenses = typeOfExpenses;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getConcessionElements() {
		return concessionElements;
	}

	public void setConcessionElements(Double concessionElements) {
		this.concessionElements = concessionElements;
	}

	public String getFin11InvNo() {
		return fin11InvNo;
	}

	public void setFin11InvNo(String fin11InvNo) {
		this.fin11InvNo = fin11InvNo;
	}

	public String getFin11InvStatus() {
		return fin11InvStatus;
	}

	public void setFin11InvStatus(String fin11InvStatus) {
		this.fin11InvStatus = fin11InvStatus;
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

	@Override
	public String toString() {
		return "Fin11ConcessionElements [id=" + id + ", stateId=" + stateId + ", stateName=" + stateName
				+ ", stateCode=" + stateCode + ", districtId=" + districtId + ", districtName=" + districtName
				+ ", districtCode=" + districtCode + ", clinicTypeId=" + clinicTypeId + ", clinicTypeCode="
				+ clinicTypeCode + ", typeOfExpenses=" + typeOfExpenses + ", reference=" + reference
				+ ", concessionElements=" + concessionElements + ", fin11InvNo=" + fin11InvNo + ", fin11InvStatus="
				+ fin11InvStatus + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
