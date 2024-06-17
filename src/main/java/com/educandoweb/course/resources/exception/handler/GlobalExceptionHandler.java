package com.educandoweb.course.resources.exception.handler;

import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.educandoweb.course.resources.exception.handler.messages.ResponseMessage;
import com.educandoweb.course.services.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseMessage> notFoundHandler(NotFoundException e) {
		ResponseMessage responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND, 
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	}
	
	@ExceptionHandler(value = { IllegalArgumentException.class, NullPointerException.class, 
			BadRequestException.class })
	public ResponseEntity<ResponseMessage> illegalArgumentHandler(Exception e) {
		ResponseMessage userMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, 
				e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userMessage);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseMessage> dataIntegrityViolationHandler(DataIntegrityViolationException e) {
		ResponseMessage userMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, 
				"Referential integrity constraint violation");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userMessage);
	}

}
