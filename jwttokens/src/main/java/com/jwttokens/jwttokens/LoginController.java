package com.jwttokens.jwttokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class LoginController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody User user) {
	Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getName());
		}
		else {
			throw new UsernameNotFoundException("invalid user request !");
		}
		
	}
	
	public AuthenticationManager 
	
}
