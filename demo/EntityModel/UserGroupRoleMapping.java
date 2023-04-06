package com.example.demo.EntityModel;

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
@Table(name="user_group_role_mapping")
public class UserGroupRoleMapping {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer userGroupId;
	@Formula("(select usergroup.group_name from user_group usergroup  where usergroup.id=user_group_id )")
	private String userGroupName;
	@Formula("(select usergroup.user_group_master_id from user_group usergroup  where usergroup.id=user_group_id )")
	private String userGroupMasterId;
	@Formula("(select usergroup.is_active from user_group usergroup  where usergroup.id=user_group_id )")
	private String userGroupIsActive;
	private Integer userRoleId;
	@Formula("(select userrole.role_name from user_role userrole  where userrole.id=user_role_id )")
	private String userRoleName;
	@Formula("(select userrole.is_active from user_role userrole  where userrole.id=user_role_id )")
	private String userRoleIsActive;
	private String isActive;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	
	public UserGroupRoleMapping() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserGroupRoleMapping(Integer id, Integer userGroupId, String userGroupName, String userGroupMasterId,
			String userGroupIsActive, Integer userRoleId, String userRoleName, String userRoleIsActive, String isActive,
			String createdBy, LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.userGroupId = userGroupId;
		this.userGroupName = userGroupName;
		this.userGroupMasterId = userGroupMasterId;
		this.userGroupIsActive = userGroupIsActive;
		this.userRoleId = userRoleId;
		this.userRoleName = userRoleName;
		this.userRoleIsActive = userRoleIsActive;
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


	public Integer getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}


	public String getUserGroupName() {
		return userGroupName;
	}


	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}


	public String getUserGroupMasterId() {
		return userGroupMasterId;
	}


	public void setUserGroupMasterId(String userGroupMasterId) {
		this.userGroupMasterId = userGroupMasterId;
	}


	public String getUserGroupIsActive() {
		return userGroupIsActive;
	}


	public void setUserGroupIsActive(String userGroupIsActive) {
		this.userGroupIsActive = userGroupIsActive;
	}


	public Integer getUserRoleId() {
		return userRoleId;
	}


	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}


	public String getUserRoleName() {
		return userRoleName;
	}


	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}


	public String getUserRoleIsActive() {
		return userRoleIsActive;
	}


	public void setUserRoleIsActive(String userRoleIsActive) {
		this.userRoleIsActive = userRoleIsActive;
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
		return "UserGroupRoleMapping [id=" + id + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName
				+ ", userGroupMasterId=" + userGroupMasterId + ", userGroupIsActive=" + userGroupIsActive
				+ ", userRoleId=" + userRoleId + ", userRoleName=" + userRoleName + ", userRoleIsActive="
				+ userRoleIsActive + ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}


	
	
	

}
