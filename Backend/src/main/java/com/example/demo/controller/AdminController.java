package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.service.AdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

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

}
