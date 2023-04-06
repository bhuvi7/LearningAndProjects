package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.UserGroupMenuMapping;


public interface UserGroupMenuMappingRepository extends CrudRepository<UserGroupMenuMapping, Integer> {
	 List<UserGroupMenuMapping> findByUserGroupId(Integer userGroupId);
}
