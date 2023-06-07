package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UserDetailsImpl;
import com.example.demo.model.pojo.AuthRequest;
import com.example.demo.model.pojo.AuthResponse;
import com.example.demo.utils.JwtUtils;

import jakarta.validation.Valid;

/**
 * The Class AuthController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	/** The authentication manager. */
	@Autowired
	AuthenticationManager authenticationManager;

	/** The jwt utils. */
	@Autowired
	JwtUtils jwtUtils;

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the response entity
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			return ResponseEntity.ok(new AuthResponse(userDetails.getEmail(),userDetails.getId(), jwt));
		} catch (BadCredentialsException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}