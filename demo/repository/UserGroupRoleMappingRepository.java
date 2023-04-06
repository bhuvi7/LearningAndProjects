package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.UserGroupRoleMapping;


public interface UserGroupRoleMappingRepository extends CrudRepository<UserGroupRoleMapping, Integer> {
	 List<UserGroupRoleMapping> findByUserGroupId(Integer userGroupId);
	 List<UserGroupRoleMapping> findByUserRoleId(Integer userRoleId);
}
