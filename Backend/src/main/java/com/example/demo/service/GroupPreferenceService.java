package com.example.demo.service;

import com.example.demo.model.dto.GroupPreferenceRequestDto;
import com.example.demo.model.dto.GroupPreferenceResponseDto;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.UserNotFoundException;

public interface GroupPreferenceService {

	GroupPreferenceResponseDto setPreferences(GroupPreferenceRequestDto preferences) throws UserNotFoundException;

	GroupPreferenceResponseDto getPreferences(Long id) throws UserNotFoundException, GroupPreferenceException;

}
