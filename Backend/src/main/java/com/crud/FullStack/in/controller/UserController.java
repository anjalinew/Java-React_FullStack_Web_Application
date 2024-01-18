package com.crud.FullStack.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.FullStack.in.exception.UserNotFoundException;
import com.crud.FullStack.in.model.User;
import com.crud.FullStack.in.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	
	@GetMapping("/users")
		List<User> getAllUsers(){
			return userRepo.findAll();
		}
	
	@GetMapping("/user/{id}")
	User getUserId(@PathVariable long id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable long id) {
		return userRepo.findById(id).map(user ->{
			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setName(newUser.getName());
			return userRepo.save(user);
		}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		
		userRepo.deleteById(id);
		return "user with id" +id + "had been deleted";
	}
	
	

}
