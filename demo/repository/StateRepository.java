package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.District;
import com.example.demo.EntityModel.State;

public interface StateRepository extends CrudRepository<State, Integer> {
	@Query("FROM State WHERE id = :id")
	State findByStateIdOne(Integer id);
}
