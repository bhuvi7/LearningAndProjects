package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin07;
import com.example.demo.EntityModel.Fin10b;

public interface Fin10bRepository extends CrudRepository<Fin10b, Integer> {
	@Query("FROM Fin10b ORDER BY id DESC")
	List<Fin10b> findAllOrderByIdDesc();
	List<Fin10b> findByStatusAndFin04InvStatusIsNull(String status);
	
//	@Query("FROM Fin10b WHERE districtId = :districtId AND clinicTypeId = :clinicTypeId AND month = :month AND year = :year ORDER BY id DESC")
	List<Fin10b> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId,Integer clinicTypeId, String month, String year);
	
	@Query ("FROM Fin10b where fin04InvNo = :fin04InvNo")
	List<Fin10b> findByFin04InvNo(String fin04InvNo);
	
	@Query("FROM Fin10b where clinicTypeId = :clinicTypeId AND stateId = :stateId ")
	List<Fin10b> findByClinicTypeIdAndStateId(Integer clinicTypeId, Integer stateId);
	
	@Query("FROM Fin10b WHERE status != :status  ORDER BY id DESC")
	List<Fin10b> findByStatusNotAndMonth(String status);
	
	@Query("FROM Fin10b WHERE status != :status AND stateId = :stateId  ORDER BY id DESC")
	List<Fin10b> findByStatusNotAndStateIdAndMonth(String status,Integer stateId);
	
	@Query("FROM Fin10b WHERE status != :status AND stateId = :stateId AND districtId = :districtId  ORDER BY id DESC")
	List<Fin10b> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId);
	
	@Query("FROM Fin10b WHERE status = :status  ORDER BY id DESC")
	List<Fin10b> findByStatusAndMonth(String status);
	
	@Query("FROM Fin10b WHERE status = :status AND stateId = :stateId  ORDER BY id DESC")
	List<Fin10b> findByStatusAndStateIdAndMonth(String status,Integer stateId);
	
	@Query("FROM Fin10b WHERE status = :status AND stateId = :stateId AND districtId = :districtId  ORDER BY id DESC")
	List<Fin10b> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId);
	
;
}
