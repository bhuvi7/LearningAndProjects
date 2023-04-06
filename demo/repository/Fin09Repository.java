package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.EntityModel.Fin09;
import com.example.demo.EntityModel.Invoice;

public interface Fin09Repository extends CrudRepository<Fin09, Integer> {
	
	@Query("FROM Fin09 WHERE status != :status ORDER BY id DESC")
	List<Fin09> findByStatusNot(String status);
	
	@Query("FROM Fin09 WHERE clinicTypeId = :clinicTypeId And status != :status ORDER BY id DESC")
	List<Fin09> findByStatusNotClinic(String status,Integer clinicTypeId);
	
	@Query("FROM Fin09 WHERE status = :status ORDER BY id DESC")
	List<Fin09> findByStatus(String status);
	@Query("FROM Fin09 WHERE districtId = :districtId AND clinicTypeId = :clinicTypeId AND status = 'APPROVED BY MOH' AND fin03_status IS NULL")
	Fin09 findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNull(Integer districtId, Integer clinicTypeId);
	Fin09 findByDistrictIdAndClinicTypeId(Integer districtId, Integer clinicTypeId);
	Fin09 findByFin03RefNo(String fin03RefNo);
	Fin09 findByCode(String code);
	
	@Query("FROM Fin09 WHERE code = :code ORDER BY id DESC")
	Fin09 findByCodes(String code);

	@Query("FROM Fin09 WHERE stateName =:stateName And status != :status ORDER BY id DESC")
	List<Fin09> findByStateNameAndStatusNot(String stateName,String status);
	
	@Query("FROM Fin09 WHERE circleCode =:circleCode And status != :status ORDER BY id DESC")
	List<Fin09> findByStateNameAndCircleCodeAndStatusNot(String circleCode,String status);
	
	@Query("FROM Fin09 WHERE districtName =:districtName And status != :status ORDER BY id DESC")
	List<Fin09> findByDistrictNameAndStatusNot(String districtName, String status);
	
	
	List<Fin09> findByQuaterAndYearAndStatusNot(String quater,String year,String status);
	List<Fin09> findByStateNameAndQuaterAndYearAndStatusNot(String stateName,String quater,String year,String status);
	List<Fin09> findByCircleCodeAndQuaterAndYearAndStatusNot(String circleCode,String quater,String year,String status);
	List<Fin09> findByDistrictNameAndQuaterAndYearAndStatusNot(String districtName,String quater,String year,String status);
	
	@Query("FROM Fin09 WHERE stateName =:stateName And districtName =:districtName And status != :status ORDER BY id DESC")
	List<Fin09> findByStateAndDistrictAndStatusNot(String stateName,String districtName,String status);
	
	
	@Query("FROM Fin09 WHERE stateName =:stateName And status != :status ORDER BY id DESC")
	List<Fin09> findByStateAndStatusNot(String stateName,String status);
	
	@Query("FROM Fin09 WHERE stateName =:stateName And districtName =:districtName And clinicTypeId=:clinicTypeId And status != :status ORDER BY id DESC")
	List<Fin09> findByStateAndDistrictAndClinicTypeAndStatusNot(String stateName,String districtName,Integer clinicTypeId,String status);
	

	@Query("select new com.example.demo.EntityModel.Fin09(i.id,i.code,i.clinicTypeId,i.stateName,i.districtName,i.clinicTypeCode,i.totalPenalty,i.status,i.approvalMonth,i.approvalQuater,i.approvalYear,i.remarks,i.totalResponseTimePenalty,i.totalRepairTimePenalty,i.totalUptimePenalty,i.totalScheduledMaintenancePenalty,i.totalLateDeliveryPenalty) from Fin09 i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND ifnull(LOWER(i.approvalQuater),'') LIKE LOWER(CONCAT('%',ifnull(:approvalQuater,''), '%')) "
			+ "AND ifnull(LOWER(i.approvalYear),'') LIKE LOWER(CONCAT('%',ifnull(:approvalYear,''), '%')) "
			+ "AND (status != 'PENALTY ADJUSTED') ORDER BY id DESC ")
	List<Fin09> findByFin09Status(@Param("stateName") String stateId,@Param("districtName") String districtName,@Param("clinicTypeId") Integer clinicTypeId,@Param("approvalQuater") String approvalQuater,
			@Param("approvalYear") String approvalYear);
	
	@Query("select new com.example.demo.EntityModel.Fin09(i.id,i.code,i.clinicTypeId,i.stateName,i.districtName,i.clinicTypeCode,i.totalPenalty,i.status,i.approvalMonth,i.approvalQuater,i.approvalYear,i.remarks,i.totalResponseTimePenalty,i.totalRepairTimePenalty,i.totalUptimePenalty,i.totalScheduledMaintenancePenalty,i.totalLateDeliveryPenalty) from Fin09 i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND ifnull(LOWER(i.approvalQuater),'') LIKE LOWER(CONCAT('%',ifnull(:approvalQuater,''), '%')) "
			+ "AND ifnull(LOWER(i.approvalYear),'') LIKE LOWER(CONCAT('%',ifnull(:approvalYear,''), '%'))  ORDER BY id DESC")
	List<Fin09> findByFin09(@Param("stateName") String stateId,@Param("districtName") String districtName,@Param("clinicTypeId") Integer clinicTypeId,@Param("approvalQuater") String approvalQuater,
			@Param("approvalYear") String approvalYear);
	
}

