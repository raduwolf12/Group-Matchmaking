package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.validation.exception.UserNotFoundException;

/**
 * The Class UserServiceImpl.
 * 
 * @author Radu
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Creates the user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the user response dto
	 */
	public UserResponseDto createUser(UserRequestDto userRequestDTO) {

		User user = new User();
		BeanUtils.copyProperties(userRequestDTO, user);

		userRepository.save(user);

		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);

		return userResponseDto;
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@Override
	public List<UserResponseDto> getAllUsers() {

		List<User> users = userRepository.findAll();
		List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
		for (User user : users) {
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, userResponseDto);
			userResponseDtos.add(userResponseDto);
		}
		return userResponseDtos;
	}

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public UserResponseDto getUserById(Long id) throws UserNotFoundException {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + id);

		User user = optional.get();

		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);

		return userResponseDto;
	}

	/**
	 * Update user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the user response dto
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDTO) throws UserNotFoundException {

		Optional<User> optional = userRepository.findById(userRequestDTO.getUserId());

		if (optional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + userRequestDTO.getUserId());

		User user = optional.get();
		BeanUtils.copyProperties(userRequestDTO, user);

		userRepository.save(user);

		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);

		return userResponseDto;
	}

	/**
	 * Delete all users.
	 */
	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public void deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + id);

		User user = optional.get();

		userRepository.delete(user);
	}

	@Override
	public List<UserResponseDto> getAllStudents() {
		List<User> users = userRepository.findAll();
		List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
		for (User user : users) {
			if (user.getRole() == Role.STUDENT) {
	            UserResponseDto studentResponseDto = new UserResponseDto();
	            BeanUtils.copyProperties(user, studentResponseDto);
	            userResponseDtos.add(studentResponseDto);
	        }
		}
		return userResponseDtos;
	}

}
