package com.example.demo.service;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;

/**
 * The Interface EmailingService.
 */
public interface EmailingService {
    /** 
     * Sends an email
     * <p>
     * The function is blocking, so it will wait for the email to be sent before returning.
     * Therefore, errors will be thrown if the email failed to leave the server.
     * <p>
     * The email will be sent from the address specified in the application.properties file.
     * <p>
     * Usage example:
     * <pre>
     * <code>
     * &commat;Autowired
     * EmailingService emailingService;
     * void foo() {
     *  try {
     *     emailingService.sendEmail("bar@example.com", "A nice subject", "A nice body\nblah\nblah\nblah");
     *  } catch (MailException e) {
     *    // Handle the errors
     *  }
     * }
     * </code>
     * </pre>
     * 
     * @param to email adress to send the message to
     * @param subject the subject
     * @param body the text
     * 
     * @throws MailSendException if the email failed to send (might be because of a wrong adress as well as the connection to the SMTP server failed) | Inherit from {@link org.springframework.mail.MailException MailException}
     * @throws MailAuthenticationException if the authentication to the SMTP server failed | Also inherit from {@link org.springframework.mail.MailException MailException}
     */
    public void sendEmail(String to, String subject, String body) throws MailSendException, MailAuthenticationException;
}
