package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.CimsHistoryFin02b;

public interface CimsHistoryFin02bRepository extends CrudRepository<CimsHistoryFin02b, Integer> {
	
	CimsHistoryFin02b findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	List<CimsHistoryFin02b> findByFin02bInvStatusIsNullAndStateId(Integer stateId);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND month = :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndMonth(String month);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND m_state_id = :stateId AND month = :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month = :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndStateIdAndDistrictIdAndMonth(Integer stateId,Integer districtId,String month);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND month != :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndMonthNot(String month);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND m_state_id = :stateId AND month != :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM CimsHistoryFin02b WHERE fin02b_inv_status IS NULL AND m_state_id = :stateId AND m_district_id = :districtId AND month != :month")
	List<CimsHistoryFin02b>findByFin02bInvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(Integer stateId,Integer districtId,String month);
	CimsHistoryFin02b findByFin02bInvNo(String fin02bInvNo);
	
	List<CimsHistoryFin02b> findByFin02bInvStatusIsNullAndMonthAndYear( String month, String year);
	
	
	List<CimsHistoryFin02b> findByFin02bInvStatusNotAndMonthAndYear(String status, String month, String year);
	List<CimsHistoryFin02b> findByFin02bInvStatusAndMonthAndYear(String status, String month, String year);
	List<CimsHistoryFin02b> findByFin02bInvStatusIsNullAndMonthAndYearAndStateId( String month, String year,Integer stateId);
	List<CimsHistoryFin02b> findByFin02bInvStatusNotAndMonthAndYearAndStateId(String status, String month, String year,Integer stateId);
	List<CimsHistoryFin02b> findByFin02bInvStatusAndMonthAndYearAndStateId(String status, String month, String year,Integer stateId);

	

	List<CimsHistoryFin02b> findByFin02bInvStatusIsNullAndMonthNotAndYear( String month, String year);
	
	
	List<CimsHistoryFin02b> findByFin02bInvStatusNotAndMonthNotAndYear(String status, String month, String year);
	List<CimsHistoryFin02b> findByFin02bInvStatusAndMonthNotAndYear(String status, String month, String year);
	List<CimsHistoryFin02b> findByFin02bInvStatusIsNullAndMonthNotAndYearAndStateId( String month, String year,Integer stateId);
	List<CimsHistoryFin02b> findByFin02bInvStatusNotAndMonthNotAndYearAndStateId(String status, String month, String year,Integer stateId);
	List<CimsHistoryFin02b> findByFin02bInvStatusAndMonthNotAndYearAndStateId(String status, String month, String year,Integer stateId);

}
