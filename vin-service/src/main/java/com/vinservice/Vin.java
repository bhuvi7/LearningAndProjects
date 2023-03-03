package com.vinservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vin implements Comparable<Vin> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vin_id;
	private String vin_description;
	private String vin_market;
	private String vin_catalog_id;
	

	@Override
	public int compareTo(Vin o) {
		// TODO Auto-generated method stub
		if(vin_id == o.vin_id)
		return 0;
		else if(vin_id > o.vin_id)
			return 1;
		else
			return -1;
	}
}
