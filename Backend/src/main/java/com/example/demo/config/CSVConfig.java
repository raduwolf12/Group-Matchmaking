package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class CSVConfig {

	@Value("${passwordLength}")
	private int passwordLength;

	public static int getPasswordLength() {
		return getInstance().passwordLength;
	}

	private static CSVConfig instance;

	public static CSVConfig getInstance() {
		if (instance == null) {
			instance = new CSVConfig();
		}
		return instance;
	}
}
