package Microserverhome.Home;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateCommon {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplates = new RestTemplate();
		return restTemplates;
	};
}
