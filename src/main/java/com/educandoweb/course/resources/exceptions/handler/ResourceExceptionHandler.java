package com.educandoweb.course.resources.exceptions.handler;

import java.time.Instant;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.educandoweb.course.resources.exceptions.handler.messages.StandardErrorMessage;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorMessage> resourceNotFoundHandler(ResourceNotFoundException e, 
			HttpServletRequest request) {
		StandardErrorMessage errorMessage = createStandardErrorMessage(e.getMessage(), 
				request, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, NullPointerException.class, 
			BadRequestException.class })
	public ResponseEntity<StandardErrorMessage> illegalArgumentHandler(Exception e, 
			HttpServletRequest request) {
		StandardErrorMessage errorMessage = createStandardErrorMessage(e.getMessage(), 
				request, HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardErrorMessage> databaseExceptionHandler(DatabaseException e,
			HttpServletRequest request) {
		StandardErrorMessage errorMessage = createStandardErrorMessage(
				"Database error. Referential integrity constraint violation", 
				request, HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	private StandardErrorMessage createStandardErrorMessage(String message, HttpServletRequest request, 
			HttpStatus status) {
		 StandardErrorMessage error = new StandardErrorMessage(
				Instant.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				request.getRequestURI());
		 return error;
	}
}
