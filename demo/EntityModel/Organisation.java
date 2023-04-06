package com.example.demo.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_organisation")
public class Organisation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String organisationName;
	
	public Organisation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organisation(Integer id, String organisationName) {
		super();
		this.id = id;
		this.organisationName = organisationName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	@Override
	public String toString() {
		return "Organisation [id=" + id + ", organisationName=" + organisationName + "]";
	}
	
	
	

}
