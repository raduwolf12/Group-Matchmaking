package com.example.demo.utils;

import java.util.Random;

public class PasswordGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

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