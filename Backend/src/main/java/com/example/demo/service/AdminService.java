package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.FinalGroupResponseDto;

/**
 * The Interface AdminService.
 */
public interface AdminService {

	/**
	 * Form groups.
	 */
	void formGroups();

	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	List<FinalGroupResponseDto> getGroups();

}
