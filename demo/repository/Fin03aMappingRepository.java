package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin03aMapping;

public interface Fin03aMappingRepository extends CrudRepository<Fin03aMapping, Integer> {

	Fin03aMapping findByInvoiceRefNo(String invoiceRefNo);
	Fin03aMapping findByFin03aRefNo(String fin03aRefNo);
}
