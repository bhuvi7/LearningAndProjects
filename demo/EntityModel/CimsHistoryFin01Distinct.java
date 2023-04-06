package com.example.demo.EntityModel;

public class CimsHistoryFin01Distinct {
	
	private Integer stateId;
	private Integer districtId;
	private Integer clinicTypeId;
	private Integer clinicId;
	private String fin06Status;
	private String month;
	private String year;
	
	
	
	
	
	 public CimsHistoryFin01Distinct(Integer stateId, Integer districtId, Integer clinicTypeId, Integer clinicId,
			 String month, String year,String fin06Status) {
		super();
		this.stateId = stateId;
		this.districtId = districtId;
		this.clinicTypeId = clinicTypeId;
		this.clinicId = clinicId;
		this.fin06Status = fin06Status;
		this.month = month;
		this.year = year;
	}

	 

	public Integer getStateId() {
		return stateId;
	}



	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}



	public Integer getDistrictId() {
		return districtId;
	}



	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}



	public Integer getClinicTypeId() {
		return clinicTypeId;
	}



	public void setClinicTypeId(Integer clinicTypeId) {
		this.clinicTypeId = clinicTypeId;
	}



	public Integer getClinicId() {
		return clinicId;
	}



	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}



	public String getFin06Status() {
		return fin06Status;
	}



	public void setFin06Status(String fin06Status) {
		this.fin06Status = fin06Status;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	@Override
	   public int hashCode() {
	      return clinicId;
	   }


	@Override
	   public boolean equals(Object obj) {
		if (obj instanceof CimsHistoryFin01Distinct) {
			CimsHistoryFin01Distinct cm = (CimsHistoryFin01Distinct) obj;
			
			return (cm.districtId.equals(this.districtId) && cm.clinicTypeId.equals(this.clinicTypeId) && cm.clinicId.equals(this.clinicId) && cm.month.equals(this.month)&& cm.year.equals(this.year) ); 
			
		} else {
			return false;
		}
	   }
	

}
