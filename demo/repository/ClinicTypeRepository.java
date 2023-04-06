package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.ClinicType;

public interface ClinicTypeRepository extends CrudRepository<ClinicType, Integer> {
	
	@Query("FROM ClinicType WHERE id = :id")
	ClinicType findByClinicTypeCode(Integer id);

}
