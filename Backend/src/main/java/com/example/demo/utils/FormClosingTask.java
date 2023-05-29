package com.example.demo.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.FormService;

@Component
@Configuration
@EnableScheduling
public class FormClosingTask {

	private final FormService formService;

	public FormClosingTask(FormService formService) {
		this.formService = formService;
	}

	@Scheduled(cron = "0 0 0 * * *") // Runs daily at midnight
//	@Scheduled(cron = "*/10 * * * * *") // Runs every 10 seconds
	public void closeExpiredForms() {
		formService.closeForm();
	}
}