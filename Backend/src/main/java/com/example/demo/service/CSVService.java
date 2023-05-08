package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.UserResponseDto;

/**
 * The Interface CSVService.
 */
public interface CSVService {
	
	/**
	 * Save.
	 *
	 * @param file the file
	 */
	public void save(MultipartFile file);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserResponseDto> getAllUsers();
}
