package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/canva-api")
public class ApiController {

	@GetMapping("/hello-world")
	@ResponseBody
	public String sayHello() {
		return "HI";
	}
	
	@GetMapping("/canvas/login")
	public void canvasLogin(HttpServletResponse response) throws IOException {
	    String canvasLoginUrl = "https://absalon.ku.dk/login/oauth2/auth?" +
	            "client_id=<client_id>&" +
	            "response_type=code&" +
	            "state=<state>&" +
	            "redirect_uri=https://example.com/oauth2response";
	    response.sendRedirect(canvasLoginUrl);
	}

	@GetMapping("/oauth2response")
	public void canvasResponse(@RequestParam("code") String code,
	                            @RequestParam("state") String state,
	                            HttpServletResponse response) throws IOException {
	    // Use code, client ID, client secret, and redirect URI to obtain access token from Canvas API
	    String accessToken = "7500~E3BT9erhIrD6fzRbImlXhhyafZURsIv82pjWzGTiXTdBLIxMZ8pYvVqXAoMPwebA";
	    
	    // Redirect user to home page or dashboard with access token
	    response.sendRedirect("/home?access_token=" + accessToken);
	}
	
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/courses")
	public String getCourses(@RequestParam("access_token") String accessToken) {
		HttpHeaders headers = createAuthorizationHeader(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("https://absalon.ku.dk/api/v1/courses", HttpMethod.GET,
				entity, String.class);
		return response.getBody();
	}
	
	
	@GetMapping("/account")
	public String getAccountInfo(@RequestParam("access_token") String accessToken) {
	    HttpHeaders headers = createAuthorizationHeader(accessToken);
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    ResponseEntity<String> response = restTemplate.exchange("https://absalon.ku.dk/api/v1/account_calendars", HttpMethod.GET,
	            entity, String.class);
	    return response.getBody();
	}
	
	@GetMapping("/courses/{courseId}/students")
	public String getStudents(@RequestParam("access_token") String accessToken,
	                          @PathVariable("courseId") String courseId) {
	    HttpHeaders headers = createAuthorizationHeader(accessToken);
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(
	            "https://absalon.ku.dk/api/v1/courses/" + courseId + "/students",
	            HttpMethod.GET,
	            entity,
	            String.class
	    );
	    return response.getBody();
	}
	
//	@PostMapping("/courses/{courseId}/groups/{access_token}")
//	public ResponseEntity<String> createGroupForStudent(@PathVariable("courseId") String courseId,
//			@PathVariable("access_token") String accessToken,
//	                                                     @RequestBody GroupRequest groupRequest) {
//	    HttpHeaders headers = createAuthorizationHeader(accessToken);
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//
//	    HttpEntity<GroupRequest> request = new HttpEntity<>(groupRequest, headers);
//
//	    String url = String.format("https://absalon.ku.dk/api/v1/courses/%s/groups", courseId);
//	    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//
//	    return response;
//	}
	
//	public static class GroupRequest {
//	    private String name;
//	    private String description;
//	    private boolean is_public;
//	    private String join_level;
//	    private int max_membership;
//	    private int[] members;
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getDescription() {
//			return description;
//		}
//		public void setDescription(String description) {
//			this.description = description;
//		}
//		public boolean isIs_public() {
//			return is_public;
//		}
//		public void setIs_public(boolean is_public) {
//			this.is_public = is_public;
//		}
//		public String getJoin_level() {
//			return join_level;
//		}
//		public void setJoin_level(String join_level) {
//			this.join_level = join_level;
//		}
//		public int getMax_membership() {
//			return max_membership;
//		}
//		public void setMax_membership(int max_membership) {
//			this.max_membership = max_membership;
//		}
//		public int[] getMembers() {
//			return members;
//		}
//		public void setMembers(int[] members) {
//			this.members = members;
//		}
//	}

	public static HttpHeaders createAuthorizationHeader(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		return headers;
	}
}
