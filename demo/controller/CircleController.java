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

import com.example.demo.EntityModel.Circle;
import com.example.demo.repository.CircleRepository;

@RestController
@RequestMapping(path="/circle")
@CrossOrigin(origins = "*")
public class CircleController {
	
	@Autowired 
	CircleRepository circleRepository;
	
	@GetMapping("/all")
	public Iterable<Circle> getAllCircle(){
		return circleRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Circle> getCircle(@PathVariable(name = "id") int id){
		return circleRepository.findById(id);
	}
	

	@GetMapping("/state-id/{id}")
	public List<Circle> getCircleByStateId(@PathVariable(name = "id") Integer stateId){
		return circleRepository.findByStateId(stateId);
	}

	@PostMapping(path = "/add")
	public Circle addCircle(@RequestBody Circle Circle) {
		return circleRepository.save(Circle);
	}

	@PutMapping(path = "/update")
	public Circle updateCircle(@RequestBody Circle Circle) {
		return circleRepository.save(Circle);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCircle(@PathVariable(name = "id") int id) {
		circleRepository.deleteById(id);
	}

}
