package com.example.demo.EntityModel;


import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   
    private String groupCode;
    private String groupName;
    private Integer groupPriority; 
	private String isActive;
	private String createdBy;
	private Integer userGroupMasterId;
	@Formula("(select ugm.no_of_level from user_group_master ugm  where ugm.id=user_group_master_id )")
	private Integer userGroupMasterNoOfLevel;
	@Formula("(select ugm.name from user_group_master ugm  where ugm.id=user_group_master_id )")
	private String userGroupMasterName;
	@Formula("(select ugm.group_master_priority from user_group_master ugm  where ugm.id=user_group_master_id )")
	private Integer userGroupMasterPriority;
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "user_group_role_mapping", 
	joinColumns = { @JoinColumn(name = "userGroupId") }, 
	inverseJoinColumns = { @JoinColumn(name = "userRoleId") })
	private List<UserRole> userRoles ;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
    public UserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGroup(Integer id, String groupCode, String groupName, Integer groupPriority, String isActive,
			String createdBy, Integer userGroupMasterId, Integer userGroupMasterNoOfLevel, String userGroupMasterName,
			Integer userGroupMasterPriority, List<UserRole> userRoles, LocalDateTime createdDate, String updatedBy,
			LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.groupPriority = groupPriority;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.userGroupMasterId = userGroupMasterId;
		this.userGroupMasterNoOfLevel = userGroupMasterNoOfLevel;
		this.userGroupMasterName = userGroupMasterName;
		this.userGroupMasterPriority = userGroupMasterPriority;
		this.userRoles = userRoles;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupPriority() {
		return groupPriority;
	}

	public void setGroupPriority(Integer groupPriority) {
		this.groupPriority = groupPriority;
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

	public Integer getUserGroupMasterId() {
		return userGroupMasterId;
	}

	public void setUserGroupMasterId(Integer userGroupMasterId) {
		this.userGroupMasterId = userGroupMasterId;
	}

	public Integer getUserGroupMasterNoOfLevel() {
		return userGroupMasterNoOfLevel;
	}

	public void setUserGroupMasterNoOfLevel(Integer userGroupMasterNoOfLevel) {
		this.userGroupMasterNoOfLevel = userGroupMasterNoOfLevel;
	}

	public String getUserGroupMasterName() {
		return userGroupMasterName;
	}

	public void setUserGroupMasterName(String userGroupMasterName) {
		this.userGroupMasterName = userGroupMasterName;
	}

	public Integer getUserGroupMasterPriority() {
		return userGroupMasterPriority;
	}

	public void setUserGroupMasterPriority(Integer userGroupMasterPriority) {
		this.userGroupMasterPriority = userGroupMasterPriority;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
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
		return "UserGroup [id=" + id + ", groupCode=" + groupCode + ", groupName=" + groupName + ", groupPriority="
				+ groupPriority + ", isActive=" + isActive + ", createdBy=" + createdBy + ", userGroupMasterId="
				+ userGroupMasterId + ", userGroupMasterNoOfLevel=" + userGroupMasterNoOfLevel
				+ ", userGroupMasterName=" + userGroupMasterName + ", userGroupMasterPriority="
				+ userGroupMasterPriority + ", userRoles=" + userRoles + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	

	
	     
  
}
