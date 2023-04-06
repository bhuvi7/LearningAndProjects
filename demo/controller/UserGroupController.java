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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.UserGroup;
import com.example.demo.EntityModel.UserGroupRoleMapping;
import com.example.demo.repository.UserGroupRepository;

@RestController
@RequestMapping(path = "/user-group")
@CrossOrigin(origins = "*")
public class UserGroupController {

	private Logger logger = LoggerFactory.getLogger(UserGroupController.class);
	@Autowired
	private UserGroupRepository userGroupRepository;

	@GetMapping(path = "/all")
	public Iterable<UserGroup> getAllUserGroup() {
		return userGroupRepository.findAll();
	}
	@GetMapping(path = "/{id}")
	public Optional<UserGroup> getUserGroupById(@PathVariable(name = "id") int id) {
		return userGroupRepository.findById(id);
	}

	@PostMapping(path = "/add")
	public UserGroup addUserGroupById(@RequestBody UserGroup UserGroup) {
		System.out.println(UserGroup);
		return userGroupRepository.save(UserGroup);
	}

	@PutMapping(path = "/update")
	public UserGroup updateUserGroupById(@RequestBody UserGroup UserGroup) {
		return userGroupRepository.save(UserGroup);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteUserGroup(@PathVariable(name = "id") int id) {
		userGroupRepository.deleteById(id);
	}
	
	@GetMapping(path = "/user-group-master/{id}")
	public List<UserGroup> getUserGroupByMasterId(@PathVariable(name = "id") int id) {
		return userGroupRepository.findByUserGroupMasterId(id);
	}
	
	@GetMapping(path = "/user-group-priority")
	public List<UserGroup> getUserGroupPriority(@RequestParam(name = "master-priority-id") int masterPriorityId,@RequestParam(name = "group-priority-id") int qroupPriorityId) {
		return userGroupRepository.findByUserGroupMasterPriorityAndGroupPriority(masterPriorityId,qroupPriorityId);
	}
	@PutMapping(path = "/update-priority")
	public Iterable<UserGroup> updateUserGroupPriority(@RequestBody List<UserGroup> userGroupList) {
		return userGroupRepository.saveAll(userGroupList);
	}
}
