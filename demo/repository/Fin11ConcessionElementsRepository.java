package com.example.demo.repository;

import com.example.demo.EntityModel.Fin11ConcessionElements;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//
public interface Fin11ConcessionElementsRepository extends CrudRepository<Fin11ConcessionElements, Integer> {
	
	List<Fin11ConcessionElements> findByFin11InvNo(String fin11RefNo);

}
