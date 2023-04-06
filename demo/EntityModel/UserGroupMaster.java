package com.example.demo.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_group_master")
public class UserGroupMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	private Integer noOfLevel;
	
	public UserGroupMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGroupMaster(Integer id, String name, Integer noOfLevel) {
		super();
		this.id = id;
		this.name = name;
		this.noOfLevel = noOfLevel;
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

	public Integer getNoOfLevel() {
		return noOfLevel;
	}

	public void setNoOfLevel(Integer noOfLevel) {
		this.noOfLevel = noOfLevel;
	}

	@Override
	public String toString() {
		return "UserGroupMaster [id=" + id + ", name=" + name + ", noOfLevel=" + noOfLevel + "]";
	}
	
	
	

}
