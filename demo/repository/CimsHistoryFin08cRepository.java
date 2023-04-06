package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.CimsHistoryFin08c;

public interface CimsHistoryFin08cRepository extends CrudRepository<CimsHistoryFin08c, Integer> {
	
	List<CimsHistoryFin08c> findByFin08cStatusIsNull();
	List<CimsHistoryFin08c> findByClinicId(Integer clinincId);
	List<CimsHistoryFin08c> findByFin08cRefNo(String fin08cRefNo);

}
