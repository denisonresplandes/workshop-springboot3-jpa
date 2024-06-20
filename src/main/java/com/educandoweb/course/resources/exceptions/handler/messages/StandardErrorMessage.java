package com.educandoweb.course.resources.exceptions.handler.messages;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public record StandardErrorMessage(
		@JsonFormat(shape = JsonFormat.Shape.STRING, 
			pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
		Instant timestamp, 
		int status,
		String error,
		String message,
		String path) {
}
