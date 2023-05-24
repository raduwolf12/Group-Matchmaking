package com.example.demo.validation.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTimel(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Invalid data " + ex.getMessage());

		for (FieldError fieldError : errors) {
			validationErrorResponse.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		logger.error(ex.getMessage());

		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(ConstraintViolationException ex) {

		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTimel(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Invalid data " + ex.getMessage());

		ex.getConstraintViolations().forEach(error -> {
			validationErrorResponse.getErrors().put("field", error.getMessage());
		});
		logger.error(ex.getMessage());

		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(UserNotFoundException ex) {

		ErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setDateTimel(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("Invalid data " + ex.getMessage());
		logger.error(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ProjectNotFoundException ex) {

		ErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setDateTimel(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("Invalid data " + ex.getMessage());
		logger.error(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GroupPreferenceException.class)
	public ResponseEntity<ErrorResponse> handleException(GroupPreferenceException ex) {

		ErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setDateTimel(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		logger.error(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}