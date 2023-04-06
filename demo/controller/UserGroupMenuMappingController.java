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

import com.example.demo.EntityModel.UserGroupMenuMapping;
import com.example.demo.repository.UserGroupMenuMappingRepository;

@RestController
@RequestMapping(path = "/user-group-menu-mapping")
@CrossOrigin(origins = "*")
public class UserGroupMenuMappingController {

	private Logger logger = LoggerFactory.getLogger(UserGroupMenuMappingController.class);
	@Autowired
	private UserGroupMenuMappingRepository userGroupMenuMappingRepository;

	@GetMapping(path = "/all")
	public Iterable<UserGroupMenuMapping> getAllUserGroupMenuMapping() {
		return userGroupMenuMappingRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserGroupMenuMapping> getUserGroupMenuMappingById(@PathVariable(name = "id") int id) {
		return userGroupMenuMappingRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public List<UserGroupMenuMapping> addUserGroupMenuMappingById(@RequestBody List<UserGroupMenuMapping> userGroupMenuMappingList) {
		userGroupMenuMappingList.forEach(userGroupMenuMapping->{userGroupMenuMappingRepository.save(userGroupMenuMapping);});
		return userGroupMenuMappingList;
	}

	@PutMapping(path = "/update")
	public UserGroupMenuMapping updateUserGroupMenuMappingById(@RequestBody UserGroupMenuMapping UserGroupMenuMapping) {
		return userGroupMenuMappingRepository.save(UserGroupMenuMapping);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserGroupMenuMapping(@PathVariable(name = "id") int id) {
		userGroupMenuMappingRepository.deleteById(id);
	}
	

	@PutMapping(path = "/delete")
	public void deleteAllUserGroupMenuMapping(@RequestBody List<UserGroupMenuMapping> userGroupMenuMappingList) {
		userGroupMenuMappingList.forEach(mapping->{
			System.out.println(mapping);
			userGroupMenuMappingRepository.deleteById(mapping.getId());
		});
	}
	
	@GetMapping(path = "/group-id/{id}")
	public List<UserGroupMenuMapping> getUserGroupMenuMappingByGroupId(@PathVariable(name = "id") int id) {
		return userGroupMenuMappingRepository.findByUserGroupId(id);
	}
	
}
