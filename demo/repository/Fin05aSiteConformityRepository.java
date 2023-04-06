package com.example.demo.repository;

import com.example.demo.EntityModel.Fin05aSiteConformity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface Fin05aSiteConformityRepository extends CrudRepository<Fin05aSiteConformity, Integer> {
	
	List<Fin05aSiteConformity> findByFin05aInvNo(String fin05aInvNo);

}
