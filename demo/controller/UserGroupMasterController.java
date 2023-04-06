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

import com.example.demo.EntityModel.UserGroupMaster;
import com.example.demo.repository.UserGroupMasterRepository;

@RestController
@RequestMapping(path = "/user-group-master")
@CrossOrigin(origins = "*")
public class UserGroupMasterController {

	private Logger logger = LoggerFactory.getLogger(UserGroupMasterController.class);
	@Autowired
	private UserGroupMasterRepository userGroupMasterRepository;

	@GetMapping(path = "/all")
	public Iterable<UserGroupMaster> getAllUserGroupMaster() {
		return userGroupMasterRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserGroupMaster> getUserGroupMasterById(@PathVariable(name = "id") int id) {
		return userGroupMasterRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public UserGroupMaster addUserGroupMasterById(@RequestBody UserGroupMaster UserGroupMaster) {
		return userGroupMasterRepository.save(UserGroupMaster);
	}

	@PutMapping(path = "/update")
	public UserGroupMaster updateUserGroupMasterById(@RequestBody UserGroupMaster UserGroupMaster) {
		return userGroupMasterRepository.save(UserGroupMaster);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserGroupMaster(@PathVariable(name = "id") int id) {
		userGroupMasterRepository.deleteById(id);
	}
}
