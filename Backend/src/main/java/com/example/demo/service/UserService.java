package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.validation.exception.UserNotFoundException;

// TODO: Auto-generated Javadoc
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
	 * @throws UserNotFoundException 
	 */
	public UserResponseDto getUserById(Long id) throws UserNotFoundException;

}
