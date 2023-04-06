package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoicePaymentHistory;

public interface InvoicePaymentHistoryRepository extends CrudRepository<InvoicePaymentHistory, Integer> {
	
	InvoicePaymentHistory findByPaymentRefNo(String paymentRefNo);
	InvoicePaymentHistory findByTransactionRefNo(String transactionRefNo);
	InvoicePaymentHistory findByPaymentDate(LocalDate paymentDate);
	List<InvoicePaymentHistory> findByStateId(Integer stateId);
	
	List<InvoicePaymentHistory> findByInvoiceNo(String invoiceNo);
	
//	mine
	@Query("From InvoicePaymentHistory WHERE id =:id ")
	InvoicePaymentHistory findByIds(int id);
	
	@Query("From InvoicePaymentHistory WHERE id =:id ")
	InvoicePaymentHistory findByPaymentId(int id);
//	FROM Invoice WHERE paymentStatus = :paymentStatus1 OR paymentStatus = :paymentStatus2 ORDER BY id DESC
	/*
	List<InvoicePaymentHistory> findByStateId(Integer stateId);
	*/
	/*
	List<InvoicePaymentHistory> findByStateName(String stateName);
	List<InvoicePaymentHistory> findByDistrictId(Integer districtId);
	List<InvoicePaymentHistory> findByDistrictName(String districtName);
	List<InvoicePaymentHistory> findByInvoiceTypeId(Integer invoiceTypeId);
	List<InvoicePaymentHistory> findByClinicTypeId(Integer clinicTypeId);
	List<InvoicePaymentHistory> findByPaymentMode(@Param("paymentMode") String paymentMode);	//
	*/
	@Query("From InvoicePaymentHistory i where ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''),'%')) "
			+ "AND ifnull(LOWER(i.transactionRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:transactionRefNo,''), '%')) "
			+ "AND ifnull(DATE(i.paymentDate),'') BETWEEN ifnull(:paymentDateFrom,'') AND ifnull(:paymentDateTo,'') "
			+ "AND (i.paymentMode IN :paymentMode)"
//			+ "AND ifnull(LOWER(i.paymentMode),'') LIKE LOWER(CONCAT('%',ifnull(:paymentMode,''), '%')) "
			+ "ORDER BY id DESC ")
	List<InvoicePaymentHistory> findByPaymentDateAndPaymentMode(@Param("transactionRefNo") String transactionRefNo,
			@Param("paymentRefNo") String paymentRefNo,@Param("paymentMode") List<String> paymentMode,@Param("paymentDateFrom") String paymentDateFrom,@Param("paymentDateTo") String paymentDateTo);
	
	@Query("From InvoicePaymentHistory i where ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''),'%')) "
			+ "AND ifnull(LOWER(i.transactionRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:transactionRefNo,''), '%')) "
			+ "AND ifnull(DATE(i.createdDate),'') BETWEEN ifnull(:paymentDateFrom,'') AND ifnull(:paymentDateTo,'') "
			+ "AND (i.paymentMode IN :paymentMode)"
//			+ "AND ifnull(LOWER(i.paymentMode),'') LIKE LOWER(CONCAT('%',ifnull(:paymentMode,''), '%')) "
			+ "ORDER BY id DESC ")
	List<InvoicePaymentHistory> findByPaymentDateAndPaymentModeEntryDate(@Param("transactionRefNo") String transactionRefNo,
			@Param("paymentRefNo") String paymentRefNo,@Param("paymentMode") List<String> paymentMode,@Param("paymentDateFrom") String paymentDateFrom,@Param("paymentDateTo") String paymentDateTo);
	
	@Query("From InvoicePaymentHistory i where ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''),'%')) "
			+ "AND ifnull(LOWER(i.transactionRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:transactionRefNo,''), '%')) "
//			+ "AND ifnull(LOWER(i.paymentMode),'') LIKE LOWER(CONCAT('%',ifnull(:paymentMode,''), '%')) "
			+ "AND (i.paymentMode IN :paymentMode)"
			+ "ORDER BY id DESC ")
	List<InvoicePaymentHistory> findByPaymentMode(@Param("transactionRefNo") String transactionRefNo,@Param("paymentRefNo") String paymentRefNo,@Param("paymentMode") List<String> paymentMode);
	

	

	 @Query("From InvoicePaymentHistory i WHERE ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
			+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
//			+ "AND ifnull(LOWER(i.invoiceTypeName),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeName,''), '%')) "
			+ "AND (i.invoiceTypeName IN :invoiceTypeName)"
			+ "AND ifnull(LOWER(i.clinicTypeCode),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeCode,''), '%')) "
			+ "AND ifnull(LOWER(i.transactionRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:transactionRefNo,''), '%')) "
			+ "AND ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''), '%')) "			
			+ "AND ifnull(LOWER(i.paymentMode),'') LIKE LOWER(CONCAT('%',ifnull(:paymentMode,''), '%')) " 
			+ "AND ifnull(DATE(i.paymentDate),'') BETWEEN ifnull(:dateFrom,'') AND ifnull(:dateTo,'') "
			+ "ORDER BY id DESC ")			
	List<InvoicePaymentHistory> findByCollectionReport(@Param("stateName") String stateName, @Param("dateFrom") String invoiceDateFrom,@Param("dateTo") String invoiceDateTo,@Param("districtName") String districtName,@Param("invoiceTypeName") List<String> invoiceTypeName,@Param("clinicTypeCode") String clinicTypeCode
			 ,@Param("transactionRefNo") String transactionRefNo,@Param("paymentRefNo") String paymentRefNo,@Param("paymentMode") String paymentMode);
	 
	 // 
			
	/*
	/* @Query("select new com.example.demo.EntityModel.InvoicePaymentHistory(i.id,i.invoiceNo,i.invoiceTypeId,i.stateId,i.stateName,"
	 		+ "			i.districtId,i.districtName,i.clinicTypeId,"
	 		+ "			i.paymentDate,i.paymentMode,i.transactionRefNo,i.paymentRefNo,i.paymentRefNo)From InvoicePaymentHistory i "	
				+ "where ifnull(LOWER(i.stateName),'') LIKE LOWER(CONCAT('%',ifnull(:stateName,''),'%')) "
				+ "AND ifnull(LOWER(i.districtName),'') LIKE LOWER(CONCAT('%',ifnull(:districtName,''), '%')) "
				+ "AND ifnull(LOWER(i.clinicTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:clinicTypeId,''), '%')) "
				+ "AND ifnull(LOWER(i.transactionRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:transactionRefNo,''), '%')) "
				+ "AND ifnull(LOWER(i.paymentMode),'') LIKE LOWER(CONCAT('%',ifnull(:paymentMode,''), '%')) "
				+ "AND ifnull(LOWER(i.paymentRefNo),'') LIKE LOWER(CONCAT('%',ifnull(:paymentRefNo,''), '%')) "			
				+ "AND ifnull(DATE(i.paymentDate),'') BETWEEN ifnull(:invoiceDateFrom,'') AND ifnull(:invoiceDateTo,'') "
				+ "AND ifnull(LOWER(i.invoiceTypeId),'')LIKE LOWER(CONCAT('%',ifnull(:invoiceTypeId,''), '%'))  ORDER BY stateId,districtId,clinicTypeId,invoiceTypeId ASC")		
				
		List<InvoicePaymentHistory> findByPaymentRefNo(@Param("stateName") String stateName,@Param("districtName") String districtName,@Param("invoiceTypeId") Integer invoiceTypeId,@Param("clinicTypeId") Integer clinicTypeId
				,@Param("transactionRefNo") String transactionRefNo,@Param("paymentRefNo") String paymentRefNo,@Param("invoiceDateFrom") String invoiceDateFrom,@Param("invoiceDateTo") String invoiceDateTo,@Param("paymentMode") String paymentMode); 
*/
	
	              

}
