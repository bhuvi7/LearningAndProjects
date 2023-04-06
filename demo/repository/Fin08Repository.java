package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin08;

public interface Fin08Repository extends CrudRepository<Fin08, Integer > {
	
	/*temporary fix for updating status */
	Fin08 findByClinicIdAndMonth(Integer clinicId, String month);
	
	@Query("FROM Fin08 WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndMonth(String status,String month);
	
	@Query("FROM Fin08 WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin08 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08 WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndMonthNot(String status,String month);
	
	@Query("FROM Fin08 WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin08 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08 WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndMonth(String status,String month);
	
	@Query("FROM Fin08 WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndStateIdAndMonth(String status,Integer stateId,String month);

	@Query("FROM Fin08 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08 WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndMonthNot(String status,String month);
	
	@Query("FROM Fin08 WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndStateIdAndMonthNot(String status,Integer stateId,String month);

	@Query("FROM Fin08 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	List<Fin08> findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNullAndMonthAndYear(Integer districtId, Integer clinicTypeId, String status,
			String month, String year);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndMonth(String month);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndStateIdAndMonth(Integer stateId, String month);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndStateIdAndMonthNot(Integer stateId, String month);
	
	@Query("FROM Fin08 WHERE status = 'APPROVED BY MOH' AND fin03_status IS NULL AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08> findByStatusAndFin03StatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId,Integer districtId, String month);
	
	List<Fin08> findByFin03RefNo(String fin03RefNo);

}
