package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.EntityModel.CimsHistoryFin02;

public interface CimsHistoryFin02Repository extends CrudRepository<CimsHistoryFin02, Integer> {
	
	/*temporary fix for updating status */
	List<CimsHistoryFin02> findByStateIdAndMonthAndFin08RefNoIsNull(Integer stateId, String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND month = :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndMonth(String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND stateId = :stateId AND month = :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndStateIdAndMonth(Integer stateId,String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND stateId = :stateId AND districtId = :districtId AND month = :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndStateIdAndDistrictIdAndMonth(Integer stateId, Integer districtId,String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND month != :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndMonthNot(String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND stateId = :stateId AND month != :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndStateIdAndMonthNot(Integer stateId,String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08bStatus IS NULL AND isFin08b = 'Y' AND stateId = :stateId AND districtId = :districtId AND month != :month")
	List<CimsHistoryFin02> findByFin08bStatusAndIsFin08bAndStateIdAndDistrictIdAndMonthNot(Integer stateId, Integer districtId,String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND month = :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndMonth(String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND stateId = :stateId AND month = :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndStateIdAndMonth(Integer stateId, String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND stateId = :stateId AND districtId = :districtId AND month = :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndStateIdAndDistrictIdAndMonth(Integer stateId, Integer districtId, String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND month != :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndMonthNot(String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND stateId = :stateId AND month != :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndStateIdAndMonthNot(Integer stateId, String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08cStatus IS NULL AND isFin08c = 'Y' AND stateId = :stateId AND districtId = :districtId AND month != :month")
	List<CimsHistoryFin02> findByFin08cStatusAndIsFin08cAndStateIdAndDistrictIdAndMonthNot(Integer stateId, Integer districtId, String month);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND month = :month AND year = :year" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNull(String fin08bStatus,String equivalentFin08c,String month,String year);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND month = :month AND year = :year AND stateId = :stateId" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateId(String fin08bStatus,String equivalentFin08c,String month,String year,Integer stateId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND month = :month AND year = :year AND stateId = :stateId AND districtId = :districtId" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictId(String fin08bStatus,String equivalentFin08c,String month,String year,Integer stateId,Integer districtId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND month = :month AND year = :year  AND districtId = :districtId" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictIdPendingListNotification(String fin08bStatus,String equivalentFin08c,String month,String year,Integer districtId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year)" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNull(String fin08bStatus,String equivalentFin08c,String month,String year);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year) AND stateId = :stateId" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNullAndStateId(String fin08bStatus,String equivalentFin08c,String month,String year,Integer stateId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08b_status = :fin08bStatus AND (equivalent_fin08c = :equivalentFin08c OR equivalent_fin08c IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year) AND stateId = :stateId AND districtId = :districtId" ) 
	List<CimsHistoryFin02> findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNullAndStateIdAndDistrictId(String fin08bStatus,String equivalentFin08c,String month,String year,Integer stateId,Integer districtId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND month = :month AND year = :year" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNull(String fin08cStatus,String equivalentFin08b,String month,String year);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND month = :month AND year = :year AND stateId = :stateId" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateId(String fin08cStatus,String equivalentFin08b,String month,String year,Integer stateId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND month = :month AND year = :year AND stateId = :stateId AND districtId = :districtId" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictId(String fin08cStatus,String equivalentFin08b,String month,String year,Integer stateId, Integer districtId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND month = :month AND year = :year  AND districtId = :districtId" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictIdPendingListNotification(String fin08cStatus,String equivalentFin08b,String month,String year,Integer districtId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year)" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNull(String fin08cStatus,String equivalentFin08b,String month,String year);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year) AND stateId = :stateId" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNullAndStateId(String fin08cStatus,String equivalentFin08b,String month,String year,Integer stateId);
	
	@Query("FROM CimsHistoryFin02 WHERE fin08Status IS NULL AND fin08c_status = :fin08cStatus AND (equivalent_fin08b = :equivalentFin08b OR equivalent_fin08b IS NULL) AND CONCAT(month,year) != CONCAT(:month,:year) AND stateId = :stateId AND districtId = :districtId" )
	List<CimsHistoryFin02> findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNullAndStateIdAndDistrictId(String fin08cStatus,String equivalentFin08b,String month,String year,Integer stateId, Integer districtId);
	
	
	List<CimsHistoryFin02> findByFin08bStatusIsNotNullOrFin08cStatusIsNotNullAndMonthAndYear(String month,String year);
	List<CimsHistoryFin02> findByFin08bStatusIsNull();
	List<CimsHistoryFin02> findByFin08cStatusIsNull();
	List<CimsHistoryFin02> findByFin08StatusIsNull();
	
	@Query("FROM CimsHistoryFin02 WHERE clinicId = :clinicId AND isFin08b = 'Y' AND month = :month AND year = :year ORDER BY assetName ASC")
	List<CimsHistoryFin02> findByClinicIdAndIsFin08bAndMonthAndYear(Integer clinicId,String month,String year);
	
	@Query("FROM CimsHistoryFin02 WHERE clinicId = :clinicId AND isFin08c = 'Y' AND month = :month AND year = :year ORDER BY assetName ASC")
	List<CimsHistoryFin02> findByClinicIdAndIsFin08cAndMonthAndYear(Integer clinicId,String month,String year);
	
	List<CimsHistoryFin02> findByClinicId(Integer clinincId);
	List<CimsHistoryFin02> findByFin08bRefNo(String fin08bRefNo);
	List<CimsHistoryFin02> findByFin08cRefNo(String fin08cRefNo);
	
	CimsHistoryFin02 findByFin08cStatusIsNullAndIsFin08cAndBeNumber(String isFin08c,String beNumber);
	
//	@Query("DELETE FROM CimsHistoryFin02 WHERE id = :id")
//	void deleteByBeNumber(Integer id);
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year  AND is_fin08b = 'Y' AND fin08b_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYear(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND is_Fin08b = 'Y' AND fin08b_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08bStatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND is_Fin08b = 'Y' AND fin08b_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08bStatus(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year  AND is_fin08c = 'Y' AND fin08c_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndIsFin08cCreated(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND is_Fin08c = 'Y' AND fin08c_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08cStatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND is_Fin08c = 'Y' AND fin08c_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08cStatus(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndIsFin08Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin08Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status='APPROVED BY MOH' AND fin03_status IS NULL AND "
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndIsFin03Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status='APPROVED BY MOH' AND fin03_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin03StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin08_status='APPROVED BY MOH' AND fin03_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin03Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin03_status='APPROVED BY MOH' AND fin02_inv_status IS NULL AND "
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndIsFin02Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin03_status='APPROVED BY MOH' AND fin02_inv_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin02StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  month = :month AND year = :year AND fin03_status='APPROVED BY MOH' AND fin02_inv_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByMonthAndYearAndFin02Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);


	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year)  AND is_fin08b = 'Y' AND fin08b_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYear(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND is_Fin08b = 'Y' AND fin08b_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08bStatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND is_Fin08b = 'Y' AND fin08b_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08bStatus(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year)  AND is_fin08c = 'Y' AND fin08c_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndIsFin08cCreated(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND is_Fin08c = 'Y' AND fin08c_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08cStatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND is_Fin08c = 'Y' AND fin08c_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08cStatus(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status IS NULL AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndIsFin08Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin08Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status='APPROVED BY MOH' AND fin03_status IS NULL AND "
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndIsFin03Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status='APPROVED BY MOH' AND fin03_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin03StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin08_status='APPROVED BY MOH' AND fin03_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin03Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	
	
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin03_status='APPROVED BY MOH' AND fin02_inv_status IS NULL AND "
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndIsFin02Created(String month, String year,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin03_status='APPROVED BY MOH' AND fin02_inv_status!=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin02StatusNot(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);
	@Query("select new com.example.demo.EntityModel.CimsHistoryFin02(i.stateId,i.districtId,i.clinicId,i.maintenanceCharges) from CimsHistoryFin02 i where  CONCAT(month,year) != CONCAT(:month,:year) AND fin03_status='APPROVED BY MOH' AND fin02_inv_status=:status AND"
			+ " ifnull(LOWER(i.stateId),'') LIKE LOWER(CONCAT('%',ifnull(:stateId,''), '%')) AND"
			+ " ifnull(LOWER(i.districtId),'') LIKE LOWER(CONCAT('%',ifnull(:districtId,''), '%'))")
	List<CimsHistoryFin02> findByNotMonthAndYearAndFin02Status(String month, String year,String status,@Param("stateId") Integer stateId,@Param("districtId") Integer districtId);


	
	
}
