package com.example.demo.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.service.EmailingService;

import jakarta.mail.internet.InternetAddress;

/**
 * The Class EmailingServiceImpl.
 */
@Service
public class EmailingServiceImpl implements EmailingService {

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/** The from. */
	@Value("${spring.mail.username}")
	private String from;

	/** The pretty name. */
	@Value("${spring.mail.prettyName}")
	private String prettyName;

	/**
	 * Send email.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param body the body
	 * @throws MailSendException the mail send exception
	 * @throws MailAuthenticationException the mail authentication exception
	 */
	@Override
	public void sendEmail(String to, String subject, String body)
			throws MailSendException, MailAuthenticationException {
		SimpleMailMessage message = new SimpleMailMessage();

		try {
			message.setFrom(new InternetAddress(from, prettyName).toUnicodeString());
		} catch (UnsupportedEncodingException e) {
			message.setFrom(from);
		}
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}
}
