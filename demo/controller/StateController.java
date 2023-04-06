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

import com.example.demo.EntityModel.State;
import com.example.demo.repository.StateRepository;

@RestController
@RequestMapping(path="/state")
@CrossOrigin(origins = "*")
public class StateController {
	
	@Autowired 
	StateRepository stateRepository;
	
	@GetMapping("/all")
	public Iterable<State> getAllState(){
		return stateRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<State> getState(@PathVariable(name = "id") int id){
		return stateRepository.findById(id);
	}
	

	@PostMapping(path = "/add")
	public State addState(@RequestBody State state) {
		return stateRepository.save(state);
	}

	@PutMapping(path = "/update")
	public State updateState(@RequestBody State state) {
		return stateRepository.save(state);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteState(@PathVariable(name = "id") int id) {
		stateRepository.deleteById(id);
	}

}
