package com.bhuvanesh.batchprocessing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Customer_Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private float expense;
	@Column(name="income")
	private float income;
	@Column(name="Bonus")
	private float Bonus;
	@Column(name="District")
	private String district;
	@Column(name="Order_list")
	private String orderList;
	@Column(name="Rate_of_interest")
	private float rateOfInterest;

}
