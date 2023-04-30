package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

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

}
