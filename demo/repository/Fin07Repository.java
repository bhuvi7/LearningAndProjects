package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin07;

public interface Fin07Repository extends CrudRepository<Fin07, Integer> {
	
	@Query("FROM Fin07 ORDER BY id DESC")
	List<Fin07> findAllOrderByIdDesc();
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND month = :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndMonth(String month);
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId, Integer districtId,String month);
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND month != :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND m_state_id = :stateId AND month != :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status = 'APPROVED BY MOH' AND fin03a_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month != :month")
	List<Fin07> findByStatusAndFin03aStatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId, Integer districtId,String month);
	
	List<Fin07> findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	
	List<Fin07> findByDistrictIdAndClinicTypeIdAndStatusAndMonthAndYearAndFin03aStatusIsNull(Integer districtId, Integer clinicTypeId, String status, String month, String year);
	
	
	
	List<Fin07> findByFin03aRefNo(String fin03aRefNo);
	
	List<Fin07> findByFin02aInvNo(String fin02aInvNo);
	
	@Query("FROM Fin07 WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusAndMonth(String status,String month);
	
	@Query("FROM Fin07 WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin07 WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusAndMonthNot(String status,String month);
	
	@Query("FROM Fin07 WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndMonth(String status,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndMonthNot(String status,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin07 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin07> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	List<Fin07> findByStatusOrStatus(String status1, String status2);
	
	Fin07 findByClinicIdAndMonthAndYear(Integer clinicId,String month,String year);
	
	@Query("FROM Fin07 WHERE id = :id")
	Fin07 findByFin07(Integer id);

}
