package com.vinservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/vin")
@CrossOrigin
public class VinController {

	
	@Autowired
	private VinService vinService;
	
	@Autowired
	private VinRepository vinRepository;
	
	@RequestMapping("/tekton")
	public String hello()
	{
		return "tekton working";
	}
	
	@PostMapping(path = "/post-data")
	public Vin getData(@RequestBody Vin data){
		System.out.println(data);
		return vinService.addDetails(data);
	}
}
