package com.example.demo.EntityModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String userName;
	private String password;
	private String isActive;
	private String designation;
	private String keyForPasswordChange;
	@Column(name="m_organisation_id")
	private Integer organisationId;
	
	@Formula("(select org.organisation_name from m_organisation org where org.id=m_organisation_id)")
	private String organisationName;
	
	@Column(name="m_application_id")
	private Integer applicationId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="userId", referencedColumnName = "id",insertable = true, updatable = true)
	private List<UserDetail> userDetails ;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(Integer id, String name, String email, String userName, String password, String isActive,
			String designation, String keyForPasswordChange, Integer organisationId, String organisationName, Integer applicationId,
			List<UserDetail> userDetails) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
		this.designation = designation;
		this.keyForPasswordChange = keyForPasswordChange;
		this.organisationId = organisationId;
		this.organisationName = organisationName;
		this.applicationId = applicationId;
		this.userDetails = userDetails;
	}



	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getKeyForPasswordChange() {
		return keyForPasswordChange;
	}



	public void setKeyForPasswordChange(String keyForPasswordChange) {
		this.keyForPasswordChange = keyForPasswordChange;
	}



	public Integer getOrganisationId() {
		return organisationId;
	}



	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}



	public String getOrganisationName() {
		return organisationName;
	}



	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}



	public Integer getApplicationId() {
		return applicationId;
	}



	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}



	public List<UserDetail> getUserDetails() {
		return userDetails;
	}



	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", isActive=" + isActive + ",  keyForPasswordChange=" + keyForPasswordChange + ",  designation=" + designation + ", organisationId="
				+ organisationId + ", organisationName=" + organisationName + ", applicationId=" + applicationId
				+ ", userDetails=" + userDetails + "]";
	}

	
	
	
	
}
