package com.educandoweb.course.resources.exceptions.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.educandoweb.course.resources.exceptions.handler.messages.SimpleErrorMessage;
import com.educandoweb.course.resources.exceptions.handler.messages.StandardErrorMessage;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
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
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, 
			WebRequest request) {
		List<SimpleErrorMessage> errors = new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(createSimpleErrorMessage(error.getField(), 
					error.getDefaultMessage()));
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
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
	
	private SimpleErrorMessage createSimpleErrorMessage(String field, String message) {
		return new SimpleErrorMessage(field, message);
	}
}