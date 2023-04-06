package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.District;

public interface DistrictRepository extends CrudRepository<District, Integer> {
	
	@Query("FROM District WHERE id = :id")
	District findByDistrictId(Integer id);

}
