package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchmakingApplicationTests {

	@Test
	void contextLoads() {
		// If this test fails, it means that the application context cannot be loaded.
		// This is a good way to check if the application context is correctly configured.
		// This test is not dependent on the codebase, so if it fails, the other result are not reliable.
		
		// The most common cause of this test failing is missing the database
		// an easy way to fix this is to run the following command in the terminal:
		// docker run -p 5432:5432 -e POSTGRES_USER=compose-postgres -e POSTGRES_PASSWORD=compose-postgres postgres:13.1-alpine
	}

}
