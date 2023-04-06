package com.example.demo.EntityModel;

public class Fin10bDistinct {
	
	private Integer stateId;
	private Integer districtId;
	private Integer clinicTypeId;
	private String status;
//	private String month;
//	private String year;
//	
	
	
	
	
	 public Fin10bDistinct(Integer stateId, Integer districtId,Integer clinicTypeId, String status
			) {
		super();
		this.stateId = stateId;
		this.districtId = districtId;
		this.clinicTypeId = clinicTypeId;
		this.status = status;
//		this.month = month;
//		this.year = year;
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



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


//
//	public String getMonth() {
//		return month;
//	}
//
//
//
//	public void setMonth(String month) {
//		this.month = month;
//	}
//
//
//
//	public String getYear() {
//		return year;
//	}
//
//
//
//	public void setYear(String year) {
//		this.year = year;
//	}



	@Override
	   public int hashCode() {
	      return clinicTypeId;
	   }


	@Override
	   public boolean equals(Object obj) {
		if (obj instanceof Fin10bDistinct) {
			Fin10bDistinct cm = (Fin10bDistinct) obj;
			
			return (cm.districtId.equals(this.districtId) && cm.clinicTypeId.equals(this.clinicTypeId)); 
//			return (cm.districtId.equals(this.districtId) && cm.clinicTypeId.equals(this.clinicTypeId) && cm.month.equals(this.month)&& cm.year.equals(this.year) ); 

			
		} else {
			return false;
		}
	   }
	

}
