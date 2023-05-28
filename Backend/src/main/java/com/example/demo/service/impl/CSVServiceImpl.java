package com.example.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.model.pojo.CSVHelper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CSVService;

/**
 * The Class CSVServiceImpl.
 */
@Service
public class CSVServiceImpl implements CSVService {
	
	/** The repository. */
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder encoder;
	

	/**
	 * Save.
	 *
	 * @param file the file
	 */
	public void save(MultipartFile file) {
		try {
			List<User> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			for(User user:tutorials) {
				user.setPassword(encoder.encode(user.getPassword()));
			}
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserResponseDto> getAllUsers() {
		List<User> users = repository.findAll();
		List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
		for (User user : users) {
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, userResponseDto);
			userResponseDtos.add(userResponseDto);
		}
		return userResponseDtos;
	}
}
