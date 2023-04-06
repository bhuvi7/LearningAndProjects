package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.EntityModel.CammsBO;

public interface CammsBORepository extends CrudRepository<CammsBO, Integer> {
	
	@Query("FROM CammsBO WHERE fin_category = :fincategory AND is_migrated IS NULL AND beconditional_status IN ('ACT','PBR')")
	List<CammsBO> findByFinCategoryAndIsMigratedIsNull(String fincategory);
	
	List<CammsBO> findByFinCategory(String fincategory);
	@Query(" FROM CammsBO WHERE id = :id")
	CammsBO findByCammsBoId(Integer id);
	
	@Query("FROM CammsBO WHERE beconditional_status != 'ACT' AND ownership = 'Purchase Biomedical'")
	List<CammsBO> findByCammsBO();
	
}
