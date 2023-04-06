package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin01;
import com.example.demo.EntityModel.Fin03a;

public interface Fin01Repository  extends CrudRepository<Fin01, Integer> {
	@Query("FROM Fin01 WHERE status = 'APPROVED BY MOH' AND fin01_inv_status IS NULL AND month = :month")
	List<Fin01> findByFin02aInvStatusIsNullAndMonth(String month);
	
	@Query("FROM Fin01 WHERE status = 'APPROVED BY MOH' AND fin01_inv_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<Fin01> findByFin02aInvStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin01 WHERE status = 'APPROVED BY MOH' AND fin01_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<Fin01> findByFin02aInvStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin01 WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusAndMonth(String status, String month);
	
	@Query("FROM Fin01 WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusAndStateIdAndMonth(String status,Integer stateId, String month);
	
	@Query("FROM Fin01 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin01 WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusAndMonthNot(String status, String month);
	
	@Query("FROM Fin01 WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusAndStateIdAndMonthNot(String status,Integer stateId, String month);
	
	@Query("FROM Fin01 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndMonth(String status, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndStateIdAndMonth(String status,Integer stateId, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndMonthNot(String status, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId, String month);
	
	@Query("FROM Fin01 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin01> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin01 ORDER BY id DESC")
	List<Fin01> findAllOrderByIdDesc();
	
	@Query("FROM Fin01 WHERE districtId = :districtId AND clinicTypeId = :clinicTypeId AND month = :month AND year =:year  ")
	List<Fin01> findByDistrictIdAndClinicTypeIdAndMonthAndYears(Integer districtId, Integer clinicTypeId, String month, String year);

	Fin01 findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	Fin01 findByCode(String code);
	List<Fin01> findByStatusOrStatus(String status1, String status2);
	List<Fin01> findByStatus(String status);
	List<Fin01> findByStatusNot(String status);

}
