package com.vinservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class VinService {

	@Autowired(required = false)
	private Vin vin;
	
	@Autowired(required = false)
	private VinRepository vinRepository;
	
	public Vin addDetails(Vin a){
	
	vinRepository.save(a);
	return vin;
	}
}
