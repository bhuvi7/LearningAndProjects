package com.example.demo.EntityModel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="user_detail")
public class UserDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer userId;
	private String userLevel;
	
	@Column(name="m_state_id")
	private Integer stateId;
	
	@Formula("(select state.state_name from m_state state where state.id=m_state_id )")
	private String stateName;

	
	@Column(name="m_district_id")
	private Integer districtId;
	
	@Formula("(select district.district_name from m_district district where district.id=m_district_id )")
	private String districtName;
	
	@Column(name="m_circle_id")
	private Integer circleId;
	
	@Formula("(select mc.circle_code from m_circle mc where mc.id=m_circle_id )")
	private String circleCode;
	
	
	@Column(name="m_clinic_id")
	private Integer clinicId;
	
	@Formula("(select mc.clinic_name from m_clinic mc where mc.id=m_clinic_id )")
	private String clinicName;
	
	@Column(name="user_group_id")
	private Integer userGroupId;
	
	@Formula("(select u.group_code from user_group u where u.id=user_group_id )")
	private String userGroupCode;
	@Formula("(select u.group_name from user_group u where u.id=user_group_id )")
	private String userGroupName;
	@Formula("(select u.group_priority from user_group u where u.id=user_group_id )")
	private String userGroupPriority;

	@Formula("(select ugm.group_master_priority from user_group_master ugm  where ugm.id= (select u.user_group_master_id from user_group u where u.id=user_group_id ))")
	private String userGroupMasterPriority;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id", referencedColumnName = "user_group_id",insertable = false, updatable = false)
	private List<UserGroup> userGroups ;
	
	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetail(Integer id, Integer userId, String userLevel, Integer stateId, String stateName,
			Integer districtId, String districtName, Integer circleId, String circleCode, Integer clinicId,
			String clinicName, Integer userGroupId, String userGroupCode, String userGroupName,
			String userGroupPriority, String userGroupMasterPriority, List<UserGroup> userGroups) {
		super();
		this.id = id;
		this.userId = userId;
		this.userLevel = userLevel;
		this.stateId = stateId;
		this.stateName = stateName;
		this.districtId = districtId;
		this.districtName = districtName;
		this.circleId = circleId;
		this.circleCode = circleCode;
		this.clinicId = clinicId;
		this.clinicName = clinicName;
		this.userGroupId = userGroupId;
		this.userGroupCode = userGroupCode;
		this.userGroupName = userGroupName;
		this.userGroupPriority = userGroupPriority;
		this.userGroupMasterPriority = userGroupMasterPriority;
		this.userGroups = userGroups;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
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

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public String getCircleCode() {
		return circleCode;
	}

	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupCode() {
		return userGroupCode;
	}

	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserGroupPriority() {
		return userGroupPriority;
	}

	public void setUserGroupPriority(String userGroupPriority) {
		this.userGroupPriority = userGroupPriority;
	}

	public String getUserGroupMasterPriority() {
		return userGroupMasterPriority;
	}

	public void setUserGroupMasterPriority(String userGroupMasterPriority) {
		this.userGroupMasterPriority = userGroupMasterPriority;
	}

	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", userId=" + userId + ", userLevel=" + userLevel + ", stateId=" + stateId
				+ ", stateName=" + stateName + ", districtId=" + districtId + ", districtName=" + districtName
				+ ", circleId=" + circleId + ", circleCode=" + circleCode + ", clinicId=" + clinicId + ", clinicName="
				+ clinicName + ", userGroupId=" + userGroupId + ", userGroupCode=" + userGroupCode + ", userGroupName="
				+ userGroupName + ", userGroupPriority=" + userGroupPriority + ", userGroupMasterPriority="
				+ userGroupMasterPriority + ", userGroups=" + userGroups + "]";
	}
	

}
