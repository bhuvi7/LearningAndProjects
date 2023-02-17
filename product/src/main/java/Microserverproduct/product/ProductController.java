package Microserverproduct.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("/product")
	public String product() {
		return"product";
	}
	
}
