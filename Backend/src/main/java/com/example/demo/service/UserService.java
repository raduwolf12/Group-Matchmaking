package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;

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

	public List<UserResponseDto> getAllUsers();

}
