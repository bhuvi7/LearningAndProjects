package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.InvoiceType;

public interface InvoiceTypeRepository extends CrudRepository<InvoiceType, Integer> {
	
	@Query("FROM InvoiceType WHERE id = :id")
	InvoiceType findByInvoiceType(Integer id);

}
