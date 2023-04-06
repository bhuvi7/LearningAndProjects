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
@Table(name="user_group_menu_mapping")
public class UserGroupMenuMapping {
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
	private Integer menuId;
	@Formula("(select menu.menu_name from m_menu menu  where menu.id=menu_id )")
	private String menuName;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	
	public UserGroupMenuMapping() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserGroupMenuMapping(Integer id, Integer userGroupId, String userGroupName, String userGroupMasterId,
			String userGroupIsActive, Integer menuId, String menuName, String createdBy, LocalDateTime createdDate,
			String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.userGroupId = userGroupId;
		this.userGroupName = userGroupName;
		this.userGroupMasterId = userGroupMasterId;
		this.userGroupIsActive = userGroupIsActive;
		this.menuId = menuId;
		this.menuName = menuName;
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


	public Integer getMenuId() {
		return menuId;
	}


	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
		return "UserGroupMenuMapping [id=" + id + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName
				+ ", userGroupMasterId=" + userGroupMasterId + ", userGroupIsActive=" + userGroupIsActive + ", menuId="
				+ menuId + ", menuName=" + menuName + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}


	

}
