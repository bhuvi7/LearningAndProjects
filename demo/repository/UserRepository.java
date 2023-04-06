package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUserNameAndPassword(String userName,String password);
	List<User> findByEmail(String email);
	User findByKeyForPasswordChange(String keyForPasswordChange);


}
