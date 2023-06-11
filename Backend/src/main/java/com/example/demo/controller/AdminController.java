package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.FinalGroupResponseDto;
import com.example.demo.model.entity.Configuration;
import com.example.demo.repository.ConfigurationRepository;
import com.example.demo.service.AdminService;

/**
 * The Class AdminController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {

	/** The admin service. */
	@Autowired
	AdminService adminService;

	/** The configuration repository. */
	@Autowired
	private ConfigurationRepository configurationRepository;

	/**
	 * Update settings.
	 *
	 * @param pairSize  the pair size
	 * @param groupSize the group size
	 * @return the response entity
	 */
	@PostMapping("/settings")
	public ResponseEntity<String> updateSettings(@RequestParam int pairSize, @RequestParam int groupSize) {

		Configuration configuration = configurationRepository.findById(1L).get();
		configuration.setPairSize(pairSize);
		configuration.setGroupSize(groupSize);

		configurationRepository.save(configuration);

		return ResponseEntity.ok("Settings updated successfully");
	}

	/**
	 * Gets the pair size.
	 *
	 * @return the pair size
	 */
	@GetMapping("/settings/pairSize")
	public ResponseEntity<Integer> getPairSize() {
		return ResponseEntity.ok(configurationRepository.findById(1L).get().getPairSize());
	}

	/**
	 * Gets the group size.
	 *
	 * @return the group size
	 */
	@GetMapping("/settings/groupSize")
	public ResponseEntity<Integer> getGroupSize() {
		return ResponseEntity.ok(configurationRepository.findById(1L).get().getGroupSize());
	}

	/**
	 * Form groups.
	 *
	 * @return the response entity
	 */
	@PostMapping("/start")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
	public ResponseEntity<String> formGroups() {
		try {
			adminService.formGroups();
			return new ResponseEntity<>("Project groups have been formed!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("A exception occured while forming groups, retry!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/final-groups")
	@PreAuthorize("hasAuthority ('PROFESSOR')")
	public ResponseEntity<List<FinalGroupResponseDto>> getGroups() {
		List<FinalGroupResponseDto> finalGroupResponseDtos = new ArrayList<FinalGroupResponseDto>();
		try {
			finalGroupResponseDtos = adminService.getGroups();
			return new ResponseEntity<>(finalGroupResponseDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
