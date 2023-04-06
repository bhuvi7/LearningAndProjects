package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.CimsHistoryFin08b;

public interface CimsHistoryFin08bRepository extends CrudRepository<CimsHistoryFin08b, Integer> {
	
	List<CimsHistoryFin08b> findByFin08bStatusIsNull();
	List<CimsHistoryFin08b> findByClinicId(Integer clinincId);
	List<CimsHistoryFin08b> findByFin08bRefNo(String fin08bRefNo);

}
