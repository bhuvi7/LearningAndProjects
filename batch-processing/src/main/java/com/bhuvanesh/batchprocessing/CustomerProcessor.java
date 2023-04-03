package com.bhuvanesh.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return customer;
	}

}
