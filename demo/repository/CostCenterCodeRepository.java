package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.CostCenterCode;

public interface CostCenterCodeRepository extends CrudRepository<CostCenterCode, Integer> {
	
	CostCenterCode findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);

}
