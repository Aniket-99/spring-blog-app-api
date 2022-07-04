package com.api.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.UserDto;
import com.api.blog.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		UserDto createdUserDto = this.userServiceImpl.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
		UserDto updatedUser = this.userServiceImpl.updateUser(userDto, userId);

		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
		this.userServiceImpl.deleteUser(userId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> allUsers = this.userServiceImpl.getAllUsers();

		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {
		UserDto user = this.userServiceImpl.getUserById(userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

}
