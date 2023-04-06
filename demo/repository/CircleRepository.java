package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Circle;

public interface CircleRepository extends CrudRepository<Circle, Integer> {
	List<Circle> findByStateId(Integer stateId);

}
