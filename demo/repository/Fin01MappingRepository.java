package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin01Mapping;

public interface Fin01MappingRepository extends CrudRepository<Fin01Mapping, Integer> {

	Fin01Mapping findByInvoiceRefNo(String invoiceRefNo);
	Fin01Mapping findByFin01RefNo(String fin01RefNo);

}
