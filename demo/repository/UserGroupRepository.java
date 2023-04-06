package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.UserGroup;


public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {
  List<UserGroup> findByUserGroupMasterId(Integer userGroupMasterId);
  List<UserGroup> findByUserGroupMasterPriorityAndGroupPriority(Integer userGroupMasterPriority,Integer groupPriority);
  
  


}
