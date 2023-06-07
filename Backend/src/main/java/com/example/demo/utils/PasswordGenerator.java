package com.example.demo.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * The Class PasswordGenerator.
 */
@Component
public class PasswordGenerator {
	
	/** The Constant CHARACTERS. */
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

	/**
	 * Generate random password.
	 *
	 * @param length the length
	 * @return the string
	 */
	public static String generateRandomPassword(int length) {
		Random random = new Random();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			password.append(CHARACTERS.charAt(randomIndex));
		}

		return password.toString();
	}
}