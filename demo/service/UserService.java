package com.example.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EntityModel.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User generateKeyForResetPassword(User user) {
		List<User> userList = userRepository.findByEmail(user.getEmail());
		User userData = new User();
		if(userList.isEmpty()) {
			
		} else {
		userData = userList.get(0);
		if(userData != null) {
		 int leftLimit = 48; // letter '0'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		 
		    String generatedKey = random.ints(leftLimit, rightLimit + 1)
		    	.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		    userData.setKeyForPasswordChange(generatedKey);
		    userRepository.save(userData);
		 TestMail testMail = new TestMail();
		 testMail.sendEmail(userData.getName(),userData.getEmail(),generatedKey);
		}
	}
		return userData;
	}
	
	public User resetPasswordByKey(User user) {
		User userData = userRepository.findByKeyForPasswordChange(user.getKeyForPasswordChange());
		if(userData != null) {
		userData.setPassword(user.getPassword());
		userData.setKeyForPasswordChange(null);
		userRepository.save(userData);
		}
		return userData;
	}

}
