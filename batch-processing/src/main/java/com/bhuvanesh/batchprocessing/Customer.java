package com.bhuvanesh.batchprocessing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer_Info")
public class Customer {
	
	@Id
	@Column(name="Customer_id")
	private int id;
	@Column(name="First_name")
	private String firstName;
	@Column(name="Last_name")
	private String lasttName;
	@Column(name="Quantity")
	private int quantity;
	@Column(name="Expense")
	private int expense;
	@Column(name="income")
	private int income;
	@Column(name="Bonus")
	private int Bonus;
	@Column(name="District")
	private String district;
	@Column(name="Order_list")
	private String orderList;
	@Column(name="Rate_of_interest")
	private float rateOfInterest;

}
