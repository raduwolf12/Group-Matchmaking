package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.service.EmailingService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/form")
public class FormController {

	@Autowired
	EmailingService emailingService;

	@Autowired
	UserService userService;

	@GetMapping("/open")
	public ResponseEntity<String> openForm() {
		List<UserResponseDto> students = userService.getAllUsers().stream()
				.filter(user -> user.getRole() == Role.STUDENT).collect(Collectors.toList());
		if (students.isEmpty()) {
			return new ResponseEntity<>("No students!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			for (UserResponseDto student : students) {
				emailingService.sendEmail(student.getEmail(), "Form credentials",
						"Welcome to the matchmaking app!\nThese are the login data\nE-mail: " + student.getEmail()
								+ "\nPassword: " + student.getPassword());
			}

			return new ResponseEntity<>("Student form is open, and all the students were notified!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("A exception occured while sending emails, resend the emails!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/close")
	public ResponseEntity<String> closeForm() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
