package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApiController {

	@GetMapping("/hello-world")
	@ResponseBody
	public String sayHello() {
		return "HI";
	}
	
//	@GetMapping("/canvas-login")
//	public ResponseEntity<Void> canvasLogin() {
//	    String clientId = "YOUR_CLIENT_ID";
//	    String state = "YOUR_STATE";
//	    String redirectUri = "YOUR_REDIRECT_URI";
//	    String url = "https://<canvas-install-url>/login/oauth2/auth?client_id=" + clientId + "&response_type=code&state=" + state + "&redirect_uri=" + redirectUri;
//	    return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, url).build();
//	}
//	
//	@GetMapping("/oauth2response")
//	public ResponseEntity<Void> canvasOAuth2Response(@RequestParam("code") String code, @RequestParam("state") String state) {
//	    // Use the code and state to obtain the final access token using a POST request to login/oauth2/token endpoint
//	    // Return a response based on whether the token was obtained successfully or not
//	}
//	
//	
	@PostMapping("/canvas-access-token")
	public ResponseEntity<String> canvasAccessToken(@RequestParam("code") String code, @RequestParam(value = "replace_tokens", required = false) String replaceTokens) {
		RestTemplate restTemplate = new RestTemplate();

		String grantType = "authorization_code";
	    String clientId = "YOUR_CLIENT_ID";
	    String clientSecret = "YOUR_CLIENT_SECRET";
	    String redirectUri = "YOUR_REDIRECT_URI";
	    String url = "https://<canvas-install-url>/login/oauth2/token";
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", grantType);
	    params.add("client_id", clientId);
	    params.add("client_secret", clientSecret);
	    params.add("redirect_uri", redirectUri);
	    params.add("code", code);
	    if (replaceTokens != null && replaceTokens.equals("1")) {
	        params.add("replace_tokens", "1");
	    }
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	    return response;
	}
	
	@GetMapping("/canvas-courses")
	public ResponseEntity<String> canvasCourses(@RequestHeader("Authorization") String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://canvas.instructure.com/api/v1/courses";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(accessToken);
	    HttpEntity<Void> request = new HttpEntity<>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	    return response;
	}
	

}
