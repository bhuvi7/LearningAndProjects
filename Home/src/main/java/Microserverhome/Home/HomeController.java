package Microserverhome.Home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

	@Autowired
	private RestTemplate restTemplates;
	
	@GetMapping("/home")
	public String home() {
		return restTemplates.getForObject("http://localhost:8082/product", String.class);
	}
}
