package com.example.demo.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.EntityModel.CammsBO;
import com.example.demo.EntityModel.CimsHistoryFin01;
import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.CimsHistoryFin02;
import com.example.demo.EntityModel.CimsHistoryFin02b;
import com.example.demo.EntityModel.Clinic;
import com.example.demo.repository.CammsBORepository;
import com.example.demo.repository.CimsHistoryFin01Repository;
import com.example.demo.repository.CimsHistoryFin02aRepository;
import com.example.demo.repository.CimsHistoryFin02Repository;
import com.example.demo.repository.CimsHistoryFin02bRepository;
import com.example.demo.repository.ClinicRepository;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class CammsDataMigrationService {
	
	//@CreationTimestamp
	//private LocalDate createdDate = LocalDate.now();
	
	@Autowired
	private CammsBORepository cammsBORepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private CimsHistoryFin01Repository cimsHistoryFin01Repository;
	
	@Autowired
	private CimsHistoryFin02aRepository cimsHistoryFin02aRepository;
	
	@Autowired
	private CimsHistoryFin02Repository cimsHistoryFin02Repository;
	
	@Autowired
	private CimsHistoryFin02bRepository cimsHistoryFin02bRepository;
	
public void dataMigrateCammsBOToCimsHistoryFin01() {
		
		LocalDate createdDate = LocalDate.now();
		
		List<CammsBO> cammsBODataForFin01 = cammsBORepository.findByFinCategory("Fin_06");
		
		List<CammsBO> cammsBODataForFin01Limit = new ArrayList<CammsBO>();
		
		Integer limitLength = 1500;
		
		if(cammsBODataForFin01.size()<limitLength) {
			limitLength = cammsBODataForFin01.size(); 
		}
		
		for(int i=0; i<limitLength; i++) {
			CammsBO cB = new CammsBO();
			if(cammsBODataForFin01.get(i) != null) {
			cB = cammsBODataForFin01.get(i);
			cammsBODataForFin01Limit.add(cB);
			} else {
				break;
			}
		}
		
		cammsBODataForFin01Limit.forEach(cammsBO -> {
			try {
				CimsHistoryFin01 cimsHistoryFin01 = new CimsHistoryFin01();
				Clinic clinic = clinicRepository.findByClinicCode(cammsBO.getClinicCode());
				cimsHistoryFin01.setStateId(clinic.getStateId());
				cimsHistoryFin01.setDistrictId(clinic.getDistrictId());
				cimsHistoryFin01.setClinicId(clinic.getId());
				cimsHistoryFin01.setClinicTypeId(clinic.getClinicTypeId());
				cimsHistoryFin01.setAssetName(cammsBO.getBECategory());
				cimsHistoryFin01.setBeNumber(cammsBO.getBENumber());
				cimsHistoryFin01.setInvoiceTypeId(1);
				cimsHistoryFin01.setMonth(String.valueOf(createdDate.getMonthValue()));
				cimsHistoryFin01.setYear(String.valueOf(createdDate.getYear()));
				cimsHistoryFin01.setAcceptedDate(cammsBO.getAcceptanceDate());
				cimsHistoryFin01.setInstalledDate(cammsBO.getInstallationDate());
				cimsHistoryFin01.setModelNumber(cammsBO.getModelNumber());
				cimsHistoryFin01.setPurchaseAmount(Double.valueOf(cammsBO.getPurchaseCost()));
				cimsHistoryFin01.setPurchasedDate(cammsBO.getPurchaseDate());
				cimsHistoryFin01.setSerialNumber(cammsBO.getSerialNumber());
				cimsHistoryFin01Repository.save(cimsHistoryFin01);
			cammsBO.setIsMigrated("Y");
			cammsBORepository.save(cammsBO);
			} catch(Exception e) {
				System.out.println(e);
			}
		});
		
	}
	
	public void dataMigrateCammsBOToCimsHistoryFin02a() {
		
		LocalDate createdDate = LocalDate.now();
		
		List<CammsBO> cammsBODataForFin02a = cammsBORepository.findByFinCategoryAndIsMigratedIsNull("Fin_07");
		
		List<CammsBO> cammsBODataForFin02aLimit = new ArrayList<CammsBO>();
		
		Integer limitLength = 1500;
		
		if(cammsBODataForFin02a.size()<limitLength) {
			limitLength = cammsBODataForFin02a.size(); 
		}
		
		for(int i=0; i<limitLength; i++) {
			CammsBO cB = new CammsBO();
			if(cammsBODataForFin02a.get(i) != null) {
			cB = cammsBODataForFin02a.get(i);
			cammsBODataForFin02aLimit.add(cB);
			} else {
				break;
			}
		}
		
		cammsBODataForFin02aLimit.forEach(cammsBO -> {
			try {
			CimsHistoryFin02a cimsHistoryFin02a = new CimsHistoryFin02a();
			Clinic clinic = clinicRepository.findByClinicCode(cammsBO.getClinicCode());
			cimsHistoryFin02a.setStateId(clinic.getStateId());
			cimsHistoryFin02a.setDistrictId(clinic.getDistrictId());
			cimsHistoryFin02a.setClinicId(clinic.getId());
			cimsHistoryFin02a.setClinicTypeId(clinic.getClinicTypeId());
			cimsHistoryFin02a.setAssetName(cammsBO.getBECategory());
			cimsHistoryFin02a.setBeNumber(cammsBO.getBENumber());
			cimsHistoryFin02a.setInvoiceTypeId(2);
			cimsHistoryFin02a.setMonth(String.valueOf(createdDate.getMonthValue()));
			cimsHistoryFin02a.setYear(String.valueOf(createdDate.getYear()));
			cimsHistoryFin02a.setBatchNumber(cammsBO.getBatchNumber());
			cimsHistoryFin02a.setInstallmentNumber((Integer.valueOf((cammsBO.getCurrentInstallmentNo())) + 0) +"/96");
			cimsHistoryFin02a.setRentalCharges(Double.valueOf(cammsBO.getRentalPerMonth()));
			cimsHistoryFin02a.setTcCertificate(cammsBO.getBENumber());
			cimsHistoryFin02a.setValueOfEquipment(Double.valueOf(cammsBO.getPurchaseCost()));
			cimsHistoryFin02aRepository.save(cimsHistoryFin02a);
			cammsBO.setIsMigrated("Y");
			cammsBORepository.save(cammsBO);
			} catch(Exception e) {
				System.out.println(e);
			}
		});
		
	}
	
	public void dataMigrateCammsBOToCimsHistoryFin08b() {
		
		LocalDate createdDate = LocalDate.now();
		
		List<CammsBO> cammsBODataForFin08b = cammsBORepository.findByFinCategoryAndIsMigratedIsNull("Fin_08B");
		
		List<CammsBO> cammsBODataForFin08bLimit = new ArrayList<CammsBO>();
		
		Integer limitLength = 1500;
		
		if(cammsBODataForFin08b.size()<limitLength) {
			limitLength = cammsBODataForFin08b.size(); 
		}
		
		for(int i=0; i<limitLength; i++) {
			CammsBO cB = new CammsBO();
			cB = cammsBODataForFin08b.get(i);
			cammsBODataForFin08bLimit.add(cB);
		}
		
		cammsBODataForFin08bLimit.forEach(cammsBO -> {
			try {
			CimsHistoryFin02 cimsHistoryFin02 = new CimsHistoryFin02();
			Clinic clinic = clinicRepository.findByClinicCode(cammsBO.getClinicCode());
			cimsHistoryFin02.setStateId(clinic.getStateId());
			cimsHistoryFin02.setDistrictId(clinic.getDistrictId());
			cimsHistoryFin02.setClinicId(clinic.getId());
			cimsHistoryFin02.setClinicTypeId(clinic.getClinicTypeId());
			cimsHistoryFin02.setCircleId(clinic.getCircleId());
			cimsHistoryFin02.setAssetName(cammsBO.getBECategory());
			cimsHistoryFin02.setBeNumber(cammsBO.getBENumber());
			cimsHistoryFin02.setInvoiceTypeId(3);
			cimsHistoryFin02.setIsFin08b("Y");
			cimsHistoryFin02.setIsFin08c("N");
			cimsHistoryFin02.setMaintenanceCharges(Double.valueOf(cammsBO.getMonthlyRev()));
			cimsHistoryFin02.setMonth(String.valueOf(createdDate.getMonthValue()));
			cimsHistoryFin02.setValueOfEquipment(Double.valueOf(cammsBO.getPurchaseCost()));
			cimsHistoryFin02.setYear(String.valueOf(createdDate.getYear()));
			cimsHistoryFin02Repository.save(cimsHistoryFin02);
			cammsBO.setIsMigrated("Y");
			cammsBORepository.save(cammsBO);
		} catch (Exception e) {
			System.out.println(e);
		}
		});
		
	}
	
	public void dataMigrateCammsBOToCimsHistoryFin08c() {
		
		LocalDate createdDate = LocalDate.now();
		
		List<CammsBO> cammsBODataForFin08c = cammsBORepository.findByFinCategoryAndIsMigratedIsNull("Fin_08C");
		
		List<CammsBO> cammsBODataForFin08cLimit = new ArrayList<CammsBO>();
		
		Integer limitLength = 1500;
		
		if(cammsBODataForFin08c.size()<limitLength) {
			limitLength = cammsBODataForFin08c.size(); 
		}
		
		for(int i=0; i<limitLength; i++) {
			CammsBO cB = new CammsBO();
			cB = cammsBODataForFin08c.get(i);
			cammsBODataForFin08cLimit.add(cB);
		}
		
		cammsBODataForFin08cLimit.forEach(cammsBO -> {
			try {
			CimsHistoryFin02 cimsHistoryFin02 = new CimsHistoryFin02();
			Clinic clinic = clinicRepository.findByClinicCode(cammsBO.getClinicCode());
			cimsHistoryFin02.setStateId(clinic.getStateId());
			cimsHistoryFin02.setDistrictId(clinic.getDistrictId());
			cimsHistoryFin02.setClinicId(clinic.getId());
			cimsHistoryFin02.setClinicTypeId(clinic.getClinicTypeId());
			cimsHistoryFin02.setCircleId(clinic.getCircleId());
			cimsHistoryFin02.setAssetName(cammsBO.getBECategory());
			cimsHistoryFin02.setBeNumber(cammsBO.getBENumber());
			cimsHistoryFin02.setInvoiceTypeId(3);
			cimsHistoryFin02.setIsFin08b("N");
			cimsHistoryFin02.setIsFin08c("Y");
			cimsHistoryFin02.setMaintenanceCharges(Double.valueOf(cammsBO.getMonthlyRev()));
			cimsHistoryFin02.setMonth(String.valueOf(createdDate.getMonthValue()));
			cimsHistoryFin02.setValueOfEquipment(Double.valueOf(cammsBO.getPurchaseCost()));
			cimsHistoryFin02.setYear(String.valueOf(createdDate.getYear()));
			cimsHistoryFin02Repository.save(cimsHistoryFin02);
			cammsBO.setIsMigrated("Y");
			cammsBORepository.save(cammsBO);
			} catch (Exception e) {
				System.out.println(e);
			}
		});
		
	}

}
