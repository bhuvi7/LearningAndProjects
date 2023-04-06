package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityModel.Menu;
import com.example.demo.repository.MenuRepository;

@RestController
@RequestMapping(path="/menu")
@CrossOrigin(origins = "*")
public class MenuController {
	
	@Autowired 
	MenuRepository menuRepository;
	
	@GetMapping("/all")
	public Iterable<Menu> getAllMenu(){
		return menuRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Menu> getMenu(@PathVariable(name = "id") int id){
		return menuRepository.findById(id);
	}
	


	@PostMapping(path = "/add")
	public Menu addMenu(@RequestBody Menu Menu) {
		return menuRepository.save(Menu);
	}

	@PutMapping(path = "/update")
	public Menu updateMenu(@RequestBody Menu Menu) {
		return menuRepository.save(Menu);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteMenu(@PathVariable(name = "id") int id) {
		menuRepository.deleteById(id);
	}

}
