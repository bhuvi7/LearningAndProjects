package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.User;
import com.example.demo.EntityModel.UserDetail;
import com.example.demo.repository.UserDetailRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MailNotificationService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {

//	private Logger logger = LoggerFactory.getLogger(UserController.class);

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailNotificationService mailNotificationService;

//	@PostMapping(path = "/add") // Map ONLY POST Requests
//	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		logger.info("inside addUser");
//		User user = new User();
//		user.setName(name);
//		user.setEmail(email);
//		userRepository.save(user);
//		logger.info("inside addUser2");
//
//		try {
//			mailNotificationService.sendNotification(user);
//		} catch (Exception e) {
//			logger.error(e.toString());
//		}
//		return "Saved";
//	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users

		return userRepository.findAll();
	}

	@GetMapping(path = "/hello")
	public @ResponseBody String getHello() {
		// This returns a JSON or XML with the users

		return "Hello";
	}

	@PostMapping(path = "/login")
	public User login(@RequestBody User user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
	}

	@GetMapping(path = "/{id}")
	public Optional<User> getUserById(@PathVariable(name = "id") int id) {
		return userRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public User addUserById(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping(path = "/update")
	public User updateUserById(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping(path = "/delete/{id}")
	public User deleteUser(@PathVariable(name = "id") int id,@RequestBody User user) {
		userRepository.deleteById(id);
		user = null;
		return user;
	}
	
	@DeleteMapping(path = "/delete-user-detail/{id}")
	public void deleteUserDetail(@PathVariable(name = "id") int id) {
		userDetailRepository.deleteById(id);
	}

	@GetMapping(path = "/email/{email-id}")
	public List<User> getUserByEmailId(@PathVariable(name = "email-id") String emailId) {
		return userRepository.findByEmail(emailId);
	}
	
	@PostMapping(path = "/generate-key-to-reset-password")
	public User resetUserPassword(@RequestBody User user) {
		return userService.generateKeyForResetPassword(user);
	}
	
	@PutMapping(path = "/reset-password")
	public User resetPasswordByKey(@RequestBody User user) {
		return userService.resetPasswordByKey(user);	
	}
}
