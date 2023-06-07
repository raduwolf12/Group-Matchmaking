package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.validation.exception.UserNotFoundException;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Creates the user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the response entity
	 */
	public UserResponseDto createUser(UserRequestDto userRequestDTO);

	/**
	 * Update user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the user response dto
	 * @throws UserNotFoundException the user not found exception
	 */
	public UserResponseDto updateUser(UserRequestDto userRequestDTO) throws UserNotFoundException;

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserResponseDto> getAllUsers();

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 * @throws UserNotFoundException the user not found exception
	 */
	public UserResponseDto getUserById(Long id) throws UserNotFoundException;

	/**
	 * Delete all users.
	 */
	public void deleteAllUsers();

	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 * @throws UserNotFoundException the user not found exception
	 */
	public void deleteUserById(Long id) throws UserNotFoundException;

	/**
	 * Gets the all students.
	 *
	 * @return the all students
	 */
	public List<UserResponseDto> getAllStudents();

}
