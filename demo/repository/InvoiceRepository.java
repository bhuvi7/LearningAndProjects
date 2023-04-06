package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.EntityModel.Invoice;


public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
	
	
	List<Invoice> findByYear(String year);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdOrderByIdDesc(Integer invoiceTypeId);
	
	@Query("FROM Invoice WHERE id = :id ")
Invoice findByIdForDate(Integer id);
	
	List<Invoice> findByInvoiceTypeIdAndStatusNot(Integer invoiceTypeId, String status);
	
	List<Invoice> findByInvoiceTypeIdAndStatus(Integer invoiceTypeId, String status);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndMonth(Integer invoiceTypeId, String status, String month);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !=''  ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndMonthForFin04(Integer invoiceTypeId, String status);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndMonth(Integer invoiceTypeId, String status, String month);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotFin04(Integer invoiceTypeId, String status);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndMonth(Integer invoiceTypeId, String status,Integer stateId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndMonthForFin04(Integer invoiceTypeId, String status,Integer stateId);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndMonth(Integer invoiceTypeId, String status,Integer stateId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdFin04(Integer invoiceTypeId, String status,Integer stateId);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonth(Integer invoiceTypeId, String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonthForFin04(Integer invoiceTypeId, String status,Integer stateId,Integer districtId);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' AND month = :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdAndMonth(Integer invoiceTypeId, String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !=''ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdFin04(Integer invoiceTypeId, String status,Integer stateId,Integer districtId);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndMonthNot(Integer invoiceTypeId, String status, String month);
	
	@Query("FROM Invoice WHERE invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndMonthNot(Integer invoiceTypeId, String status, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndMonthNot(Integer invoiceTypeId, String status,Integer stateId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndMonthNot(Integer invoiceTypeId, String status,Integer stateId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdAndMonthNot(Integer invoiceTypeId, String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' AND month != :month ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdAndMonthNot(Integer invoiceTypeId, String status,Integer stateId,Integer districtId, String month);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictId(Integer invoiceTypeId, String status, Integer stateId,Integer districtId);

	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status != :status AND status IS NOT NULL  AND status !='' ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusNotAndStateIdAndDistrictIdOlder(Integer invoiceTypeId, String status, Integer stateId,Integer districtId);
	
	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !=''  ORDER BY id DESC")
	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndDistrictId(Integer invoiceTypeId, String status, Integer stateId,Integer districtId);
	
 	@Query("FROM Invoice WHERE stateId =:stateId AND districtId = :districtId AND invoiceTypeId = :invoiceTypeId AND status = :status AND status IS NOT NULL AND status !=''  ORDER BY id DESC")
 	List<Invoice> findByInvoiceTypeIdAndStatusAndStateIdAndDistrictIdOlder(Integer invoiceTypeId, String status, Integer stateId,Integer districtId);

	List<Invoice> findByInvoiceTypeIdAndStatusOrStatus(Integer invoiceTypeId, String status1, String status2);
	
	@Query("FROM Invoice WHERE paymentStatus = :paymentStatus1 OR paymentStatus = :paymentStatus2 ORDER BY id DESC")
	List<Invoice> findByPaymentStatusOrPaymentStatus(String paymentStatus1, String paymentStatus2);
	
	List<Invoice> findByInvoiceTypeIdAndDistrictIdAndClinicTypeIdAndMonthAndYear(Integer invoiceTypeId, Integer districtId, Integer clinicTypeId, String month, String year);
	
	List<Invoice> findByStateId(Integer stateId);
	List<Invoice> findByStateName(String stateName);
	List<Invoice> findByDistrictName(String districtName);
	List<Invoice> findByStateNameAndYear(String stateName, String year);
	List<Invoice> findByDistrictNameAndYear(String districtName, String year);
	List<Invoice> findByStateNameAndQuaterAndYear(String stateName,String quater,String year);
	List<Invoice> findByDistrictNameAndQuaterAndYear(String districtName,String quater,String year);
	List<Invoice> findByQuater(String quater);
	List<Invoice> findByQuaterAndYear(String quater,String year);
//	mine
	Invoice findByInvoiceNo(String invoiceNo);
	
//	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
//			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
//			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
//			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
//			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
//			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%'))"
//			+ "AND (month < :month OR year <= :approvalYear)"
//			+ "AND (paymentStatus = 'PAYMENT-PENDING' OR paymentStatus = 'PAYMENT-DRAFT') ORDER BY id DESC ")
//	List<Invoice> findByQuaterAndYearAndStatus(String stateName,String districtName,String invoiceTypeName,Integer clinicTypeId,String month,String approvalYear);
	
//	@Query("FROM Invoice WHERE invoiceNo = :invoiceNo")
//	List<Invoice> findByInvoiceNos(String invoiceNo);
	
	List<Invoice> findByCircleCode(String circleCode);
	List<Invoice> findByCircleCodeAndYear(String circleCode, String year);
	
	List<Invoice> findByCircleCodeAndQuaterAndYear(String circleCode,String quater,String year);
	
	
//	@Query("select new com.example.demo.EntityModel.Invoice(i.id, i.code, i.invoiceNo, i.invoiceTypeId, i.invoiceTypeName,"
//			+ "	i.stateId,i.stateName,i.stateCode,i.districtId,i.districtName,"
//			+ " i.districtCode,i.clinicTypeId,i.clinicTypeCode,i.circleCode,i.invoiceDate,i.invoiceBase) from Invoice i where i.id = :id")
//	Invoice findByIdForRA ( @Param("id") Integer id);

	@Query("select new com.example.demo.EntityModel.Invoice(i.invoiceNo,"
			+ "	i.stateName,i.districtName,"
			+ " i.invoiceDate,i.invoiceBaseValue,i.sst,i.retentionAmount,i.totalInvoiceValue,i.outstandingAmount,i.paymentReceived) from Invoice i where i.id = :id")
	Invoice findByIdForRA ( @Param("id") Integer id);
	


	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry)"
			+ " from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.circleCode),'') LIKE LOWER(CONCAT('%',ifnull(:circleCode,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND (i.invoiceTypeName IN :invoiceTypeName)"
//			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%'))"
			+ " ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
	List<Invoice> findByInvoice(@Param("stateName") String stateId,@Param("circleCode") String circleCode,@Param("districtName") String districtName,@Param("invoiceTypeName") List<String> invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId);
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.circleCode),'') LIKE LOWER(CONCAT('%',ifnull(:circleCode,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%')) ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
	List<Invoice> findByInvoiceForFin09(@Param("stateName") String stateId,@Param("circleCode") String circleCode,@Param("districtName") String districtName,@Param("invoiceTypeName") String invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId);
	
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
            + "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
            + "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
            + "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
            + "AND ifnull(LOWER(i.circleCode),'') LIKE LOWER(CONCAT('%',ifnull(:circleCode,''), '%')) "
            + "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
            + "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%')) "
            + "AND ifnull(LOWER(i.year),'')LIKE LOWER(CONCAT('%',ifnull(:year,''), '%')) ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
    List<Invoice> findByInvoiceYear(@Param("stateName") String stateId,@Param("circleCode") String circleCode,@Param("districtName") String districtName,@Param("invoiceTypeName") String invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId,@Param("year") String year);
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.circleCode),'') LIKE LOWER(CONCAT('%',ifnull(:circleCode,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
		//	+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%'))"
			+ "AND (i.invoiceTypeName IN :invoiceTypeName)"
//			+ "AND  (ifnull(:month,'')='' OR i.month=ifnull(:month,''))"
			+ "AND  (i.month IN :month)"
			//+" AND (ifnull(:year,'')='' OR i.year=ifnull(:year,'')) ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
	+" AND (i.year IN :year) ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
	
//			+ "AND ifnull(LOWER(i.month),'') LIKE LOWER(CONCAT('%',ifnull(:month,''), '%')) "
//			+ "AND ifnull(LOWER(i.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''), '%')) "
	List<Invoice> findByInvoiceReport(@Param("stateName") String stateId,@Param("circleCode") String circleCode,@Param("districtName") String districtName,
			@Param("invoiceTypeName") List<String> invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId,@Param("month") List<String> month,@Param("year") List<String> year);
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%'))"
			+ "AND (paymentStatus = 'PAYMENT-PENDING' OR paymentStatus = 'PAYMENT-DRAFT') ORDER BY id DESC ")
	List<Invoice> findByInvoicePaymentStatus(@Param("stateName") String stateId,@Param("districtName") String districtName,@Param("invoiceTypeName") String invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId);
	
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.retentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst,i.invoiceBaseValue,i.netRetentionAmount,i.debitNoteEntry) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
			+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
			+ "AND ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''), '%')) "
			+ "AND ifnull(LOWER(i.invoiceNo),'') LIKE LOWER(CONCAT('%',ifnull(:invoiceNo,''), '%')) "
			+ "AND ifnull(DATE(i.invoiceDate),'') BETWEEN ifnull(:invoiceDateFrom,'') AND ifnull(:invoiceDateTo,'') "
			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%'))  ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC ")
	List<Invoice> findByInvoiceNo(@Param("stateName") String stateId,@Param("districtName") String districtName,@Param("invoiceTypeName") String invoiceTypeName,@Param("clinicTypeId") Integer clinicTypeId
			,@Param("invoiceNo") String invoiceNo,@Param("paymentRefNo") String paymentRefNo,@Param("invoiceDateFrom") String invoiceDateFrom,@Param("invoiceDateTo") String invoiceDateTo
			);
	
//	@Query("select new com.example.demo.EntityModel.Invoice(i.invoiceTypeId,i.invoiceTypeName,i.clinicTypeId,i.clinicTypeCode,i.stateId,i.stateName,i.districtId,i.districtName,SUM(i.totalInvoiceValue) )FROM Invoice i WHERE "	
//			+ " ifnull(DATE(i.invoiceDate),'') BETWEEN ifnull(:invoiceDateFrom,'') AND ifnull(:invoiceDateTo,'') "	
//			+ "   GROUP BY invoiceTypeId,clinicTypeId,stateId,districtId")	
//	List<Invoice> findByConsolidatedReport(@Param("invoiceDateFrom") String invoiceDateFrom,@Param("invoiceDateTo") String invoiceDateTo);
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.invoiceTypeId,i.invoiceTypeName,i.clinicTypeId,i.clinicTypeCode,i.stateId,i.stateName,i.districtId,i.districtName,SUM(i.totalInvoiceValue) )FROM Invoice i WHERE ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%'))"	
			+ "AND (i.invoiceTypeName IN :invoiceTypeName)"
//			+ "AND ifnull(LOWER(i.month),'') LIKE LOWER(CONCAT('%',ifnull(:month,''), '%')) "
			+ "AND  (i.month IN :month)"
			+" AND (i.year IN :year)"
//			+ "AND ifnull(LOWER(i.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''), '%')) " 
			+ "   GROUP BY invoiceTypeId,clinicTypeId,stateId,districtId")	
	List<Invoice> findByConsolidatedReport(@Param("stateName") String stateName,@Param("invoiceTypeName") List<String>invoiceTypeName,@Param("month") List<String> month,@Param("year") List<String> year);
	
	@Query("select new com.example.demo.EntityModel.Invoice(i.id,i.invoiceNo,i.invoiceTypeId,i.invoiceTypeName,i.stateId,i.stateName,"
			+ "i.districtId,i.districtName,i.clinicTypeId,i.clinicTypeCode,i.month,i.year,i.invoiceDate,i.netRetentionAmount,i.totalInvoiceValue,"
			+ "i.outstandingAmount,i.status,i.paymentStatus,i.paymentReceived,i.paymentRefNo,i.totalInvoiceValueWoRetention,i.circleCode,i.sst) from Invoice i where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "+
	
			"AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) " + 
			"AND ifnull(LOWER(i.quater),'') LIKE LOWER(CONCAT('%',ifnull(:quater,''), '%')) " + 
			"AND ifnull(LOWER(i.month),'') LIKE LOWER(CONCAT('%',ifnull(:month,''), '%')) " + 
			"AND ifnull(LOWER(i.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''), '%')) " + 
			"AND ifnull(LOWER(i.circleCode),'') LIKE LOWER(CONCAT('%',ifnull(:circleCode,''), '%')) ")
	List<Invoice> findByInvoiceMonthWise(@Param("stateName") String stateName,@Param("circleCode") String circleCode,@Param("districtName") String districtName,@Param("quater") String quater,@Param("month") String month,@Param("year") String year);



}

