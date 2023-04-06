package com.example.demo.repository;

 

import java.util.List;

 

import org.springframework.data.repository.CrudRepository;

 

import com.example.demo.EntityModel.ConstructionNumberingSequence;

 

public interface ConstructionNumberingSequenceRepository extends CrudRepository<ConstructionNumberingSequence, Integer> {

 

    List<ConstructionNumberingSequence> findAll();
    
    ConstructionNumberingSequence findByStateIdAndClinicTypeId(Integer stateId, Integer clinicTypeId);

 

}
