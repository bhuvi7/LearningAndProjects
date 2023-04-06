package com.example.demo.controller;

import java.util.List;
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

import com.example.demo.EntityModel.UserDetail;
import com.example.demo.EntityModel.UserGroupRoleMapping;
import com.example.demo.repository.UserDetailRepository;

@RestController
@RequestMapping(path = "/user-detail")
@CrossOrigin(origins = "*")
public class UserDetailController {

	private Logger logger = LoggerFactory.getLogger(UserDetailController.class);
	@Autowired
	private UserDetailRepository userDetailRepository;

	@GetMapping(path = "/all")
	public Iterable<UserDetail> getAllUserDetail() {
		return userDetailRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserDetail> getUserDetailById(@PathVariable(name = "id") int id) {
		return userDetailRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public UserDetail addUserDetailById(@RequestBody UserDetail UserDetail) {
		return userDetailRepository.save(UserDetail);
	}

	@PutMapping(path = "/update")
	public UserDetail updateUserDetailById(@RequestBody UserDetail UserDetail) {
		return userDetailRepository.save(UserDetail);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserDetail(@PathVariable(name = "id") int id) {
		userDetailRepository.deleteById(id);
	}
	
	@GetMapping(path = "/group-id/{id}")
	public List<UserDetail> getUserDetailByGroupId(@PathVariable(name = "id") int id) {
		return userDetailRepository.findByUserGroupId(id);
	}
}
