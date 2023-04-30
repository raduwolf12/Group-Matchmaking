package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class UserController.
 * 
 * @author Radu
 */
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
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDTO) {

		UserResponseDto userResponseDto;
		userResponseDto = userService.createUser(userRequestDTO);

		return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.ACCEPTED);

	}

	@GetMapping("/get/users")
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

	@GetMapping("/get/user/{id}")

	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) throws UserNotFoundException {
		
		UserResponseDto user = new UserResponseDto();
		try {
			user = userService.getUserById(id);

			
		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
