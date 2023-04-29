package com.example.demo.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.pojo.ResponseMessage;

/**
 * The Class FileUploadExceptionAdvice.
 */
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Handle max size exception.
	 *
	 * @param exc the exc
	 * @return the response entity
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	}
}
