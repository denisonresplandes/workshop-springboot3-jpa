package com.educandoweb.course.resources.exception.handler.messages;

import org.springframework.http.HttpStatus;

public record ResponseMessage(HttpStatus httpStatus, String message) {
}
