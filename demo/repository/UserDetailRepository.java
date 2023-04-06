package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.UserDetail;


public interface UserDetailRepository extends CrudRepository<UserDetail, Integer> {
	List<UserDetail> findByUserGroupId(Integer userGroupId);


}
