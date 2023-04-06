package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.Fin07;

public interface CimsHistoryFin02aRepository extends CrudRepository<CimsHistoryFin02a, Integer> {
	
	// @Query("From CimsHistoryFin02a WHERE fin07_status IS NULL")
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndMonthAndYear(String month, String year);
	
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndDistrictIdAndClinicTypeIdAndMonthAndYear( Integer districtId,Integer ClinicTypeId ,String month, String year);
	
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndStateIdAndMonthAndYear(Integer stateId,String month, String year);
	
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndStateIdAndDistrictIdAndMonthAndYear(Integer stateId, Integer districtId,String month, String year);
	@Query("FROM CimsHistoryFin02a WHERE fin07_status IS NULL AND CONCAT(month,year) != CONCAT(:month,:year)")
     List<CimsHistoryFin02a> findByFin07StatusIsNullAndMonthNotAndYear(String month, String year);
	
	@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%'))")
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndStateIdAndMonthNotAndYear(Integer stateId,String month, String year);
	
	@Query("FROM CimsHistoryFin02a WHERE CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02a> findByFin07StatusIsNullAndStateIdAndDistrictIdAndMonthNotAndYear(Integer stateId, Integer districtId,String month, String year);
	
	@Query("FROM CimsHistoryFin02a WHERE districtId = :districtId AND clinicTypeId = :clinicTypeId AND fin07_status IS NULL AND year = :year AND month != :month")
	List<CimsHistoryFin02a> findByDistrictIdAndClinicTypeIdAndFin07StatusAndMonthAndYearAndFin03aStatusIsNull(Integer districtId,Integer clinicTypeId, String month, String year);
	
	List<CimsHistoryFin02a> findByMonthAndYear(String month, String year);
	
	List<CimsHistoryFin02a> findByFin07RefNo(String fin07RefNo);
	
	List<CimsHistoryFin02a> findByFin03aRefNo(String fin03aRefNo);
	
	@Query("FROM CimsHistoryFin02a WHERE clinicId = :clinicId AND month = :month AND year = :year ORDER BY batchNumber,installmentNumber  ASC")
	List<CimsHistoryFin02a> findByClinicIdAndMonthAndYear(Integer clinicId,String month,String year);
	
	List<CimsHistoryFin02a> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);

	 
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin07_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndIsFin07Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND  fin07_status!=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndIsFin07CreatedAndFin07StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin07_status=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndIsFin07CreatedAndFin07Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin07_status=:status AND fin03a_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin07Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin07_status=:fin07status AND fin03a_status !=:fin03astatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin07StatusAndFin03aStatusNot(String month, String year,String fin07status,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin07_status=:fin07status AND fin03a_status =:fin03astatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin07StatusAndFin03aStatus(String month, String year,String fin07status,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin03a_status=:fin03astatus AND fin02a_inv_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin03aStatus(String month, String year,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin03a_status=:fin03astatus AND fin02a_inv_status !=:fin02aInvstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin03aStatusAndFin02aStatusNot(String month, String year,String fin03astatus,String fin02aInvstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  month = :month AND year = :year AND fin03a_status=:fin03astatus AND fin02a_inv_status =:fin02aInvstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByMonthAndYearAndFin03aStatusAndFin02aStatus(String month, String year,String fin03astatus,String fin02aInvstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		
		
		
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndIsFin07Created(String month,String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND  fin07_status!=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndIsFin07CreatedAndFin07StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndIsFin07CreatedAndFin07Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status=:status AND fin03a_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin07Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status=:fin07status AND fin03a_status !=:fin03astatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin07StatusAndFin03aStatusNot(String month, String year,String fin07status,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE CONCAT(month,year) != CONCAT(:month,:year) AND fin07_status=:fin07status AND fin03a_status =:fin03astatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin07StatusAndFin03aStatus(String month, String year,String fin07status,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin03a_status=:fin03astatus AND fin02a_inv_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin03aStatus(String month, String year,String fin03astatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin03a_status=:fin03astatus AND fin02a_inv_status !=:fin02aInvstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin03aStatusAndFin02aStatusNot(String month, String year,String fin03astatus,String fin02aInvstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
		@Query("FROM CimsHistoryFin02a WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin03a_status=:fin03astatus AND fin02a_inv_status =:fin02aInvstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
		List<CimsHistoryFin02a> findByNotMonthAndYearAndFin03aStatusAndFin02aStatus(String month, String year,String fin03astatus,String fin02aInvstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);


}
