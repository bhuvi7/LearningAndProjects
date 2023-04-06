package com.example.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.UserRole;
import com.example.demo.repository.UserRoleRepository;

@RestController
@RequestMapping(path = "/user-role")
@CrossOrigin(origins = "*")
public class UserRoleController {

	private Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	@Autowired
	private UserRoleRepository userRoleRepository;

	@GetMapping(path = "/all")
	public Iterable<UserRole> getAllUserRole() {
		return userRoleRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserRole> getUserRoleById(@PathVariable(name = "id") int id) {
		return userRoleRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public UserRole addUserRoleById(@RequestBody UserRole UserRole) {
		return userRoleRepository.save(UserRole);
	}

	@PutMapping(path = "/update")
	public UserRole updateUserRoleById(@RequestBody UserRole UserRole) {
		return userRoleRepository.save(UserRole);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserRole(@PathVariable(name = "id") int id) {
		userRoleRepository.deleteById(id);
	}
}
