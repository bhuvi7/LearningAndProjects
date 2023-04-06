package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin08b;

public interface Fin08bRepository extends CrudRepository<Fin08b, Integer> {
	
	@Query("FROM Fin08b WHERE status != :status AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndMonth(String status,String month);
	
	@Query("FROM Fin08b WHERE status != :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndStateIdAndMonth(String status,Integer stateId,String month);
	
	@Query("FROM Fin08b WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08b WHERE status != :status AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndMonthNot(String status,String month);
	
	@Query("FROM Fin08b WHERE status != :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndStateIdAndMonthNot(String status,Integer stateId,String month);
	
	@Query("FROM Fin08b WHERE status != :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusNotAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08b WHERE status = :status AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndMonth(String status,String month);
	
	@Query("FROM Fin08b WHERE status = :status AND stateId = :stateId AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndStateIdAndMonth(String status,Integer stateId,String month);

	@Query("FROM Fin08b WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month = :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndStateIdAndDistrictIdAndMonth(String status,Integer stateId,Integer districtId,String month);
	
	@Query("FROM Fin08b WHERE status = :status AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndMonthNot(String status,String month);
	
	@Query("FROM Fin08b WHERE status = :status AND stateId = :stateId AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndStateIdAndMonthNot(String status,Integer stateId,String month);

	@Query("FROM Fin08b WHERE status = :status AND stateId = :stateId AND districtId = :districtId AND month != :month ORDER BY id DESC")
	List<Fin08b> findByStatusAndStateIdAndDistrictIdAndMonthNot(String status,Integer stateId,Integer districtId,String month);
	
	@Query("From Fin08b WHERE status = 'APPROVED BY MOH' AND t_fin08_ref_no IS NULL")
	List<Fin08b> findByStatusFin08RefNoIsNull();
	
	@Query("FROM Fin08b WHERE id = :id")
	Fin08b findByFin08b(Integer id);
	
	Fin08b findByClinicId(Integer clinicId);
	Fin08b findByClinicIdAndMonthAndYear(Integer clinicId,String month,String year);
	Fin08b findByFin08RefNo(String fin08RefNo);
}
