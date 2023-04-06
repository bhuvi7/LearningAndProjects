package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.EntityModel.Menu;


public interface MenuRepository extends CrudRepository<Menu, Integer> {

}
