package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.CimsHistoryFin02a;

public interface CimsHistoryFin01Repository extends CrudRepository<CimsHistoryFin01, Integer> {
	
	@Query("FROM CimsHistoryFin01 WHERE m_clinic_id = :clinicId AND month = :month AND year = :year AND (is_fin06_created IS NULL OR is_fin06_created = :isFin06Created)")
	List<CimsHistoryFin01> findByClinicIdAndIsFin06Created(Integer clinicId,String month, String year,String isFin06Created);
	List<CimsHistoryFin01> findByMonthAndYear(String month, String year);
	List<CimsHistoryFin01> findByFin06RefNo(String fin06RefNo);
	List<CimsHistoryFin01> findByFin01RefNo(String fin01RefNo);
	List<CimsHistoryFin01> findByDistrictIdAndClinicTypeIdAndMonthAndYear(Integer districtId, Integer clinicTypeId, String month, String year);
	List<CimsHistoryFin01> findByStateId(Integer stateId);
	
	List<CimsHistoryFin01> findByFin06StatusIsNullAndMonthAndYear(String month, String year);
	
	List<CimsHistoryFin01> findByFin06StatusIsNullAndStateIdAndMonthAndYear(Integer stateId,String month, String year);
	
	List<CimsHistoryFin01> findByFin06StatusIsNullAndStateIdAndDistrictIdAndMonthAndYear(Integer stateId, Integer districtId,String month, String year);

	@Query("FROM CimsHistoryFin01 WHERE fin06_status IS NULL AND CONCAT(month,year) != CONCAT(:month,:year)")
	List<CimsHistoryFin01> findByFin06StatusIsNullAndMonthNotAndYear(String month, String year); 
	
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin06_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%'))")
	List<CimsHistoryFin01> findByFin06StatusIsNullAndStateIdAndMonthNotAndYear(Integer stateId,String month, String year);
	
	@Query("FROM CimsHistoryFin01 WHERE CONCAT(month,year) != CONCAT(:month,:year) AND fin06_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByFin06StatusIsNullAndStateIdAndDistrictIdAndMonthNotAndYear(Integer stateId, Integer districtId,String month, String year);
	
	
	
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND (is_fin06_created IS NULL OR is_fin06_created = :isFin06Created) AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndIsFin06Created(String month, String year,String isFin06Created,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND is_fin06_created = :isFin06Created AND fin06_status!=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndIsFin06CreatedAndFin06StatusNot(String month, String year,String isFin06Created,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND is_fin06_created = :isFin06Created AND fin06_status=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndIsFin06CreatedAndFin06Status(String month, String year,String isFin06Created,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin06_status=:status AND fin01_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin06Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin06_status=:fin06status AND fin01_status !=:fin01status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin06StatusAndFin01StatusNot(String month, String year,String fin06status,String fin01status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin06_status=:fin06status AND fin01_status =:fin01status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin06StatusAndFin01Status(String month, String year,String fin06status,String fin01status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin01_status=:status AND fin01_inv_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin01Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin01_status=:fin01status AND fin01_inv_status !=:fin01Invstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin01StatusAndFin01InvStatusNot(String month, String year,String fin01status,String fin01Invstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  month = :month AND year = :year AND fin01_status=:fin01status AND fin01_inv_status =:fin01Invstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByMonthAndYearAndFin01StatusAndFin01InvStatus(String month, String year,String fin01status,String fin01Invstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);

	

	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND (is_fin06_created IS NULL OR is_fin06_created = :isFin06Created) AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndIsFin06Created(String month, String year,String isFin06Created,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND is_fin06_created = :isFin06Created AND fin06_status!=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndIsFin06CreatedAndFin06StatusNot(String month, String year,String isFin06Created,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND is_fin06_created = :isFin06Created AND fin06_status=:status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndIsFin06CreatedAndFin06Status(String month, String year,String isFin06Created,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin06_status=:status AND fin01_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin06Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin06_status=:fin06status AND fin01_status !=:fin01status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin06StatusAndFin01StatusNot(String month, String year,String fin06status,String fin01status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin06_status=:fin06status AND fin01_status =:fin01status AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin06StatusAndFin01Status(String month, String year,String fin06status,String fin01status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin01_status=:status AND fin01_inv_status IS NULL AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin01Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE CONCAT(month,year) != CONCAT(:month,:year) AND fin01_status=:fin01status AND fin01_inv_status !=:fin01Invstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin01StatusAndFin01InvStatusNot(String month, String year,String fin01status,String fin01Invstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("FROM CimsHistoryFin01 WHERE  CONCAT(month,year) != CONCAT(:month,:year) AND fin01_status=:fin01status AND fin01_inv_status =:fin01Invstatus AND ifnull(LOWER(stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND ifnull(LOWER(districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin01> findByNotMonthAndYearAndFin01StatusAndFin01InvStatus(String month, String year,String fin01status,String fin01Invstatus,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);

}
