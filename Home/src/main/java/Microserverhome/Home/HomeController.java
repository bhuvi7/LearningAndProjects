package Microserverhome.Home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class HomeController {

	@Autowired
	private RestTemplate restTemplates;
	
//	@Autowired
//	private Employee employee;
	
	@GetMapping("/home")
	public String home() {
		return "ddd";
//				restTemplates.getForObject("http://PRODUCT-MICROSERVICE/product", String.class);
	}
	
//	@GetMapping("/getResponse")
//	public 	ResponseEntity
}
