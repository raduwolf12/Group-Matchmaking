package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class ApiController.
 */
@RestController
@RequestMapping("/canva-api")
public class ApiController {

	/**
	 * Canvas login.
	 *
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping("/canvas/login")
	public void canvasLogin(HttpServletResponse response) throws IOException {
		String canvasLoginUrl = "https://absalon.ku.dk/login/oauth2/auth?" + "client_id=<client_id>&"
				+ "response_type=code&" + "state=<state>&" + "redirect_uri=https://example.com/oauth2response";
		response.sendRedirect(canvasLoginUrl);
	}

	/**
	 * Canvas response.
	 *
	 * @param code     the code
	 * @param state    the state
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping("/oauth2response")
	public void canvasResponse(@RequestParam("code") String code, @RequestParam("state") String state,
			HttpServletResponse response) throws IOException {
		// Use code, client ID, client secret, and redirect URI to obtain access token
		// from Canvas API
		String accessToken = "7500~E3BT9erhIrD6fzRbImlXhhyafZURsIv82pjWzGTiXTdBLIxMZ8pYvVqXAoMPwebA";

		// Redirect user to home page or dashboard with access token
		response.sendRedirect("/home?access_token=" + accessToken);
	}

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets the courses.
	 *
	 * @param accessToken the access token
	 * @return the courses
	 */
	@GetMapping("/courses")
	public String getCourses(@RequestParam("access_token") String accessToken) {
		HttpHeaders headers = createAuthorizationHeader(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("https://absalon.ku.dk/api/v1/courses", HttpMethod.GET,
				entity, String.class);
		return response.getBody();
	}

	/**
	 * Gets the account info.
	 *
	 * @param accessToken the access token
	 * @return the account info
	 */
	@GetMapping("/account")
	public String getAccountInfo(@RequestParam("access_token") String accessToken) {
		HttpHeaders headers = createAuthorizationHeader(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("https://absalon.ku.dk/api/v1/account_calendars",
				HttpMethod.GET, entity, String.class);
		return response.getBody();
	}

	/**
	 * Gets the students.
	 *
	 * @param accessToken the access token
	 * @param courseId    the course id
	 * @return the students
	 */
	@GetMapping("/courses/{courseId}/students")
	public String getStudents(@RequestParam("access_token") String accessToken,
			@PathVariable("courseId") String courseId) {
		HttpHeaders headers = createAuthorizationHeader(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"https://absalon.ku.dk/api/v1/courses/" + courseId + "/students", HttpMethod.GET, entity, String.class);
		return response.getBody();
	}

	/**
	 * Creates the authorization header.
	 *
	 * @param accessToken the access token
	 * @return the http headers
	 */
	public static HttpHeaders createAuthorizationHeader(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		return headers;
	}
}
