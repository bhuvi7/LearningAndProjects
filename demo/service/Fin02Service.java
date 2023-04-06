package com.example.demo.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import com.example.demo.EntityModel.Clinic;
import com.example.demo.EntityModel.InvoiceGeneration;
import com.example.demo.repository.ClinicRepository;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.EntityModel.CimsHistoryFin02;
import com.example.demo.EntityModel.CimsHistoryFin02Distinct;
import com.example.demo.EntityModel.CimsHistoryFin02a;
import com.example.demo.EntityModel.CimsHistoryFin08b;
import com.example.demo.EntityModel.CimsHistoryFin08bDistinct;
import com.example.demo.EntityModel.CimsHistoryFin08c;
import com.example.demo.EntityModel.CimsHistoryFin08cDistinct;
import com.example.demo.EntityModel.CostCenterCode;
import com.example.demo.EntityModel.Fin03;
import com.example.demo.EntityModel.Fin03a;
import com.example.demo.EntityModel.Fin03aMapping;
import com.example.demo.EntityModel.Fin08;
import com.example.demo.EntityModel.Fin08Distinct;
import com.example.demo.EntityModel.Invoice;
import com.example.demo.EntityModel.InvoiceType;
import com.example.demo.EntityModel.Fin08b;
import com.example.demo.EntityModel.Fin08c;
import com.example.demo.EntityModel.Fin09;
import com.example.demo.EntityModel.Fin09Clinic;
import com.example.demo.repository.CimsHistoryFin02Repository;
import com.example.demo.repository.CimsHistoryFin08bRepository;
import com.example.demo.repository.CimsHistoryFin08cRepository;
import com.example.demo.repository.CostCenterCodeRepository;
import com.example.demo.repository.Fin03Repository;
import com.example.demo.repository.Fin08Repository;
import com.example.demo.repository.Fin08bRepository;
import com.example.demo.repository.Fin08cRepository;
import com.example.demo.repository.Fin09ClinicRepository;
import com.example.demo.repository.Fin09Repository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.InvoiceTypeRepository;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class Fin02Service {
	
	@CreationTimestamp
	private LocalDate createdDate = LocalDate.now();
	
	DecimalFormat df = new DecimalFormat("###.##");
	
	
	//DecimalFormat dfi = new DecimalFormat( "#,###.00" );
	//DecimalFormat dfe = new DecimalFormat( "#,###,##0.00" );
	@Autowired
	private CostCenterCodeRepository costCenterCodeRepository;
	
	@Autowired
	private CimsHistoryFin02Repository cimsHistoryFin02Repository;
	
	@Autowired
	private CimsHistoryFin08bRepository cimsHistoryFin08bRepository;
	
	@Autowired
	private CimsHistoryFin08cRepository cimsHistoryFin08cRepository;
	
	@Autowired
	private Fin08bRepository fin08bRepository;
	

	@Autowired
	private ClinicRepository clinicRepository;
	
	
	
	@Autowired
	private Fin08cRepository fin08cRepository;
	
	@Autowired
	private Fin08Repository fin08Repository;
	
	@Autowired
	private Fin09Repository fin09Repository;
	
	@Autowired
	private Fin09ClinicRepository fin09ClinicRepository;
	
	@Autowired
	private Fin03Repository fin03Repository;
	
	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public String invoiceQuater(String month) {
		String quater = null;
		if(month.equals("1") || month.equals("2") || month.equals("3")){ quater = "Q1";}
		else if(month.equals("4") || month.equals("5") || month.equals("6")){ quater = "Q2";}
		else if(month.equals("7") || month.equals("8") || month.equals("9")){ quater = "Q3";}
		else if(month.equals("10") || month.equals("11") || month.equals("12")){ quater = "Q4";}
		return quater;
	}
	
	
	/*temporary fix for updating status */
	public void updateFin08RefNoInCimsHistoryFin02() {
		   List<CimsHistoryFin02> cimsHistoryFin02s = cimsHistoryFin02Repository.findByStateIdAndMonthAndFin08RefNoIsNull(1, "11");
		   cimsHistoryFin02s.forEach(c02 -> {
			   Fin08 fin08 = fin08Repository.findByClinicIdAndMonth(c02.getClinicId(), c02.getMonth());
			   System.out.println(fin08.getClinicCode());
			   c02.setFin08RefNo(fin08.getCode());
			   c02.setFin08Status(fin08.getStatus());
			   cimsHistoryFin02Repository.save(c02);
		   });
		   System.out.println(cimsHistoryFin02s.size());
	   }
	
	
	public Iterable<CimsHistoryFin08b> getFin08bCreateList(){
		Set<CimsHistoryFin08bDistinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin08b> uniqueCimsHistoryFin08b = new ArrayList<CimsHistoryFin08b>();
		List<CimsHistoryFin08b> cimsHistoryFin08bList = (List<CimsHistoryFin08b>) cimsHistoryFin08bRepository.findByFin08bStatusIsNull();
		cimsHistoryFin08bList.forEach(cims -> {
		CimsHistoryFin08bDistinct cimsTransactionUnique = new CimsHistoryFin08bDistinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear(),cims.getFin08bStatus());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin08b.add(cims);
		}
		});
		return uniqueCimsHistoryFin08b;
	}
	
	public Fin08b createFin08b(List<CimsHistoryFin08b> cimsHistoryFin08bList) {
		CimsHistoryFin08b cimsHistoryFin08b = cimsHistoryFin08bList.get(0);
		Fin08b fin08b = new Fin08b();
		BeanUtils.copyProperties(cimsHistoryFin08b, fin08b );
		fin08b.setId(null);
		fin08b.setCreatedDate(null);
		fin08b.setUpdatedDate(null);
		fin08b.setRemarks(null);
		fin08b.setCode("FIN08B" + fin08b.getClinicCode() + "-" + fin08b.getMonth()+"-"+fin08b.getYear());
		fin08b.setStatus("IN INTERNAL APPROVAL");
		fin08b.setTotalMaintenanceCharges(0.0);
		fin08b.setTotalEquipmentCount(0);
		cimsHistoryFin08bList.forEach(cims -> {
			cims.setFin08bRefNo(fin08b.getCode());
			cims.setFin08bStatus(fin08b.getStatus());
			fin08b.setTotalMaintenanceCharges(fin08b.getTotalMaintenanceCharges() + cims.getMaintenanceCharges());
			fin08b.setTotalEquipmentCount(fin08b.getTotalEquipmentCount() + 1);
			cimsHistoryFin08bRepository.save(cims);
		});
		fin08bRepository.save(fin08b);
		return fin08b;
	}
	
	public Fin08b updateFin08bStatus(Fin08b fin08b) {
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin08b> cimsHistoryList = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
		cimsHistoryList.forEach(cims -> {
			cims.setFin08bStatus(fin08b.getStatus());
			cimsHistoryFin08bRepository.save(cims);
		});
		return fin08b;
	}
	
	public Iterable<CimsHistoryFin08c> getFin08cCreateList(){
		Set<CimsHistoryFin08cDistinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin08c> uniqueCimsHistoryFin08c = new ArrayList<CimsHistoryFin08c>();
		List<CimsHistoryFin08c> cimsHistoryFin08cList = (List<CimsHistoryFin08c>) cimsHistoryFin08cRepository.findByFin08cStatusIsNull();
		cimsHistoryFin08cList.forEach(cims -> {
		CimsHistoryFin08cDistinct cimsTransactionUnique = new CimsHistoryFin08cDistinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear(),cims.getFin08cStatus());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin08c.add(cims);
		}
		});
		return uniqueCimsHistoryFin08c;
	}
	
	public Fin08c createFin08c(List<CimsHistoryFin08c> cimsHistoryFin08cList) {
		CimsHistoryFin08c cimsHistoryFin08c = cimsHistoryFin08cList.get(0);
		Fin08c fin08c = new Fin08c();
		BeanUtils.copyProperties(cimsHistoryFin08c, fin08c );
		fin08c.setId(null);
		fin08c.setCreatedDate(null);
		fin08c.setUpdatedDate(null);
		fin08c.setRemarks(null);
		fin08c.setCode("FIN08C" + fin08c.getClinicCode() + "-" + fin08c.getMonth()+"-"+fin08c.getYear());
		fin08c.setStatus("IN INTERNAL APPROVAL");
		fin08c.setTotalMaintenanceCharges(0.0);
		fin08c.setTotalEquipmentCount(0);
		cimsHistoryFin08cList.forEach(cims -> {
			cims.setFin08cRefNo(fin08c.getCode());
			cims.setFin08cStatus(fin08c.getStatus());
			fin08c.setTotalMaintenanceCharges(fin08c.getTotalMaintenanceCharges() + cims.getMaintenanceCharges());
			fin08c.setTotalEquipmentCount(fin08c.getTotalEquipmentCount() + 1);
			cimsHistoryFin08cRepository.save(cims);
		});
		fin08cRepository.save(fin08c);
		return fin08c;
	}
	
	public Fin08c updateFin08cStatus(Fin08c fin08c) {
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin08c> cimsHistoryList = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
		cimsHistoryList.forEach(cims -> {
			cims.setFin08cStatus(fin08c.getStatus());
			cimsHistoryFin08cRepository.save(cims);
		});
		return fin08c;
	}
	
	public List<Fin08c> getFin08CreateList(){
		List<Fin08c> fin08CreateList = new ArrayList<Fin08c>();
		List<Fin08b> fin08bList = fin08bRepository.findByStatusFin08RefNoIsNull();
		List<Fin08c> fin08cList = fin08cRepository.findByStatusFin08RefNoIsNull();
		List<Fin08c> fin08bAsFin08c = new ArrayList<Fin08c>(); 
		fin08CreateList.addAll(fin08cList);
		fin08bList.forEach(fin08b -> {	
			Fin08c fin08c = new Fin08c();
			BeanUtils.copyProperties(fin08b, fin08c);
			fin08bAsFin08c.add(fin08c);
		});
		System.out.println(fin08bAsFin08c);
		fin08cList.forEach(fin08c -> {
			fin08bAsFin08c.forEach(fin08bAsfin08c -> {
				if(fin08bAsfin08c.getClinicId() == fin08c.getClinicId()) {
					fin08CreateList.remove(fin08c);
				}
			});
		});
		System.out.println(fin08CreateList);
		fin08CreateList.addAll(fin08bAsFin08c);
		Comparator<Fin08c> compareByClinicId = (Fin08c o1, Fin08c o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		Collections.sort(fin08CreateList, compareByClinicId);
		return fin08CreateList;
	}
	
	public Fin08b findFin08bByClinicIdMonthYear(Integer clinicId) {
		LocalDate createdDate = LocalDate.now();
		Fin08b fin08b = fin08bRepository.findByClinicIdAndMonthAndYear(clinicId,String.valueOf(createdDate.getMonthValue()),String.valueOf((createdDate.getYear())));
		return fin08b;
	}
	
	public Fin08c findFin08cByClinicIdMonthYear(Integer clinicId) {
		LocalDate createdDate = LocalDate.now();
		Fin08c fin08c = fin08cRepository.findByClinicIdAndMonthAndYear(clinicId,String.valueOf(createdDate.getMonthValue()),String.valueOf((createdDate.getYear())));
		return fin08c;
	}
	
	public Fin08 createFin08(int clinicId) {
		Fin08b fin08b = fin08bRepository.findByClinicId(clinicId);
		Fin08c fin08c = fin08cRepository.findByClinicId(clinicId);
		Fin08 fin08 = new Fin08();
		if(fin08b != null) {
		BeanUtils.copyProperties(fin08b, fin08);
		}
		if(fin08c != null) {
		BeanUtils.copyProperties(fin08c, fin08);
		}
		fin08.setId(null);
		fin08.setCreatedDate(null);
		fin08.setUpdatedDate(null);
		fin08.setRemarks(null);
		fin08.setTotalMaintenanceCharges(0.0);
		if(fin08b != null) {
			fin08.setTotalMaintenanceCharges( fin08.getTotalMaintenanceCharges() + fin08b.getTotalMaintenanceCharges());
		}
		if(fin08c != null) {
			fin08.setTotalMaintenanceCharges( fin08.getTotalMaintenanceCharges() + fin08c.getTotalMaintenanceCharges());
		}
		fin08.setCode("FIN08" + fin08.getClinicCode() + "-" + fin08.getMonth()+"-"+fin08.getYear());
		fin08.setStatus("IN INTERNAL APPROVAL");
		fin08Repository.save(fin08);
		if(fin08b != null) {
		fin08b.setFin08RefNo(fin08.getCode());
		fin08b.setFin08Status(fin08.getStatus());
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
		cimsHistoryFin08b.forEach(cims -> {
			cims.setFin08RefNo(fin08.getCode());
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin08bRepository.save(cims);
		});
		}
		if(fin08c != null) {
		fin08c.setFin08RefNo(fin08.getCode());
		fin08c.setFin08Status(fin08.getStatus());
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
		cimsHistoryFin08c.forEach(cims -> {
			cims.setFin08RefNo(fin08.getCode());
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin08cRepository.save(cims);
		});
		}
		return fin08;
	}
	
	public Fin08 updateFin08Status(Fin08 fin08) {
		LocalDate createdDate = LocalDate.now();
		if(fin08.getStatus().equals("APPROVED BY MOH")){
			fin08.setApproval2Date(createdDate);
		}
		fin08Repository.save(fin08);
		Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
		if(fin08b != null) {
		fin08b.setFin08Status(fin08.getStatus());
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
		cimsHistoryFin08b.forEach(cims -> {
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin08bRepository.save(cims);
		});
		}
		Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
		if(fin08c != null) {
		fin08c.setFin08Status(fin08.getStatus());
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
		cimsHistoryFin08c.forEach(cims -> {
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin08cRepository.save(cims);
		});
		}
		return fin08;
	}
	
	public List<Fin08> getFin03CreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin08Distinct> uniqueSet = new HashSet<>();
		List<Fin08> uniqueFin08List = new ArrayList<Fin08>();
		List<Fin08>  fin08List = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08List.forEach(e->{
			Fin08Distinct CimsTransactionUnique = new Fin08Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin08List.add(e);
			}			
		});

		return uniqueFin08List;
	}
	
	public List<Fin08> getFin03CreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		Set<Fin08Distinct> uniqueSet = new HashSet<>();
		List<Fin08> uniqueFin08List = new ArrayList<Fin08>();
		List<Fin08>  fin08List = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08List = (List<Fin08>) fin08Repository.findByStatusAndFin03StatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08List.forEach(e->{
			Fin08Distinct CimsTransactionUnique = new Fin08Distinct(e.getStateId(),e.getDistrictId(),e.getClinicTypeId(),e.getMonth(),e.getYear(),e.getStatus());
			if(uniqueSet.add(CimsTransactionUnique)) {
				uniqueFin08List.add(e);
			}			
		});

		return uniqueFin08List;
	}
	
	public Fin09Clinic fin09Finder(List<Fin09Clinic> fin09Datas, Integer clinicId) {
	Fin09Clinic fin09Clinic = new Fin09Clinic();
		 fin09Datas.forEach(fin09Cli -> {
		 if(fin09Cli.getClinicId() == clinicId) {
			 BeanUtils.copyProperties(fin09Cli, fin09Clinic );
		 }
		 });
		return fin09Clinic;
	}
	
	public Fin03 createFin03(Integer districtId, Integer clinicTypeId) {
		LocalDate date = LocalDate.now();
		List<Fin03> fin03Check = fin03Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(districtId, clinicTypeId, String.valueOf(date.getMonthValue()), String.valueOf(date.getYear()));
		if(fin03Check.size() == 0) {
		List<Fin08> fin08Datas = fin08Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNullAndMonthAndYear(districtId, clinicTypeId, "APPROVED BY MOH",
				String.valueOf(date.getMonthValue()), String.valueOf(date.getYear()));
		Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNull(districtId, clinicTypeId);
		final List<Fin09Clinic> fin09ClinicDatas ;
		if(fin09!=null){
			fin09ClinicDatas = fin09.getFin09Clinic();
		}else {
			fin09ClinicDatas = new ArrayList<Fin09Clinic>();
		}
		Fin03 fin03 = new Fin03();
		BeanUtils.copyProperties(fin08Datas.get(0), fin03 );
		fin03.setId(null);
		fin03.setCreatedDate(null);
		fin03.setUpdatedDate(null);
		fin03.setRemarks(null);
		fin03.setCode("FIN03"+ fin03.getDistrictCode()+ fin03.getClinicTypeCode()+fin03.getMonth()+"-"+fin03.getYear());
		fin03.setStatus("IN INTERNAL APPROVAL");
		fin03.setTotalMaintenanceCharges(0.0);
		fin03.setTotalPenaltyCharges(0.0);
		fin03.setNetMaintenanceCharges(0.0);
		fin08Datas.forEach(fin08 -> {
			fin03.setTotalMaintenanceCharges(fin03.getTotalMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
			Fin09Clinic fin09Clinic = new Fin09Clinic();
				fin09Clinic = fin09Finder(fin09ClinicDatas,fin08.getClinicId());
			if(fin09Clinic.getTotalPenalty() != null) {
				fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges()  + (fin08.getTotalMaintenanceCharges() - fin09.getTotalPenalty()));
				fin09Clinic.setFin03RefNo(fin03.getCode());
				fin09Clinic.setFin03Status(fin03.getStatus());
				fin09ClinicRepository.save(fin09Clinic);
			} else {
				fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
			}
			fin08.setFin03RefNo(fin03.getCode());
			fin08.setFin03Status(fin03.getStatus());
			fin08Repository.save(fin08);
			Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
			Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
			if(fin08b != null) {
			fin08b.setFin03RefNo(fin03.getCode());
			fin08b.setFin03Status(fin03.getStatus());
			fin08bRepository.save(fin08b);
			List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
			cimsHistoryFin08b.forEach(cims ->{
				cims.setFin03RefNo(fin03.getCode());
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin08bRepository.save(cims);
			});
			}
			if(fin08c != null) {
			fin08c.setFin03RefNo(fin03.getCode());
			fin08c.setFin03Status(fin03.getStatus());
			fin08cRepository.save(fin08c);
			List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
			cimsHistoryFin08c.forEach(cims -> {
				cims.setFin03RefNo(fin03.getCode());
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin08cRepository.save(cims);
			});
			}
		});
		if(fin09!=null) {
			fin03.setTotalPenaltyCharges(fin03.getTotalPenaltyCharges() + fin09.getTotalPenalty());
			fin09.setFin03RefNo(fin03.getCode());
			fin09.setFin03Status(fin03.getStatus());
			fin09Repository.save(fin09);	
		}
		fin03Repository.save(fin03);
		return fin03;
		} else {
			List<Fin08> fin08Datas = fin08Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNullAndMonthAndYear(districtId, clinicTypeId, "APPROVED BY MOH",
					String.valueOf(date.getMonthValue()), String.valueOf(date.getYear()));
			Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNull(districtId, clinicTypeId);
			final List<Fin09Clinic> fin09ClinicDatas ;
			if(fin09!=null){
				fin09ClinicDatas = fin09.getFin09Clinic();
			}else {
				fin09ClinicDatas = new ArrayList<Fin09Clinic>();
			}
			Fin03 fin03 = new Fin03();
			BeanUtils.copyProperties(fin08Datas.get(0), fin03 );
			fin03.setId(null);
			fin03.setCreatedDate(null);
			fin03.setUpdatedDate(null);
			fin03.setRemarks(null);
			fin03.setCode("FIN03"+ fin03.getDistrictCode()+ fin03.getClinicTypeCode()+fin03.getMonth()+"-"+fin03.getYear()+"-"+fin03Check.size());
			fin03.setStatus("IN INTERNAL APPROVAL");
			fin03.setTotalMaintenanceCharges(0.0);
			fin03.setTotalPenaltyCharges(0.0);
			fin03.setNetMaintenanceCharges(0.0);
			fin08Datas.forEach(fin08 -> {
				fin03.setTotalMaintenanceCharges(fin03.getTotalMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
				Fin09Clinic fin09Clinic = new Fin09Clinic();
					fin09Clinic = fin09Finder(fin09ClinicDatas,fin08.getClinicId());
				if(fin09Clinic.getTotalPenalty() != null) {
					fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges()  + (fin08.getTotalMaintenanceCharges() - fin09.getTotalPenalty()));
					fin09Clinic.setFin03RefNo(fin03.getCode());
					fin09Clinic.setFin03Status(fin03.getStatus());
					fin09ClinicRepository.save(fin09Clinic);
				} else {
					fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
				}
				fin08.setFin03RefNo(fin03.getCode());
				fin08.setFin03Status(fin03.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin03RefNo(fin03.getCode());
				fin08b.setFin03Status(fin03.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin08b.forEach(cims ->{
					cims.setFin03RefNo(fin03.getCode());
					cims.setFin03Status(fin03.getStatus());
					cimsHistoryFin08bRepository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin03RefNo(fin03.getCode());
				fin08c.setFin03Status(fin03.getStatus());
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin08c.forEach(cims -> {
					cims.setFin03RefNo(fin03.getCode());
					cims.setFin03Status(fin03.getStatus());
					cimsHistoryFin08cRepository.save(cims);
				});
				}
			});
			if(fin09!=null) {
				fin03.setTotalPenaltyCharges(fin03.getTotalPenaltyCharges() + fin09.getTotalPenalty());
				fin09.setFin03RefNo(fin03.getCode());
				fin09.setFin03Status(fin03.getStatus());
				fin09Repository.save(fin09);	
			}
			fin03Repository.save(fin03);
			return fin03;
		}
	}
	
	public Fin03 updateFin03Status(Fin03 fin03) {
		List<Fin08> fin08Datas = fin08Repository.findByFin03RefNo(fin03.getCode());
		Fin09 fin09 = fin09Repository.findByFin03RefNo(fin03.getCode());
		final List<Fin09Clinic> fin09Clinic ;
		fin08Datas.forEach(fin08 -> {
			fin08.setFin03Status(fin03.getStatus());
			fin08Repository.save(fin08);
			Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
			Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
			if(fin08b != null) {
			fin08b.setFin03Status(fin03.getStatus());
			fin08bRepository.save(fin08b);
			List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
			cimsHistoryFin08b.forEach(cims ->{
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin08bRepository.save(cims);
			});
			}
			if(fin08c != null) {
			fin08c.setFin03Status(fin03.getStatus());
			fin08cRepository.save(fin08c);
			List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
			cimsHistoryFin08c.forEach(cims -> {
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin08cRepository.save(cims);
			});
			}
		});
		if(fin09!=null){
			fin09Clinic = fin09.getFin09Clinic();
			fin09.setFin03Status(fin03.getStatus());
			fin09Repository.save(fin09);
		}else {
			fin09Clinic = new ArrayList<Fin09Clinic>();
		}
		fin09Clinic.forEach(fin09Cli -> {
			fin09Cli.setFin03Status(fin03.getStatus());
			fin09ClinicRepository.save(fin09Cli);
		});
		fin03Repository.save(fin03);
		return fin03;
	}
	
	public List<Fin03> getFin02CreateList(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<Fin03> fin03List = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
		fin03List = fin03Repository.findByFin02InvStatusIsNullAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03List = fin03Repository.findByFin02InvStatusIsNullAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
			} else if(stateId != 0 && districtId != 0) {
				fin03List = fin03Repository.findByFin02InvStatusIsNullAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
			}
		fin03List.forEach(fin03 -> {
			fin03.setFin08(null);
			fin03.setFin09(null);
		});
		return fin03List;
	}
	
	public List<Fin03> getFin02CreateListOlder(Integer stateId, Integer districtId) {
		LocalDate date = LocalDate.now();
		List<Fin03> fin03List = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
		fin03List = fin03Repository.findByFin02InvStatusIsNullAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03List = fin03Repository.findByFin02InvStatusIsNullAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
			} else if(stateId != 0 && districtId != 0) {
				fin03List = fin03Repository.findByFin02InvStatusIsNullAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
			}
		fin03List.forEach(fin03 -> {
			fin03.setFin08(null);
			fin03.setFin09(null);
		});
		return fin03List;
	}
	
	public Invoice addFin02Invoice(List<Fin03> fin03) {
		Invoice invoice = new Invoice();
		Fin03 fin03Data = fin03.get(0);
		Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeId(fin03Data.getDistrictId(), fin03Data.getClinicTypeId());
		String clinicCatCode = "";
		if(fin03Data.getClinicTypeCode().equals("PKD")) { clinicCatCode = "K"; }
		else if(fin03Data.getClinicTypeCode().equals("PPD")) { clinicCatCode = "P"; }
		invoice.setCode("2-"+fin03Data.getStateCode()+"-"+fin03Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin03Data.getMonth()+"-"+fin03Data.getYear());
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setInvoiceTypeId(3);
		invoice.setStateId(fin03Data.getStateId());
		invoice.setDistrictId(fin03Data.getDistrictId());
		invoice.setMonth(fin03Data.getMonth());
		invoice.setYear(fin03Data.getYear());
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		fin03.forEach(fin03Sub -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin03Sub.getNetMaintenanceCharges());
			fin03Sub.setFin02InvNo(invoice.getInvoiceNo());
			fin03Sub.setFin02InvStatus("IN INTERNAL APPROVAL");
			fin03Repository.save(fin03Sub);
			List<Fin08> fin08Data = fin08Repository.findByFin03RefNo(fin03Sub.getCode());
			fin08Data.forEach(fin08 -> {
				fin08.setFin02InvNo(invoice.getCode());
				fin08.setFin02InvStatus(invoice.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin02InvNo(invoice.getCode());
				fin08b.setFin02InvStatus(invoice.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin08b.forEach(cims ->{
					cims.setFin02InvNo(invoice.getCode());
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin08bRepository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin02InvNo(invoice.getCode());
				fin08c.setFin02InvStatus(invoice.getStatus());
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin08c.forEach(cims -> {
					cims.setFin02InvNo(invoice.getCode());
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin08cRepository.save(cims);
				});
				}
			});
		});
		if(fin09 != null) {
			fin09.setFin02RefNo(invoice.getCode());
			fin09.setFin02Status(invoice.getStatus());
			fin09Repository.save(fin09);	
		}
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(3);
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			df.setRoundingMode(RoundingMode.CEILING);
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
		}
		if(invoiceType.getSstIncluded().equals("Y")) {
			df.setRoundingMode(RoundingMode.CEILING);
//			setRoundingMode(RoundingMode.CEILING);
			invoice.setSst(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getSstPercentage()/100))));
		}
		df.setRoundingMode(RoundingMode.CEILING);
		invoice.setNetAfterSst(invoice.getInvoiceBaseValue()+invoice.getSst());
		invoice.setNetAfterSst(Double.valueOf(df.format(invoice.getNetAfterSst())));
		invoice.setTotalInvoiceValue(invoice.getNetAfterSst());
		invoice.setOutstandingAmount(invoice.getNetAfterSst());
		invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValue()-invoice.getRetentionAmount())));
		invoice.setPaymentStatus("PAYMENT-PENDING");
		invoice.setClinicTypeId(fin03Data.getClinicTypeId());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Invoice updateFin02InvoiceStatus(Invoice invoice) {
		invoiceRepository.save(invoice);
		List<Fin03> fin03 = fin03Repository.findByFin02InvNo(invoice.getInvoiceNo());
		Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeId(invoice.getDistrictId(),invoice.getClinicTypeId());
		fin03.forEach( fin03Data -> {
			fin03Data.setFin02InvStatus(invoice.getStatus());
			fin03Repository.save(fin03Data);
			List<Fin08> fin08Data = fin08Repository.findByFin03RefNo(fin03Data.getCode());
			fin08Data.forEach(fin08 -> {
				fin08.setFin02InvStatus(invoice.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin02InvStatus(invoice.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin08b> cimsHistoryFin08b = cimsHistoryFin08bRepository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin08b.forEach(cims ->{
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin08bRepository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin02InvStatus(invoice.getStatus());
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin08c> cimsHistoryFin08c = cimsHistoryFin08cRepository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin08c.forEach(cims -> {
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin08cRepository.save(cims);
				});
				}
			});
		});
		if(fin09 != null) {
			fin09.setFin02Status(invoice.getStatus());
			fin09Repository.save(fin09);	
		}
		return invoice;
	}
	
	
	/*---------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	public List<CimsHistoryFin02> getFin08bCreateListUsingChf2(Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getFin08bCreateListUsingChf2Older(Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08bStatusAndIsFin08bAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getEquipmentsForFin08bCreate(Integer clinicId, String month ,String year) {
		//LocalDate date = LocalDate.now();
		List<CimsHistoryFin02> equipList = cimsHistoryFin02Repository.findByClinicIdAndIsFin08bAndMonthAndYear(clinicId, String.valueOf(month), String.valueOf(year));
		return equipList;
	}
	
	public Fin08b createFin08bUsingChf2(List<CimsHistoryFin02> cimsHistoryFin02List,int userId) {
		CimsHistoryFin02 cimsHistoryFin02 = cimsHistoryFin02List.get(0);
		Fin08b fin08b = new Fin08b();
		String prvZero = "0";
		BeanUtils.copyProperties(cimsHistoryFin02, fin08b );
		fin08b.setId(null);
		fin08b.setCreatedDate(null);
		fin08b.setUpdatedDate(null);
		fin08b.setRemarks(null);
		System.out.println(fin08b.getMonth().length());
		if(fin08b.getMonth().length() == 1) {
			prvZero = "00";
		}
		fin08b.setCode("FIN08B" + fin08b.getClinicCode() + "-" + prvZero + fin08b.getMonth()+"-"+fin08b.getYear());
		fin08b.setStatus("IN INTERNAL APPROVAL");
		fin08b.setTotalMaintenanceCharges(0.0);
		fin08b.setTotalEquipmentCount(0);
		fin08bRepository.save(fin08b);
		cimsHistoryFin02List.forEach(cims -> {
			cims.setFin08bRefNo(fin08b.getCode());
			cims.setFin08bStatus(fin08b.getStatus());
			fin08b.setTotalMaintenanceCharges(fin08b.getTotalMaintenanceCharges() + cims.getMaintenanceCharges());
			fin08b.setTotalEquipmentCount(fin08b.getTotalEquipmentCount() + 1);
			cimsHistoryFin02Repository.save(cims);
		});
		fin08b.setTotalMaintenanceCharges(Double.valueOf(df.format(fin08b.getTotalMaintenanceCharges())));
		fin08b.setSubmittedUserId(userId);
		fin08b.setSubmittedDate(createdDate);
		fin08bRepository.save(fin08b);
		return fin08b;
	}
	
	public Fin08b getFin08b(Integer id) {
		Fin08b fin08b = fin08bRepository.findByFin08b(id);
		Comparator<CimsHistoryFin02> compareByBatchNumber = (CimsHistoryFin02 o1, CimsHistoryFin02 o2) -> o1.getAssetName().compareTo( o2.getAssetName() );
		Collections.sort(fin08b.getCimsHistoryFin02(), compareByBatchNumber);
		return fin08b;
	}
	
	public List<Fin08b> getFin08bInProgressListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08b> fin08bInProgressList = new ArrayList<Fin08b>();
		if(stateId == 0 && districtId == 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08bInProgressList.forEach(fin08b -> {
			fin08b.setCimsHistoryFin02(null);
		});
		return fin08bInProgressList;
	}
	
	public List<Fin08b> getFin08bInProgressListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08b> fin08bInProgressList = new ArrayList<Fin08b>();
		if(stateId == 0 && districtId == 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08bInProgressList = (List<Fin08b>) fin08bRepository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08bInProgressList.forEach(fin08b -> {
			fin08b.setCimsHistoryFin02(null);
		});
		return fin08bInProgressList;
	}
	
	public List<Fin08b> getFin08bApprovedListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08b> fin08bApprovedList = new ArrayList<Fin08b>();
		if(stateId == 0 && districtId == 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08bApprovedList.forEach(fin08b -> {
			fin08b.setCimsHistoryFin02(null);
		});
		return fin08bApprovedList;
	}
	
	public List<Fin08b> getFin08bApprovedListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08b> fin08bApprovedList = new ArrayList<Fin08b>();
		if(stateId == 0 && districtId == 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08bApprovedList = (List<Fin08b>) fin08bRepository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08bApprovedList.forEach(fin08b -> {
			fin08b.setCimsHistoryFin02(null);
		});
		return fin08bApprovedList;
	}
	
	public Fin08b updateFin08bStatusUsingChf2(Fin08b fin08b) {
		LocalDate date = LocalDate.now();
		if(fin08b.getStatus().equals("FOR APPROVAL TO MOH")) {
			fin08b.setApproval1Date(createdDate);			
		}else if(fin08b.getStatus().equals("APPROVED BY MOH")){
			fin08b.setApproval2Date(createdDate);
		}
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin02> cimsHistoryList = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
		List<CimsHistoryFin02> cimsHistoryFin02Fin08cCheck = cimsHistoryFin02Repository.findByClinicIdAndIsFin08cAndMonthAndYear(cimsHistoryList.get(0).getClinicId(),fin08b.getMonth(),String.valueOf(date.getYear()));
		cimsHistoryList.forEach(cims -> {
			cims.setFin08bStatus(fin08b.getStatus());
			if(cims.getFin08bStatus().equals("APPROVED BY MOH")) {
				if(cimsHistoryFin02Fin08cCheck.isEmpty()) {
					
				} else {
				if(cimsHistoryFin02Fin08cCheck.get(0).getFin08cStatus() == null || cimsHistoryFin02Fin08cCheck.get(0).getFin08cStatus().equals("IN INTERNAL APPROVAL") || cimsHistoryFin02Fin08cCheck.get(0).getFin08cStatus().equals("FOR APPROVAL TO MOH") ) {
					cims.setEquivalentFin08c("PENDING");
				} else if(cimsHistoryFin02Fin08cCheck.get(0).getFin08cStatus().equals("APPROVED BY MOH")) {
					cims.setEquivalentFin08c("PROCESSED");
				}
				}
			}
			cimsHistoryFin02Repository.save(cims);
		});
		if(cimsHistoryList.get(0).getFin08bStatus().equals("APPROVED BY MOH")) {
			if(cimsHistoryFin02Fin08cCheck.isEmpty()) {
				
			} else {
				cimsHistoryFin02Fin08cCheck.forEach(cims -> {
					cims.setEquivalentFin08b("PROCESSED");
					cimsHistoryFin02Repository.save(cims);
				});
			}
		}
		return fin08b;
	}
	
	public List<CimsHistoryFin02> getFin08cCreateListUsingChf2(Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndMonth(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndStateIdAndMonth(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndStateIdAndDistrictIdAndMonth(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getFin08cCreateListUsingChf2Older(Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndMonthNot(String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndStateIdAndMonthNot(stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02List = (List<CimsHistoryFin02>) cimsHistoryFin02Repository.findByFin08cStatusAndIsFin08cAndStateIdAndDistrictIdAndMonthNot(stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getEquipmentsForFin08cCreate(Integer clinicId, String month , String year ) {
		//LocalDate date = LocalDate.now();
		List<CimsHistoryFin02> equipList = cimsHistoryFin02Repository.findByClinicIdAndIsFin08cAndMonthAndYear(clinicId, String.valueOf(month), String.valueOf(year));
		return equipList;
	}
	
	public List<Fin08c> getFin08cInProgressListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08c> fin08cInProgressList = new ArrayList<Fin08c>();
		if(stateId == 0 && districtId == 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08cInProgressList.forEach(fin08c -> {
			fin08c.setCimsHistoryFin02(null);
		});
		return fin08cInProgressList;
	}
	
	public List<Fin08c> getFin08cInProgressListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08c> fin08cInProgressList = new ArrayList<Fin08c>();
		if(stateId == 0 && districtId == 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08cInProgressList = (List<Fin08c>) fin08cRepository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08cInProgressList.forEach(fin08c -> {
			fin08c.setCimsHistoryFin02(null);
		});
		return fin08cInProgressList;
	}
	
	public List<Fin08c> getFin08cApprovedListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08c> fin08cApprovedList = new ArrayList<Fin08c>();
		if(stateId == 0 && districtId == 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08cApprovedList.forEach(fin08c -> {
			fin08c.setCimsHistoryFin02(null);
		});
		return fin08cApprovedList;
	}
	
	public List<Fin08c> getFin08cApprovedListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08c> fin08cApprovedList = new ArrayList<Fin08c>();
		if(stateId == 0 && districtId == 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08cApprovedList = (List<Fin08c>) fin08cRepository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08cApprovedList.forEach(fin08c -> {
			fin08c.setCimsHistoryFin02(null);
		});
		return fin08cApprovedList;
	}
	
	public Fin08c createFin08cUsingChf2(List<CimsHistoryFin02> cimsHistoryFin02List,int userId) {
		CimsHistoryFin02 cimsHistoryFin02 = cimsHistoryFin02List.get(0);
		Fin08c fin08c = new Fin08c();
		String prvZero = "0";
		BeanUtils.copyProperties(cimsHistoryFin02, fin08c );
		fin08c.setId(null);
		fin08c.setCreatedDate(null);
		fin08c.setUpdatedDate(null);
		fin08c.setRemarks(null);
		System.out.println(fin08c.getMonth().length());
		if(fin08c.getMonth().length() == 1) {
			prvZero = "00";
		}
		fin08c.setCode("FIN08C" + fin08c.getClinicCode() + "-" + prvZero + fin08c.getMonth()+"-"+fin08c.getYear());
		fin08c.setStatus("IN INTERNAL APPROVAL");
		fin08c.setTotalMaintenanceCharges(0.0);
		fin08c.setTotalEquipmentCount(0);
		fin08cRepository.save(fin08c);
		cimsHistoryFin02List.forEach(cims -> {
			cims.setFin08cRefNo(fin08c.getCode());
			cims.setFin08cStatus(fin08c.getStatus());
			fin08c.setTotalMaintenanceCharges(fin08c.getTotalMaintenanceCharges() + cims.getMaintenanceCharges());
			fin08c.setTotalEquipmentCount(fin08c.getTotalEquipmentCount() + 1);
			cimsHistoryFin02Repository.save(cims);
		});
		fin08c.setTotalMaintenanceCharges(Double.valueOf(df.format(fin08c.getTotalMaintenanceCharges())));
		fin08c.setSubmittedUserId(userId);
		fin08c.setSubmittedDate(createdDate);
		fin08cRepository.save(fin08c);
		return fin08c;
	}
	
	public Fin08c getFin08c(Integer id) {
		Fin08c fin08c = fin08cRepository.findByFin08c(id);
		Comparator<CimsHistoryFin02> compareByBatchNumber = (CimsHistoryFin02 o1, CimsHistoryFin02 o2) -> o1.getAssetName().compareTo( o2.getAssetName() );
		Collections.sort(fin08c.getCimsHistoryFin02(), compareByBatchNumber);
		return fin08c;
	}
	
	public Fin08c updateFin08cStatusUsingChf2(Fin08c fin08c) {
		LocalDate date = LocalDate.now();
		if(fin08c.getStatus().equals("FOR APPROVAL TO MOH")) {
			fin08c.setApproval1Date(createdDate);			
		}else if(fin08c.getStatus().equals("APPROVED BY MOH")){
			fin08c.setApproval2Date(createdDate);
		}
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin02> cimsHistoryList = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
		List<CimsHistoryFin02> cimsHistoryFin02Fin08bCheck = cimsHistoryFin02Repository.findByClinicIdAndIsFin08bAndMonthAndYear(cimsHistoryList.get(0).getClinicId(),fin08c.getMonth(),String.valueOf(date.getYear()));
		cimsHistoryList.forEach(cims -> {
			cims.setFin08cStatus(fin08c.getStatus());
			if(cims.getFin08cStatus().equals("APPROVED BY MOH")) {
			if(cimsHistoryFin02Fin08bCheck.isEmpty()) {
				
			} else {
				if(cimsHistoryFin02Fin08bCheck.get(0).getFin08bStatus() == null || cimsHistoryFin02Fin08bCheck.get(0).getFin08bStatus().equals("IN INTERNAL APPROVAL") || cimsHistoryFin02Fin08bCheck.get(0).getFin08bStatus().equals("FOR APPROVAL TO MOH") ) {
					cims.setEquivalentFin08b("PENDING");
				} else if(cimsHistoryFin02Fin08bCheck.get(0).getFin08bStatus().equals("APPROVED BY MOH")) {
					cims.setEquivalentFin08b("PROCESSED");
				}
			}
			}
			cimsHistoryFin02Repository.save(cims);
		});
		if(cimsHistoryList.get(0).getFin08cStatus().equals("APPROVED BY MOH")) {
		if(cimsHistoryFin02Fin08bCheck.isEmpty()) {
			
		} else {
			cimsHistoryFin02Fin08bCheck.forEach(cims -> {
				cims.setEquivalentFin08c("PROCESSED");
				cimsHistoryFin02Repository.save(cims);
			});
		}
		}
		return fin08c;
	}
	
	public List<CimsHistoryFin02> getFin08CreateListUsingChf2(Integer stateId, Integer districtId){
		LocalDate createdDate = LocalDate.now();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08b = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08c = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);	
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId,districtId);
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId,districtId);
		}
		
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08b);
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08c);
		
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		//Comparator<CimsHistoryFin02> compareByClinicId = (CimsHistoryFin02 o1, CimsHistoryFin02 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		//Collections.sort(uniqueCimsHistoryFin02, compareByClinicId);
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getFin08CreateLisPendingListNotificaitontUsingChf2(Integer districtId){
		LocalDate createdDate = LocalDate.now();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08b = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08c = new ArrayList<CimsHistoryFin02>();
//		if(stateId == 0 && districtId == 0) {
//			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
//			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
//		} else if(stateId != 0 && districtId == 0) {
//			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);
//			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);	
//		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictIdPendingListNotification("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),districtId);
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthAndYearAndFin08StatusIsNullAndStateIdAndDistrictIdPendingListNotification("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),districtId);
//		}
		
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08b);
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08c);
		
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		//Comparator<CimsHistoryFin02> compareByClinicId = (CimsHistoryFin02 o1, CimsHistoryFin02 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		//Collections.sort(uniqueCimsHistoryFin02, compareByClinicId);
		return uniqueCimsHistoryFin02;
	}
	
	public List<CimsHistoryFin02> getFin08CreateListUsingChf2Older(Integer stateId, Integer districtId){
		LocalDate createdDate = LocalDate.now();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08b = new ArrayList<CimsHistoryFin02>();
		List<CimsHistoryFin02> cimsHistoryFin02ForFin08c = new ArrayList<CimsHistoryFin02>();
		if(stateId == 0 && districtId == 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNull("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()));
		} else if(stateId != 0 && districtId == 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNullAndStateId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId);	
		} else if(stateId != 0 && districtId != 0) {
			cimsHistoryFin02ForFin08b = cimsHistoryFin02Repository.findByFin08bStatusAndEquivalentFin08cAndMonthNotAndYearAndFin08StatusIsNullAndStateIdAndDistrictId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId,districtId);
			cimsHistoryFin02ForFin08c = cimsHistoryFin02Repository.findByFin08cStatusAndEquivalentFin08bAndMonthNotAndYearAndFin08StatusIsNullAndStateIdAndDistrictId("APPROVED BY MOH","PROCESSED",String.valueOf(createdDate.getMonthValue()-1),String.valueOf(createdDate.getYear()),stateId,districtId);
		}
		
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08b);
		cimsHistoryFin02List.addAll(cimsHistoryFin02ForFin08c);
		
		Set<CimsHistoryFin02Distinct> uniqueSet = new HashSet<>();
		List<CimsHistoryFin02> uniqueCimsHistoryFin02 = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List.forEach(cims -> {
		CimsHistoryFin02Distinct cimsTransactionUnique = new CimsHistoryFin02Distinct(cims.getStateId(),cims.getDistrictId(),cims.getClinicTypeId(),cims.getClinicId(),cims.getMonth(),cims.getYear());
		if(uniqueSet.add(cimsTransactionUnique)) {
			uniqueCimsHistoryFin02.add(cims);
		}
		});
		//Comparator<CimsHistoryFin02> compareByClinicId = (CimsHistoryFin02 o1, CimsHistoryFin02 o2) -> o1.getClinicId().compareTo( o2.getClinicId() );
		//Collections.sort(uniqueCimsHistoryFin02, compareByClinicId);
		return uniqueCimsHistoryFin02;
	}
	
	public List<Fin08> getFin08InProgressListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08> fin08InProgressList = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08InProgressList.forEach(fin08 -> {
			fin08.setFin08b(null);
			fin08.setFin08c(null);
		});
		return fin08InProgressList;
	}
	
	public List<Fin08> getFin08InProgressListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08> fin08InProgressList = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08InProgressList = (List<Fin08>) fin08Repository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08InProgressList.forEach(fin08 -> {
			fin08.setFin08b(null);
			fin08.setFin08c(null);
		});
		return fin08InProgressList;
	}
	
	public List<Fin08> getFin08ApprovedListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08> fin08ApprovedList = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08ApprovedList.forEach(fin08 -> {
			fin08.setFin08b(null);
			fin08.setFin08c(null);
		});
		return fin08ApprovedList;
	}
	
	public List<Fin08> getFin08ApprovedListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin08> fin08ApprovedList = new ArrayList<Fin08>();
		if(stateId == 0 && districtId == 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin08ApprovedList = (List<Fin08>) fin08Repository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin08ApprovedList.forEach(fin08 -> {
			fin08.setFin08b(null);
			fin08.setFin08c(null);
		});
		return fin08ApprovedList;
	}
	
	public Fin08 createFin08UsingChf2(int clinicId,String month,String year,int userId) {
		Fin08b fin08b = fin08bRepository.findByClinicIdAndMonthAndYear(clinicId,month,year);
		Fin08c fin08c = fin08cRepository.findByClinicIdAndMonthAndYear(clinicId,month,year);
		Fin08 fin08 = new Fin08();
		String prvZero = "0";
		if(fin08b != null) {
		BeanUtils.copyProperties(fin08b, fin08);
		}
		if(fin08c != null) {
		BeanUtils.copyProperties(fin08c, fin08);
		}
		fin08.setId(null);
		fin08.setCreatedDate(null);
		fin08.setUpdatedDate(null);
		fin08.setRemarks(null);
		fin08.setApproval1UserId(null);
		fin08.setApproval2UserId(null);
		fin08.setApproval1Date(null);
		fin08.setApproval2Date(null);
		fin08.setTotalMaintenanceCharges(0.0);
		if(fin08b != null) {
			fin08.setTotalMaintenanceCharges( fin08.getTotalMaintenanceCharges() + fin08b.getTotalMaintenanceCharges());
		}
		if(fin08c != null) {
			fin08.setTotalMaintenanceCharges( fin08.getTotalMaintenanceCharges() + fin08c.getTotalMaintenanceCharges());
		}
		fin08.setTotalMaintenanceCharges(Double.valueOf(df.format(fin08.getTotalMaintenanceCharges())));
		System.out.println(fin08.getMonth().length());
		if(fin08.getMonth().length() == 1) {
			prvZero = "00";
		}
		fin08.setCode("FIN08" + fin08.getClinicCode() + "-" + prvZero + fin08.getMonth()+"-"+fin08.getYear());
		fin08.setStatus("IN INTERNAL APPROVAL");
		fin08.setSubmittedUserId(userId);
		fin08.setSubmittedDate(createdDate);
		fin08Repository.save(fin08);
		if(fin08b != null) {
		fin08b.setFin08RefNo(fin08.getCode());
		fin08b.setFin08Status(fin08.getStatus());
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
		cimsHistoryFin02.forEach(cims -> {
			cims.setFin08RefNo(fin08.getCode());
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin02Repository.save(cims);
		});
		}
		if(fin08c != null) {
		fin08c.setFin08RefNo(fin08.getCode());
		fin08c.setFin08Status(fin08.getStatus());
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
		cimsHistoryFin02.forEach(cims -> {
			cims.setFin08RefNo(fin08.getCode());
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin02Repository.save(cims);
		});
		}
		return fin08;
	}
	
	public Fin08 updateFin08StatusUsingChf2(Fin08 fin08) {
		if(fin08.getStatus().equals("FOR APPROVAL TO MOH")) {
			fin08.setApproval1Date(createdDate);			
		}else if(fin08.getStatus().equals("APPROVED BY MOH")){
			fin08.setApproval2Date(createdDate);
		}
		fin08Repository.save(fin08);
		Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
		if(fin08b != null) {
		fin08b.setFin08Status(fin08.getStatus());
		fin08bRepository.save(fin08b);
		List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
		cimsHistoryFin02.forEach(cims -> {
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin02Repository.save(cims);
		});
		}
		Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
		if(fin08c != null) {
		fin08c.setFin08Status(fin08.getStatus());
		fin08cRepository.save(fin08c);
		List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
		cimsHistoryFin02.forEach(cims -> {
			cims.setFin08Status(fin08.getStatus());
			cimsHistoryFin02Repository.save(cims);
		});
		}
		return fin08;
	}
	
	public List<Fin03> getFin03InProgressListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03> fin03InProgressList = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03InProgressList.forEach(fin03 -> {
			fin03.setFin08(null);
		});
		return fin03InProgressList;
	}
	
	public List<Fin03> getFin03InProgressListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03> fin03InProgressList = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin03InProgressList = (List<Fin03>) fin03Repository.findByStatusNotAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03InProgressList.forEach(fin03 -> {
			fin03.setFin08(null);
		});
		return fin03InProgressList;
	}
	
	public List<Fin03> getFin03ApprovedListUsingChf2(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03> fin03ApprovedList = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndMonth(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndStateIdAndMonth(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndStateIdAndDistrictIdAndMonth(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03ApprovedList.forEach(fin03 -> {
			fin03.setFin08(null);
		});
		return fin03ApprovedList;
	}
	
	public List<Fin03> getFin03ApprovedListUsingChf2Older(String status,Integer stateId, Integer districtId){
		LocalDate date = LocalDate.now();
		List<Fin03> fin03ApprovedList = new ArrayList<Fin03>();
		if(stateId == 0 && districtId == 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndMonthNot(status,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId == 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndStateIdAndMonthNot(status,stateId,String.valueOf(date.getMonthValue()-1));
		} else if(stateId != 0 && districtId != 0) {
			fin03ApprovedList = (List<Fin03>) fin03Repository.findByStatusAndStateIdAndDistrictIdAndMonthNot(status,stateId,districtId,String.valueOf(date.getMonthValue()-1));
		}
		fin03ApprovedList.forEach(fin03 -> {
			fin03.setFin08(null);
		});
		return fin03ApprovedList;
	}
	
	public List<Fin08> getFin08ForFin03Create(Integer districtId, Integer clinicTypeId, String month, String year){
		LocalDate date = LocalDate.now();
		List<Fin08> fin08 = fin08Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNullAndMonthAndYear(districtId, clinicTypeId, "APPROVED BY MOH",
				String.valueOf(month), String.valueOf(year));
		Comparator<Fin08> compareByBatchNumber = (Fin08 o1, Fin08 o2) -> o1.getClinicCode().compareTo( o2.getClinicCode() );
		Collections.sort(fin08, compareByBatchNumber);
		return fin08;
	}
	
	public Fin03 createFin03UsingChf2(Integer districtId, Integer clinicTypeId, String month, String year, int userId,int backLog) {
		LocalDate date = LocalDate.now();
		String prvZero = "0";
	//	List<Fin03> fin03Check = fin03Repository.findByDistrictIdAndClinicTypeIdAndMonthAndYear(districtId, clinicTypeId, String.valueOf(month), String.valueOf(year));
		List<Fin08> fin08Datas = fin08Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNullAndMonthAndYear(districtId, clinicTypeId, "APPROVED BY MOH",
				String.valueOf(month), String.valueOf(year));
//		Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeIdAndStatusAndFin03StatusIsNull(districtId, 32);
//		final List<Fin09Clinic> fin09ClinicDatas ;
//		if(fin09!=null){
//			fin09ClinicDatas = fin09.getFin09Clinic();
//		}else {
//			fin09ClinicDatas = new ArrayList<Fin09Clinic>();
//		}
		Fin03 fin03 = new Fin03();
		if(fin08Datas.size()>0 ) {
			BeanUtils.copyProperties(fin08Datas.get(0), fin03 );
			fin03.setId(null);
			fin03.setCreatedDate(null);
			fin03.setUpdatedDate(null);
			fin03.setApproval1UserId(null);
			fin03.setApproval2UserId(null);
			fin03.setApproval1Date(null);
			fin03.setApproval2Date(null);
			fin03.setRemarks(null);
			CostCenterCode costCenterCode = costCenterCodeRepository.findByDistrictIdAndClinicTypeId(fin03.getDistrictId(), fin03.getClinicTypeId());
			if(fin03.getMonth().length() == 1) {
				prvZero = "00";
			}
			if(backLog == 0) {
				fin03.setCode("FIN03"+ fin03.getStateCode()+ costCenterCode.getCostCenterName() + "-" + prvZero +fin03.getMonth()+"-"+fin03.getYear());
			} else {
				fin03.setCode("FIN03"+ fin03.getStateCode()+ costCenterCode.getCostCenterName() + "-" + prvZero +fin03.getMonth()+"-"+fin03.getYear()+"-"+backLog);
			}
			fin03.setStatus("IN INTERNAL APPROVAL");
			fin03.setTotalMaintenanceCharges(0.0);
			fin03.setTotalPenaltyCharges(0.0);
			fin03.setNetMaintenanceCharges(0.0);
			fin03Repository.save(fin03);
			fin08Datas.forEach(fin08 -> {
				fin03.setTotalMaintenanceCharges(fin03.getTotalMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
//				Fin09Clinic fin09Clinic = new Fin09Clinic();
//					fin09Clinic = fin09Finder(fin09ClinicDatas,fin08.getClinicId());
//				if(fin09Clinic.getTotalPenalty() != null) {
//					fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges()  + (fin08.getTotalMaintenanceCharges() - fin09.getTotalPenalty()));
//					fin09Clinic.setFin03RefNo(fin03.getCode());
//					fin09Clinic.setFin03Status(fin03.getStatus());
//					fin09ClinicRepository.save(fin09Clinic);
//				} else {
					fin03.setNetMaintenanceCharges(fin03.getNetMaintenanceCharges() + fin08.getTotalMaintenanceCharges());
//				}
				fin08.setFin03RefNo(fin03.getCode());
				fin08.setFin03Status(fin03.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin03RefNo(fin03.getCode());
				fin08b.setFin03Status(fin03.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin02.forEach(cims ->{
					cims.setFin03RefNo(fin03.getCode());
					cims.setFin03Status(fin03.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin03RefNo(fin03.getCode());
				fin08c.setFin03Status(fin03.getStatus());
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin02.forEach(cims -> {
					cims.setFin03RefNo(fin03.getCode());
					cims.setFin03Status(fin03.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
			});
//			if(fin09!=null) {
//				fin03.setTotalPenaltyCharges(fin03.getTotalPenaltyCharges() + fin09.getTotalPenalty());
//				fin09.setFin03RefNo(fin03.getCode());
//				fin09.setFin03Status(fin03.getStatus());
//				fin09Repository.save(fin09);	
//			}
			fin03.setTotalMaintenanceCharges(Double.valueOf(df.format(fin03.getTotalMaintenanceCharges())));
			fin03.setTotalPenaltyCharges(Double.valueOf(df.format(fin03.getTotalPenaltyCharges())));
			fin03.setNetMaintenanceCharges(Double.valueOf(df.format(fin03.getNetMaintenanceCharges())));
			fin03.setSubmittedUserId(userId);
			fin03.setSubmittedDate(createdDate);
			fin03Repository.save(fin03);
		}
		return fin03;
		
		}
	
	public Fin03 getFin03(Integer id) {
		Fin03 fin03 = fin03Repository.findByFin03(id);
		Comparator<Fin08> compareByBatchNumber = (Fin08 o1, Fin08 o2) -> o1.getClinicCode().compareTo( o2.getClinicCode() );
		Collections.sort(fin03.getFin08(), compareByBatchNumber);
		return fin03;
	}
	
	public Fin03 updateFin03StatusUsingChf2(Fin03 fin03) {
		if(fin03.getStatus().equals("FOR APPROVAL TO MOH")) {
			fin03.setApproval1Date(createdDate);			
		}else if(fin03.getStatus().equals("APPROVED BY MOH")){
			fin03.setApproval2Date(createdDate);
		}
		List<Fin08> fin08Datas = fin08Repository.findByFin03RefNo(fin03.getCode());
		//Fin09 fin09 = fin09Repository.findByFin03RefNo(fin03.getCode());
		//final List<Fin09Clinic> fin09Clinic ;
		fin08Datas.forEach(fin08 -> {
			fin08.setFin03Status(fin03.getStatus());
			fin08Repository.save(fin08);
			Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
			Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
			if(fin08b != null) {
			fin08b.setFin03Status(fin03.getStatus());
			fin08bRepository.save(fin08b);
			List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
			cimsHistoryFin02.forEach(cims ->{
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin02Repository.save(cims);
			});
			}
			if(fin08c != null) {
			fin08c.setFin03Status(fin03.getStatus());
			fin08cRepository.save(fin08c);
			List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
			cimsHistoryFin02.forEach(cims -> {
				cims.setFin03Status(fin03.getStatus());
				cimsHistoryFin02Repository.save(cims);
			});
			}
		});
//		if(fin09!=null){
//			fin09Clinic = fin09.getFin09Clinic();
//			fin09.setFin03Status(fin03.getStatus());
//			fin09Repository.save(fin09);
//		}else {
//			fin09Clinic = new ArrayList<Fin09Clinic>();
//		}
//		fin09Clinic.forEach(fin09Cli -> {
//			fin09Cli.setFin03Status(fin03.getStatus());
//			fin09ClinicRepository.save(fin09Cli);
//		});
		
		fin03Repository.save(fin03);
		return fin03;
	}

	public Invoice addFin02InvoiceUsingChf2(List<Fin03> fin03,int userId) {
		Invoice invoice = new Invoice();
		Fin03 fin03Data = fin03.get(0);
		List<Invoice> invoiceCheck = invoiceRepository.findByInvoiceTypeIdAndDistrictIdAndClinicTypeIdAndMonthAndYear(3,fin03Data.getDistrictId(),
				fin03Data.getClinicTypeId(),fin03Data.getMonth(),fin03Data.getYear());
		//Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeId(fin03Data.getDistrictId(), 32);
		String clinicCatCode = "";
		if(fin03Data.getClinicTypeCode().equals("PKD")) { clinicCatCode = "K"; }
		else if(fin03Data.getClinicTypeCode().equals("PPD")) { clinicCatCode = "P"; }
		if(invoiceCheck.size() == 0) {
			invoice.setCode("2-"+fin03Data.getStateCode()+"-"+fin03Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin03Data.getMonth()+"-"+fin03Data.getYear());
		} else {
			invoice.setCode("2-"+fin03Data.getStateCode()+"-"+fin03Data.getDistrictCode()+"-"+clinicCatCode+"-"+fin03Data.getMonth()+"-"+fin03Data.getYear()+"-"+invoiceCheck.size());
		}
		invoice.setInvoiceNo(invoice.getCode());
		invoice.setInvoiceTypeId(3);
		invoice.setStateId(fin03Data.getStateId());
		invoice.setDistrictId(fin03Data.getDistrictId());
		invoice.setMonth(fin03Data.getMonth());
		invoice.setYear(fin03Data.getYear());
		invoice.setStatus("IN INTERNAL APPROVAL");
		invoice.setInvoiceBaseValue(0.0);
		invoiceRepository.save(invoice);
		fin03.forEach(fin03Sub -> {
			invoice.setInvoiceBaseValue(invoice.getInvoiceBaseValue()+fin03Sub.getNetMaintenanceCharges());
			fin03Sub.setFin02InvNo(invoice.getInvoiceNo());
			fin03Sub.setFin02InvStatus("IN INTERNAL APPROVAL");
			fin03Repository.save(fin03Sub);
			List<Fin08> fin08Data = fin08Repository.findByFin03RefNo(fin03Sub.getCode());
			fin08Data.forEach(fin08 -> {
				fin08.setFin02InvNo(invoice.getCode());
				fin08.setFin02InvStatus(invoice.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin02InvNo(invoice.getCode());
				fin08b.setFin02InvStatus(invoice.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin02.forEach(cims ->{
					cims.setFin02InvNo(invoice.getCode());
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin02InvNo(invoice.getCode());
				fin08c.setFin02InvStatus(invoice.getStatus());
				
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin02.forEach(cims -> {
					cims.setFin02InvNo(invoice.getCode());
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
			});
		});
//		if(fin09 != null) {
//			fin09.setFin02RefNo(invoice.getCode());
//			fin09.setFin02Status(invoice.getStatus());
//			fin09Repository.save(fin09);	
//		}
		invoice.setInvoiceBaseValue(Double.valueOf(df.format(invoice.getInvoiceBaseValue())));
		invoice.setSst(0.0);
		invoice.setRetentionAmount(0.0);
		invoice.setNetRetentionAmount(0.0);
		invoice.setDebitNoteEntry(0.0);
		InvoiceType invoiceType = invoiceTypeRepository.findByInvoiceType(3);
		if(invoiceType.getRetentionAvailable().equals("Y")) {
			df.setRoundingMode(RoundingMode.HALF_UP);
			invoice.setRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
			invoice.setNetRetentionAmount(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getRetentionPercentage()/100))));	
		}
		if(invoiceType.getSstIncluded().equals("Y")) {
			df.setRoundingMode(RoundingMode.HALF_UP);
			invoice.setSst(Double.valueOf(df.format(invoice.getInvoiceBaseValue()*(invoiceType.getSstPercentage()/100))));
		}
		invoice.setNetAfterSst(invoice.getInvoiceBaseValue()+invoice.getSst());
		df.setRoundingMode(RoundingMode.HALF_UP);
		invoice.setNetAfterSst(Double.valueOf(df.format(invoice.getNetAfterSst())));
		invoice.setTotalInvoiceValue(invoice.getNetAfterSst());
		invoice.setOutstandingAmount(invoice.getNetAfterSst());
		df.setRoundingMode(RoundingMode.HALF_UP);
		invoice.setTotalInvoiceValueWoRetention(Double.valueOf(df.format(invoice.getTotalInvoiceValue()-invoice.getRetentionAmount())));
		invoice.setPaymentStatus("PAYMENT-PENDING");
		invoice.setClinicTypeId(fin03Data.getClinicTypeId());
		invoice.setQuater(invoiceQuater(invoice.getMonth()));
		invoice.setSubmittedUserId(userId);
		invoice.setSubmittedDate(createdDate);
		invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Invoice updateFin02InvoiceStatusUsingChf2(Invoice invoice) {
		if(invoice.getStatus().equals("FOR APPROVAL TO MOH")) {
			invoice.setApproval1Date(createdDate);			
		}else if(invoice.getStatus().equals("APPROVED BY MOH")){
			invoice.setApproval2Date(createdDate);
		}
		invoiceRepository.save(invoice);
		List<Fin03> fin03 = fin03Repository.findByFin02InvNo(invoice.getInvoiceNo());
		//Fin09 fin09 = fin09Repository.findByDistrictIdAndClinicTypeId(invoice.getDistrictId(),invoice.getClinicTypeId());
		fin03.forEach( fin03Data -> {
			fin03Data.setFin02InvStatus(invoice.getStatus());
			fin03Repository.save(fin03Data);
			List<Fin08> fin08Data = fin08Repository.findByFin03RefNo(fin03Data.getCode());
			fin08Data.forEach(fin08 -> {
				fin08.setFin02InvStatus(invoice.getStatus());
				fin08Repository.save(fin08);
				Fin08b fin08b = fin08bRepository.findByFin08RefNo(fin08.getCode());
				Fin08c fin08c = fin08cRepository.findByFin08RefNo(fin08.getCode());
				if(fin08b != null) {
				fin08b.setFin02InvStatus(invoice.getStatus());
				fin08bRepository.save(fin08b);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08bRefNo(fin08b.getCode());
				cimsHistoryFin02.forEach(cims ->{
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
				if(fin08c != null) {
				fin08c.setFin02InvStatus(invoice.getStatus());
				fin08cRepository.save(fin08c);
				List<CimsHistoryFin02> cimsHistoryFin02 = cimsHistoryFin02Repository.findByFin08cRefNo(fin08c.getCode());
				cimsHistoryFin02.forEach(cims -> {
					cims.setFin02InvStatus(invoice.getStatus());
					cimsHistoryFin02Repository.save(cims);
				});
				}
			});
		});
//		if(fin09 != null) {
//			fin09.setFin02Status(invoice.getStatus());
//			fin09Repository.save(fin09);	
//		}
		return invoice;
	}

	public List<InvoiceGeneration> getInvoiceFin08bInitiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYear( month, year,stateId,districtId);
//		System.out.println(cimsHistoryFin02List);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
	
   public List<InvoiceGeneration> getInvoiceFin08bInprogress(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08bStatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
   
   public List<InvoiceGeneration> getInvoiceFin08bApproved(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08bStatus(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
	}
   
	
   public List<InvoiceGeneration> getInvoiceFin08cInitiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndIsFin08cCreated( month, year,stateId,districtId);
//		System.out.println(cimsHistoryFin02List);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
	
  public List<InvoiceGeneration> getInvoiceFin08cInprogress(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08cStatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
  
  public List<InvoiceGeneration> getInvoiceFin08cApproved(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
	   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08cStatus(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
	}
  
  
  
  public List<InvoiceGeneration> getInvoiceFin08Initiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndIsFin08Created( month, year,stateId,districtId);
//		System.out.println(cimsHistoryFin02List);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
	
public List<InvoiceGeneration> getInvoiceFin08Inprogress(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
public List<InvoiceGeneration> getInvoiceFin08Approved(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin08Status(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
	}
public List<InvoiceGeneration> getInvoiceFin03Initiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndIsFin03Created( month, year,stateId,districtId);
//		System.out.println(cimsHistoryFin02List);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
	
public List<InvoiceGeneration> getInvoiceFin03Inprogress(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin03StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
public List<InvoiceGeneration> getInvoiceFin03Approved(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin03Status(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
	}
public List<InvoiceGeneration> getInvoiceFin02Initiate(String month,String year,Integer stateId ,Integer districtId){
		if(stateId==0) {
			stateId = null;
		}
		if(districtId==0) {
			districtId = null;
		}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndIsFin02Created( month, year,stateId,districtId);
//		System.out.println(cimsHistoryFin02List);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
	
public List<InvoiceGeneration> getInvoiceFin02Inprogress(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
		List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin02StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);
		
	}
public List<InvoiceGeneration> getInvoiceFin02Approved(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
		cimsHistoryFin02List = cimsHistoryFin02Repository.findByMonthAndYearAndFin02Status(month, year,"APPROVED BY MOH",stateId,districtId);
		return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
	}


public List<InvoiceGeneration> getInvoiceFin08bInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYear( month, year,stateId,districtId);
//	System.out.println(cimsHistoryFin02List);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin08bInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08bStatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin08bApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08bStatus(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
}


public List<InvoiceGeneration> getInvoiceFin08cInitiateOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndIsFin08cCreated( month, year,stateId,districtId);
//	System.out.println(cimsHistoryFin02List);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin08cInprogressOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08cStatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin08cApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08cStatus(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
}



public List<InvoiceGeneration> getInvoiceFin08InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndIsFin08Created( month, year,stateId,districtId);
//	System.out.println(cimsHistoryFin02List);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin08InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}
public List<InvoiceGeneration> getInvoiceFin08ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin08Status(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
}
public List<InvoiceGeneration> getInvoiceFin03InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndIsFin03Created( month, year,stateId,districtId);
//	System.out.println(cimsHistoryFin02List);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin03InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin03StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}
public List<InvoiceGeneration> getInvoiceFin03ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin03Status(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
}
public List<InvoiceGeneration> getInvoiceFin02InitiateOlder(String month,String year,Integer stateId ,Integer districtId){
	if(stateId==0) {
		stateId = null;
	}
	if(districtId==0) {
		districtId = null;
	}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndIsFin02Created( month, year,stateId,districtId);
//	System.out.println(cimsHistoryFin02List);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}

public List<InvoiceGeneration> getInvoiceFin02InprogressOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
	List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin02StatusNot(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);
	
}
public List<InvoiceGeneration> getInvoiceFin02ApprovedOlder(String month,String year,Integer stateId ,Integer districtId){
if(stateId==0) {
	stateId = null;
}
if(districtId==0) {
	districtId = null;
}
   List<CimsHistoryFin02> cimsHistoryFin02List = new ArrayList<CimsHistoryFin02>();
	cimsHistoryFin02List = cimsHistoryFin02Repository.findByNotMonthAndYearAndFin02Status(month, year,"APPROVED BY MOH",stateId,districtId);
	return getUniqueInvoiceGeneration(cimsHistoryFin02List);		
}


   public List<InvoiceGeneration> getUniqueInvoiceGeneration(List<CimsHistoryFin02> cimsHistoryFin02List){
		List<InvoiceGeneration> invoiceGenerationList = new ArrayList<InvoiceGeneration>();
		Map<Integer, Double> amountList =cimsHistoryFin02List.stream().collect(Collectors.groupingBy(CimsHistoryFin02::getClinicId,Collectors. summingDouble(CimsHistoryFin02::getMaintenanceCharges)));
		
//		System.out.println(amountList);
		Map<Integer, Long> countList =cimsHistoryFin02List.stream().collect(Collectors.groupingBy(CimsHistoryFin02::getClinicId,Collectors.counting()));
		amountList.forEach((key,value)->{ 
				
				countList.forEach((key1,value1)->{ 
					
					if(key==key1) {
						Clinic clinic = clinicRepository.findByClinicId(key);	
//						System.out.println(key);
						var invoiceGenerationUnique = invoiceGenerationList.stream().filter(invGen-> clinic.getStateId() == invGen.getStateId() && clinic.getCircleId() == invGen.getCircleId() && clinic.getDistrictId() == invGen.getDistrictId()).findAny().orElse(null);
						if(invoiceGenerationUnique ==  null) {
							var invoiceGeneration = new InvoiceGeneration();
							invoiceGeneration.setStateId(clinic.getStateId());
							invoiceGeneration.setStateName(clinic.getStateName());
							invoiceGeneration.setCircleId(clinic.getCircleId());
							invoiceGeneration.setCircleName(clinic.getCircleName());
							invoiceGeneration.setDistrictId(clinic.getDistrictId());
							invoiceGeneration.setDistrictName(clinic.getDistrictName());
							invoiceGeneration.setClinicCount(1);
							invoiceGeneration.setEquipmentCount((int) (long)value1);
							invoiceGeneration.setAmount(value);
							invoiceGenerationList.add(invoiceGeneration);
						}else {
							
							invoiceGenerationUnique.setClinicCount(invoiceGenerationUnique.getClinicCount()+1);
							invoiceGenerationUnique.setEquipmentCount(invoiceGenerationUnique.getEquipmentCount()+(int) (long)value1);
							invoiceGenerationUnique.setAmount(invoiceGenerationUnique.getAmount()+value);
							invoiceGenerationList.stream().map(invGen-> (clinic.getStateId() == invGen.getStateId() && clinic.getCircleId() == invGen.getCircleId() && clinic.getDistrictId() == invGen.getDistrictId())? invoiceGenerationUnique : invGen).collect(Collectors.toList());
						}				
						
					}
				});
			});
	return invoiceGenerationList;
	}

}
