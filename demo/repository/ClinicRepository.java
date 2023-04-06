package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Clinic;

public interface ClinicRepository extends CrudRepository<Clinic, Integer> {
	
	@Query("FROM Clinic WHERE id = :id")
	Clinic findByClinicId(Integer id);
	Clinic findByClinicCode(String clinicCode);
	List<Clinic> findByDistrictId(Integer districtId);
	List<Clinic> findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	@Query("select count(*) from Clinic where districtId=:districtId")
	Integer findClinicCountByDistrictId(Integer districtId);

}
