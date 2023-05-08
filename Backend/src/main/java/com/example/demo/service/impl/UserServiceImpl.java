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

	@Autowired
	UserRepository userRepository;

	public UserResponseDto createUser(UserRequestDto userRequestDTO) {

		User user = new User();
		BeanUtils.copyProperties(userRequestDTO, user);

		userRepository.save(user);

		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);

		return userResponseDto;
	}

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

}
