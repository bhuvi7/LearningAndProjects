package com.example.demo.EntityModel;

public class CimsHistoryFin02aDistinct {
	
	private Integer stateId;
	private Integer districtId;
	private Integer clinicTypeId;
	private Integer clinicId;
	private String fin07Status;
	private String month;
	private String year;
	
	
	
	
	
	 public CimsHistoryFin02aDistinct(Integer stateId, Integer districtId, Integer clinicTypeId, Integer clinicId,
			 String month, String year,String fin07Status) {
		super();
		this.stateId = stateId;
		this.districtId = districtId;
		this.clinicTypeId = clinicTypeId;
		this.clinicId = clinicId;
		this.fin07Status = fin07Status;
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



	public String getFin07Status() {
		return fin07Status;
	}



	public void setFin07Status(String fin07Status) {
		this.fin07Status = fin07Status;
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
		if (obj instanceof CimsHistoryFin02aDistinct) {
			CimsHistoryFin02aDistinct cm = (CimsHistoryFin02aDistinct) obj;
			
			return (cm.districtId.equals(this.districtId) && cm.clinicTypeId.equals(this.clinicTypeId) && cm.clinicId.equals(this.clinicId) && cm.month.equals(this.month)&& cm.year.equals(this.year) ); 
			
		} else {
			return false;
		}
	   }
	

}
