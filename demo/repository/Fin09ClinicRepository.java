package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.Fin09Clinic;

public interface Fin09ClinicRepository extends CrudRepository<Fin09Clinic, Integer> {

	List<Fin09Clinic> findByFin09RefNo(String fin09RefNo);
}
