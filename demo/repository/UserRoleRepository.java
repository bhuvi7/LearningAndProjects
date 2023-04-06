package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.UserRole;


public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

}
