package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.service.EmailingService;
import com.example.demo.service.FormService;
import com.example.demo.service.UserService;

/**
 * The Class FormController.
 * 
 * @author Radu
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/form")
public class FormController {

	/** The emailing service. */
	@Autowired
	EmailingService emailingService;

	/** The user service. */
	@Autowired
	UserService userService;

	/** The form service. */
	@Autowired
	FormService formService;

	/**
	 * Open form.
	 *
	 * @param duration the duration
	 * @return the response entity
	 */
	@PostMapping("/open")
	@PreAuthorize ("hasAuthority ('PROFESSOR')")
	public ResponseEntity<String> openForm(@RequestParam("duration") int duration) {

		formService.createForm(duration);
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

	/**
	 * Close form.
	 *
	 * @return the response entity
	 */
	@GetMapping("/close")
	public ResponseEntity<String> closeForm() {

		formService.closeFormManualy();

		return new ResponseEntity<>("Form was closed manualy!", HttpStatus.OK);
	}

	/**
	 * Gets the form status.
	 *
	 * @return the form status
	 */
	@GetMapping("/status")
	public ResponseEntity<String> getFormStatus() {
		boolean isOpen = formService.isFormOpen();
		String status = isOpen ? "Open" : "Closed";
		return ResponseEntity.ok(status);
	}

}
