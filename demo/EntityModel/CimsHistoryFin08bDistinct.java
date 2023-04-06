package com.example.demo.EntityModel;

public class CimsHistoryFin08bDistinct {
	
	private Integer stateId;
	private Integer districtId;
	private Integer clinicTypeId;
	private Integer clinicId;
	private String fin08bStatus;
	private String month;
	private String year;
	
	
	
	
	
	 public CimsHistoryFin08bDistinct(Integer stateId, Integer districtId, Integer clinicTypeId, Integer clinicId,
			 String month, String year,String fin08bStatus) {
		super();
		this.stateId = stateId;
		this.districtId = districtId;
		this.clinicTypeId = clinicTypeId;
		this.clinicId = clinicId;
		this.fin08bStatus = fin08bStatus;
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



	public String getFin08bStatus() {
		return fin08bStatus;
	}



	public void setFin08bStatus(String fin08bStatus) {
		this.fin08bStatus = fin08bStatus;
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
		if (obj instanceof CimsHistoryFin08bDistinct) {
			CimsHistoryFin08bDistinct cm = (CimsHistoryFin08bDistinct) obj;
			
			return (cm.districtId.equals(this.districtId) && cm.clinicTypeId.equals(this.clinicTypeId) && cm.clinicId.equals(this.clinicId) && cm.month.equals(this.month)&& cm.year.equals(this.year) ); 
			
		} else {
			return false;
		}
	   }
	

}
