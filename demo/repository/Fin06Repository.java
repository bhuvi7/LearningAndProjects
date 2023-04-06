package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin06;
import com.example.demo.EntityModel.Fin07;

public interface Fin06Repository  extends CrudRepository<Fin06, Integer> {
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND month = :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndMonth(String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId, Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND month != :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND month != :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month != :month")
	List<Fin06> findByStatusAndFin01StatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId, Integer districtId,String month);
	
	//mine
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND  fin01_inv_status IS NULL AND month = :month")
	List<Fin06> findByFin06InvStatusIsNullAndMonth(String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_inv_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<Fin06> findByFin06InvStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<Fin06> findByFin06InvStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId, Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND  fin01_inv_status IS NULL AND month != :month")
	List<Fin06> findByFin06InvStatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND month != :month")
	List<Fin06> findByFin06InvStatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = 'APPROVED BY MOH' AND fin01_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month != :month")
	List<Fin06> findByFin06InvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId, Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndMonth(String status,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndMonthNot(String status,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusAndMonth(String status,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin06> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusAndMonthNot(String status,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin06 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin06> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	List<Fin06> findByDistrictIdAndClinicTypeIdAndStatusAndMonthAndYearAndFin01StatusIsNull(Integer districtId, Integer clinicTypeId, String status, String month, String year);
	
	
	@Query("FROM Fin06 ORDER BY id DESC")
	List<Fin06> findAllOrderByIdDesc();
	
	List<Fin06> findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	
	List<Fin06> findByDistrictIdAndClinicTypeIdAndMonthAndYearAndFin01InvNoIsNullAndFin01InvStatusIsNull(Integer districtId, Integer clinicTypeId, String month, String year);
	
	List<Fin06> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	
	List<Fin06> findByFin01RefNo(String fin01RefNo);
	List<Fin06> findByFin01InvNo(String fin01InvNo);
	List<Fin06> findByStatus(String status);
	List<Fin06> findByStatusNot(String status);
	List<Fin06> findByStatusOrStatus(String status1, String status2);
	Fin06 findByClinicIdAndMonthAndYear(Integer clinicId,String month,String year);

}
