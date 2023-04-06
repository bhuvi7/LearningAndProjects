package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin08c;

public interface Fin08cRepository extends CrudRepository<Fin08c, Integer> {

	@Query("FROM Fin08c WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndMonth(String status,String month);
	
	@Query("FROM Fin08c WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin08c WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08c WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndMonthNot(String status,String month);
	
	@Query("FROM Fin08c WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin08c WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08c WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndMonth(String status,String month);
	
	@Query("FROM Fin08c WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndStateIdAndMonth(String status,Integer stateId,String month);

	@Query("FROM Fin08c WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08c WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndMonthNot(String status,String month);
	
	@Query("FROM Fin08c WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndStateIdAndMonthNot(String status,Integer stateId,String month);

	@Query("FROM Fin08c WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08c> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("From Fin08c WHERE status = 'APPROVED BY MOH' AND t_fin08_ref_no IS NULL")
	List<Fin08c> findByStatusFin08RefNoIsNull();
	
	@Query("FROM Fin08c WHERE id = :id")
	Fin08c findByFin08c(Integer id);
	
	Fin08c findByClinicId(Integer clinicId);
	Fin08c findByClinicIdAndMonthAndYear(Integer clinicId, String month, String year);
	Fin08c findByFin08RefNo(String fin08RefNo);
}
