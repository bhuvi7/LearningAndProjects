package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.NumberingSequence;

public interface NumberingSequenceRepository extends CrudRepository<NumberingSequence, Integer> {

	
}
