package com.vinservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinRepository extends JpaRepository<Vin,Integer>{

}
