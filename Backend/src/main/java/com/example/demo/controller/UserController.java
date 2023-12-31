package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.service.UserService;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class UserController.
 * 
 * @author Radu
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	/** The user service. */
	@Autowired
	UserService userService;

	/**
	 * Creates the user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the response entity
	 */
	@PostMapping("/save/user")
	@PreAuthorize ("hasAuthority ('PROFESSOR')")
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDTO) {

		UserResponseDto userResponseDto;
		userResponseDto = userService.createUser(userRequestDTO);

		return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.ACCEPTED);

	}

	/**
	 * Update user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the response entity
	 */
	@PutMapping("/update/user")
	@PreAuthorize ("hasAuthority ('PROFESSOR')")
	public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto userRequestDTO) {
		try {
			UserResponseDto userResponseDto = userService.updateUser(userRequestDTO);

			return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GetMapping("/get/users")
	@PreAuthorize ("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		try {
			List<UserResponseDto> users = userService.getAllUsers();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get/students")
	@PreAuthorize ("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<List<UserResponseDto>> getAllStudents() {
		try {
			List<UserResponseDto> users = userService.getAllStudents();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 * @throws UserNotFoundException the user not found exception
	 */
	@GetMapping("/get/user/{id}")
	@PreAuthorize ("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) throws UserNotFoundException {

		UserResponseDto user = new UserResponseDto();
		try {
			user = userService.getUserById(id);

		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Removes the user by id.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws UserNotFoundException the user not found exception
	 */
	@DeleteMapping("/delete/user/{id}")
	@PreAuthorize ("hasAuthority ('PROFESSOR')")
	public ResponseEntity<UserResponseDto> removeUserById(@PathVariable Long id) throws UserNotFoundException {

		try {
			userService.deleteUserById(id);

		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	/**
	 * Removes the all users.
	 *
	 * @return the response entity
	 * @throws UserNotFoundException the user not found exception
	 */
	@DeleteMapping("/delete/users")
	@PreAuthorize ("hasAuthority ('PROFESSOR')")
	public ResponseEntity<UserResponseDto> removeAllUsers() throws UserNotFoundException {
		userService.deleteAllUsers();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
