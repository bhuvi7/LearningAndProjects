package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin03;
import com.example.demo.EntityModel.Fin03a;

public interface Fin03Repository extends CrudRepository<Fin03, Integer> {

	@Query("FROM Fin03 WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndMonth(String status, String month);
	
	@Query("FROM Fin03 WHERE  districtId = :districtId AND clinicTypeId = :clinicTypeId AND month = :month AND year = :year  ORDER BY id DESC")
	List<Fin03> findByDistrictIdClinicTypeIdMonthYearForDuplication(Integer districtId,Integer clinicTypeId,String month ,String year);
	
	@Query("FROM Fin03 WHERE  districtId = :districtId AND clinicTypeId = :clinicTypeId AND month = :month AND year = :year  ORDER BY id DESC")
	List<Fin03> findByDistrictIdClinicTypeIdMonthYearDuplication(Integer districtId,Integer clinicTypeId,String month ,String year);
	
	@Query("FROM Fin03 WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndStateIdAndMonth(String status,Integer stateId, String month);
	
	@Query("FROM Fin03 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03 WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndMonthNot(String status, String month);
	
	@Query("FROM Fin03 WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId, String month);
	
	@Query("FROM Fin03 WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03 WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusAndMonth(String status, String month);
	
	@Query("FROM Fin03 WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusAndStateIdAndMonth(String status,Integer stateId, String month);

	@Query("FROM Fin03 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin03> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03 WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusAndMonthNot(String status, String month);
	
	@Query("FROM Fin03 WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusAndStateIdAndMonthNot(String status,Integer stateId, String month);

	@Query("FROM Fin03 WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin03> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Fin03 WHERE id = :id")
	Fin03 findByFin03(Integer id);
	
	List<Fin03> findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	
	List<Fin03> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	
	List<Fin03> findByFin02InvNo(String fin02InvNo);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND month = :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndMonth(String month);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND stateId = :stateId AND month = :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND month != :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndMonthNot(String month);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND stateId = :stateId AND month != :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM Fin03 WHERE status = 'APPROVED BY MOH' AND fin02InvStatus IS NULL AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC" )
	List<Fin03> findByFin02InvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId,Integer districtId,String month);
	
}
