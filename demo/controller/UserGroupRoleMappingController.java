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

import com.example.demo.EntityModel.UserGroupRoleMapping;
import com.example.demo.repository.UserGroupRoleMappingRepository;

@RestController
@RequestMapping(path = "/user-group-role-mapping")
@CrossOrigin(origins = "*")
public class UserGroupRoleMappingController {

	private Logger logger = LoggerFactory.getLogger(UserGroupRoleMappingController.class);
	@Autowired
	private UserGroupRoleMappingRepository userGroupRoleMappingRepository;

	@GetMapping(path = "/all")
	public Iterable<UserGroupRoleMapping> getAllUserGroupRoleMapping() {
		return userGroupRoleMappingRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserGroupRoleMapping> getUserGroupRoleMappingById(@PathVariable(name = "id") int id) {
		return userGroupRoleMappingRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public UserGroupRoleMapping addUserGroupRoleMappingById(@RequestBody UserGroupRoleMapping UserGroupRoleMapping) {
		return userGroupRoleMappingRepository.save(UserGroupRoleMapping);
	}

	@PutMapping(path = "/update")
	public UserGroupRoleMapping updateUserGroupRoleMappingById(@RequestBody UserGroupRoleMapping UserGroupRoleMapping) {
		return userGroupRoleMappingRepository.save(UserGroupRoleMapping);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserGroupRoleMapping(@PathVariable(name = "id") int id) {
		userGroupRoleMappingRepository.deleteById(id);
	}
	
	@GetMapping(path = "/group-id/{id}")
	public List<UserGroupRoleMapping> getUserGroupRoleMappingByGroupId(@PathVariable(name = "id") int id) {
		return userGroupRoleMappingRepository.findByUserGroupId(id);
	}
	
	@GetMapping(path = "/role-id/{id}")
	public List<UserGroupRoleMapping> getUserGroupRoleMappingByRoleId(@PathVariable(name = "id") int id) {
		return userGroupRoleMappingRepository.findByUserRoleId(id);
	}
}
