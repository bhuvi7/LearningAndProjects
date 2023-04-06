package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin03a;

public interface Fin03aRepository extends CrudRepository<Fin03a, Integer> {
	
	@Query("FROM Fin03a ORDER BY id DESC")
	List<Fin03a> findAllOrderByIdDesc();
	
	@Query("FROM Fin03a WHERE  districtId = :districtId AND clinicTypeId = :clinicTypeId AND month = :month AND year = :year  ORDER BY id DESC")
	List<Fin03a> findByDistrictIdClinicTypeIdMonthYear(Integer districtId,Integer clinicTypeId,String month ,String year);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND month = :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndMonth(String month);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND month != :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND m_state_id = :stateId AND month != :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM Fin03a WHERE status = 'APPROVED BY MOH' AND fin02a_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month != :month")
	List<Fin03a> findByFin02aInvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId,Integer districtId,String month);
	
	List<Fin03a> findByFin02aInvNo(String fin02aInvNo);
	
	List<Fin03a> findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	
	List<Fin03a> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	
	Fin03a findByCode(String code);
	
	List<Fin03a> findByStatusOrStatus(String status1, String status2);
	
	@Query("FROM Fin03a WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndMonth(String status, String month);
	
	@Query("FROM Fin03a WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndStateIdAndMonth(String status,Integer stateId, String month);
	
	@Query("FROM Fin03a WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03a WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndMonthNot(String status, String month);
	
	@Query("FROM Fin03a WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndStateIdAndMonthNot(String status,Integer stateId, String month);
	
	@Query("FROM Fin03a WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndMonth(String status, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndStateIdAndMonth(String status,Integer stateId, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndMonthNot(String status, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId, String month);
	
	@Query("FROM Fin03a WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin03a> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03a WHERE id = :id")
	Fin03a findByFin03a(Integer id);

}
