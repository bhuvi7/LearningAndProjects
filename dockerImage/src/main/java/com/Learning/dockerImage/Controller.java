package com.Learning.dockerImage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

	
	@GetMapping("/docker")
	public String getDocker() {
		
		return"Docker is working";
	}
}
